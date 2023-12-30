// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import com.google.gson.annotations.SerializedName;

public class Language
{
    @SerializedName("code")
    private String code;
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}
