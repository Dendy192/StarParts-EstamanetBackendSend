// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import java.io.IOException;
import okhttp3.Request;
import id.git.model.WaParam;
import okhttp3.Response;
import okhttp3.Interceptor;

public class MyIntercepter implements Interceptor
{
    public Response intercept(final Interceptor.Chain chain) throws IOException {
        final Request request = chain.request();
        final String auth = WaParam.getToken();
        final Request newRequest = request.newBuilder().header("Authorization", "Bearer " + auth).header("Content-Type", "application/json").build();
        return chain.proceed(newRequest);
    }
}
