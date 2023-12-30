// 
// Decompiled by Procyon v0.5.36
// 

package id.git.main;

import id.git.service.SendEngine;
import id.git.utils.Config;

public class tester
{
    public static void main(final String[] args) {
        Config.init();
        final SendEngine se = new SendEngine();
        se.engine();
    }
}
