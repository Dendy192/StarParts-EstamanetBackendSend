// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Template
{
    @SerializedName("components")
    private List<ComponentsItem> components;
    @SerializedName("name")
    private String name;
    @SerializedName("language")
    private Language language;
    
    public void setComponents(final List<ComponentsItem> components) {
        this.components = components;
    }
    
    public List<ComponentsItem> getComponents() {
        return this.components;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setLanguage(final Language language) {
        this.language = language;
    }
    
    public Language getLanguage() {
        return this.language;
    }
}
