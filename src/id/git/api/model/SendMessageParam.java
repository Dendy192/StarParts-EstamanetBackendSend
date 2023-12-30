package id.git.api.model;

import com.google.gson.annotations.SerializedName;

public class SendMessageParam {
	@SerializedName("template")
	private Template template;

	@SerializedName("messaging_product")
	private String messagingProduct;

	@SerializedName("to")
	private String to;

	@SerializedName("type")
	private String type;

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setMessagingProduct(String messagingProduct) {
		this.messagingProduct = messagingProduct;
	}

	public String getMessagingProduct() {
		return this.messagingProduct;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return this.to;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
