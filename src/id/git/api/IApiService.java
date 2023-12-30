// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api;

import retrofit2.http.POST;
import id.git.api.model.MsgObj;
import retrofit2.Call;
import retrofit2.http.Body;

public interface IApiService
{
    @POST("messages")
    Call<MsgObj> sendMessage(@Body final StringBuilder p0);
}
