// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import com.google.gson.annotations.SerializedName;

public class Document
{
    @SerializedName("id")
    private String id;
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
}
