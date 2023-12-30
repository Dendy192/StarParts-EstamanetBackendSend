// 
// Decompiled by Procyon v0.5.36
// 

package id.git.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import id.git.model.Param;
import id.git.utils.Function;
import id.git.utils.SQLData;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendEngine {
	String url;
	String messaging_product;
	String token;
	List<String> total;
	List<String> failed;
	List<String> succes;
	private static Logger log = Logger.getLogger(SendEngine.class.getName());;

	public SendEngine() {
		this.url = Param.getUrl();
		this.messaging_product = Param.getProduct();
		this.token = Param.getToken();
		this.total = new ArrayList<String>();
		this.failed = new ArrayList<String>();
		this.succes = new ArrayList<String>();
	}

	public void engine() {
		Function.printStatus("SEND ENGINE: SENDING START");
		try {
			String period = Function.getPeriod();
			System.out.println(period);
			HashMap<String, String> getInvo = SQLData.getInvoice(period);
			System.out.println(getInvo);
			int tmp = getInvo.size() / 2;
			int s1 = 0;
			int e1;
			if (tmp == 0) {
				e1 = 1;
			} else {
				e1 = tmp;
			}
			int s2 = e1;
			int e2 = getInvo.size();
			System.out.println(getInvo.size());
			Set<String> no = getInvo.keySet();
			List<String> po = new ArrayList<String>();
			for (String n : no) {
				po.add(n);
				System.out.println(n);
			}
			List<Thread> tList = new ArrayList<Thread>();
			Thread t1 = new Thread(new myCellAble("1", s1, e1, po, getInvo));
			t1.start();
			tList.add(t1);
			Thread t2 = new Thread(new myCellAble("2", s2, e2, po, getInvo));
			t2.start();
			tList.add(t2);
			for (Thread t3 : tList) {
				t3.join();
				System.out.println("join thread : " + t3);
			}
		} catch (Exception e3) {
			SendEngine.log.info("Thread : " + e3.getMessage());
			e3.printStackTrace();
		}
		Function.printStatus("SEND ENGINE: SENDING END");
		Date date = new Date();
		String start = Param.getStartDate();
		String end = Param.getFinishDate();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
		String my = sf1.format(date);
		String sDate = String.valueOf(my) + "-" + start;
		String fDate = String.valueOf(my) + "-" + end;
		int to = this.total.size();
		int su = this.succes.size();
		int fa = this.failed.size();
		SQLData.InsertLogSCH(sDate, fDate, to, su, fa, "WA");
	}

	public void prepare(int Start, int End, List<String> idCus, HashMap<String, String> getInvo) {
		System.out.println("sebelom for");
		System.out.println(String.valueOf(Start) + " " + End);
		for (int i = Start; i < End; ++i) {
			System.out.println("masuk for");
			String n = idCus.get(i);
			String path = getInvo.get(n);
			List<String> cust = SQLData.getCustomerPhone(n);
			Date date = new Date();
			Locale lc = new Locale("ID", "INDONESIA");
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", lc);
			SimpleDateFormat sdf2 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", lc);
			String per = sdf.format(date);
			if (cust.size() != 0) {
				String name = cust.get(0);
				String phone = cust.get(1);
				String upl = this.uploadPDF(this.messaging_product, path);
				System.out.println("id abis upload" + upl);
				SendEngine.log.info("id: " + n + " name: " + name + " phone: " + phone);
				String send = this.sendPDF(this.messaging_product, phone, upl, name, per);
				System.out.println(send);
				if (send.contains("error")) {
					this.failed.add(n);
					SQLData.updateLog(n, "R", send, Function.getPeriod());
				} else {
					System.out.println(send);
					this.succes.add(name);
					SQLData.updateLog(n, "Y", "Sended ", Function.getPeriod());
				}
				this.total.add(n);
			} else {
				SQLData.updateLog(n, "R", "not found phone number", Function.getPeriod());
			}
		}
	}

	public String sendPDF(String messaging_product, String to, String id, String filename, String period) {
		String result = "Success";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("application/json");
			System.out.println(to);
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"messaging_product\": \"" + messaging_product + "\",");
			sb.append("\"to\": \"" + to + "\",");
			sb.append("\"type\": \"template\",");
			sb.append("\"template\": {");
			sb.append("\"name\": \"arstatment\",");
			sb.append("\"language\": {");
			sb.append("\"code\": \"id\"");
			sb.append("},");
			sb.append("\"components\": [");
			sb.append("{");
			sb.append("\"type\": \"header\",");
			sb.append("\"parameters\": [");
			sb.append("{");
			sb.append("\"type\": \"document\",");
			sb.append("\"document\": {");
			sb.append("\"id\":\"" + id + "\",");
			sb.append("\"filename\":\"" + filename + "\"");
			sb.append("}");
			sb.append("}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("\"type\": \"body\",");
			sb.append("\"parameters\": [");
			sb.append("{");
			sb.append("\"type\": \"text\",");
			sb.append("\"text\": \"" + period + "\"");
			sb.append("}");
			sb.append("]");
			sb.append("}");
			sb.append("]");
			sb.append("}");
			sb.append("}");
			RequestBody body = RequestBody.create(mediaType, sb.toString());
			Request request = new Request.Builder().url(String.valueOf(this.url) + "messages").method("POST", body)
					.addHeader("Authorization", "Bearer " + this.token).build();
			Response res = client.newCall(request).execute();
			result = res.body().string();
			System.out.println("dari send message" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String uploadPDF(String product, String file) {
		String result = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("text/plain");
			RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("messaging_product", product).addFormDataPart("file", file,
							RequestBody.create(MediaType.parse("application/pdf"), new File(file)))
					.build();
			Request request = new Request.Builder().url(String.valueOf(this.url) + "media").method("POST", body)
					.addHeader("Authorization", "Bearer " + this.token).build();
			Response response = client.newCall(request).execute();
			String id = response.body().string();
			System.out.println(id);
			if (!id.contains("error")) {
				JSONObject res = new JSONObject(id);
				String idFin = res.getString("id");
				System.out.println("masuk ke id: " + idFin);
				result = idFin;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	class myCellAble extends Thread {
		private String numberThread;
		private int Start;
		private int End;
		private List<String> idCus;
		private HashMap<String, String> getInvo;

		myCellAble(String numberThread, int Start, int End, List<String> idCus, HashMap<String, String> getInvo) {
			this.numberThread = numberThread;
			this.Start = Start;
			this.End = End;
			this.idCus = idCus;
			this.getInvo = getInvo;
		}

		@Override
		public void run() {
			SendEngine.this.prepare(this.Start, this.End, this.idCus, this.getInvo);
			System.out.println("THread: " + this.numberThread);
		}
	}
}
