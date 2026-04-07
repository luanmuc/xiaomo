package com.hyper.mod;

import de.robv.android.xposed.XposedHelpers;

public class NetOpt {
    public static void init(ClassLoader cl) {
        if (!Config.ENABLE_NET_OPT) return;
        try {
            Class<?> netClass = XposedHelpers.findClass("android.net.NetworkFactory", cl);
            if (netClass != null) {
                XposedHelpers.setStaticBooleanField(netClass, "TCP_NODELAY", true);
            }
        } catch (Exception ignored) {}
    }
}
