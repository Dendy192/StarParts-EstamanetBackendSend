// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

public class Message
{
    private String messaging_product;
    private String to;
    private String type;
    private Document document;
    
    public String getMessaging_product() {
        return this.messaging_product;
    }
    
    public void setMessaging_product(final String messaging_product) {
        this.messaging_product = messaging_product;
    }
    
    public String getTo() {
        return this.to;
    }
    
    public void setTo(final String to) {
        this.to = to;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public Document getDocument() {
        return this.document;
    }
    
    public void setDocument(final Document document) {
        this.document = document;
    }
}
