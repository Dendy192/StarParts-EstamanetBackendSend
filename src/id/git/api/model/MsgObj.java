// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import java.util.List;

public class MsgObj
{
    private String messaging_product;
    private List<MessagesItem> messages;
    private List<ContactsItem> contacts;
    
    public String getMessaging_product() {
        return this.messaging_product;
    }
    
    public void setMessaging_product(final String messaging_product) {
        this.messaging_product = messaging_product;
    }
    
    public List<MessagesItem> getMessages() {
        return this.messages;
    }
    
    public void setMessages(final List<MessagesItem> messages) {
        this.messages = messages;
    }
    
    public List<ContactsItem> getContacts() {
        return this.contacts;
    }
    
    public void setContacts(final List<ContactsItem> contacts) {
        this.contacts = contacts;
    }
}
