package com.hyper.mod;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class AdBlock {
    public static void init(ClassLoader cl) {
        if (!Config.ENABLE_AD_BLOCK) return;
        try {
            // 拦截小米系统广告
            Class<?> miAd = XposedHelpers.findClass("com.miui.system.ad.AdManager", cl);
            if (miAd != null) {
                XposedHelpers.findAndHookMethod(miAd, "showAd", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(null);
                    }
                });
            }
        } catch (Exception ignored) {}
    }
}
