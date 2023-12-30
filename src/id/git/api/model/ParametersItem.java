// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import com.google.gson.annotations.SerializedName;

public class ParametersItem
{
    @SerializedName("document")
    private Document document;
    @SerializedName("type")
    private String type;
    @SerializedName("text")
    private String text;
    
    public void setDocument(final Document document) {
        this.document = document;
    }
    
    public Document getDocument() {
        return this.document;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
}
