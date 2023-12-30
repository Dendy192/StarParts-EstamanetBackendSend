// 
// Decompiled by Procyon v0.5.36
// 

package id.git.api.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ComponentsItem
{
    @SerializedName("type")
    private String type;
    @SerializedName("parameters")
    private List<ParametersItem> parameters;
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setParameters(final List<ParametersItem> parameters) {
        this.parameters = parameters;
    }
    
    public List<ParametersItem> getParameters() {
        return this.parameters;
    }
}
