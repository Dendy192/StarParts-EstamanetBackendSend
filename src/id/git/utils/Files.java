// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class Files
{
    public static Properties getProperties(final String configFilename) {
        final Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(configFilename));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        return prop;
    }
}
