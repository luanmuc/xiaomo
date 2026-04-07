package com.hyper.mod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpp) {
        String pkg = lpp.packageName;
        // 静态作用域：仅系统进程生效
        if ("android".equals(pkg)) {
            NetOpt.init(lpp.classLoader);
            AdBlock.init(lpp.classLoader);
            return;
        }
        // 仅指定APP生效，避免全量扫描
        if (pkg.equals("com.tencent.mm")
                || pkg.equals("com.tencent.mobileqq")
                || pkg.equals("com.bytedance.douyin")
                || pkg.equals("com.taobao.taobao")
                || pkg.equals("com.alipay.mobile")) {
            AdBlock.init(lpp.classLoader);
        }
    }
}
