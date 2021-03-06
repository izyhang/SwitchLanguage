package com.zyhang.switchlanguage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import static android.content.pm.ApplicationInfo.FLAG_SUPPORTS_RTL;

/**
 * Created by zyhang on 2018/9/11.18:09
 */

public class SwitchLanguageUtils {

    /**
     * 配置多语言
     *
     * @param newBase context
     * @return context
     * @see AutoConfigLanguageActivity#attachBaseContext(Context)
     */
    public static Context configLanguage(@NonNull Context newBase) {
        Configuration configuration = Resources.getSystem().getConfiguration();
        Locale locale = SwitchLanguageHelper.getInstance()
                .getLocale(newBase);
        if (locale != null) {
            configuration.setLocale(locale);
        }
        return newBase.createConfigurationContext(configuration);
    }

    /**
     * 开始切换语言
     *
     * @param locale 目标切换语言
     */
    public static void startSwitchLanguage(@NonNull Locale locale) {
        startSwitchLanguage(locale, 1000);
    }

    /**
     * 开始切换语言
     *
     * @param locale      目标切换语言
     * @param delayMillis 切换动画显示时间
     */
    public static void startSwitchLanguage(@NonNull Locale locale, @IntRange(from = 700) long delayMillis) {
        SwitchLanguageHelper.getInstance()
                .startSwitch(locale, delayMillis);
    }

    /**
     * 立刻结束切换语言界面
     * 无需手动调用
     *
     * @param context context
     */
    public static void endSwitchLanguage(@NonNull Context context) {
        SwitchLanguageActivity.destroy(context);
    }

    /**
     * 判断当前是否处于镜像模式
     *
     * @param context context
     * @return 是否镜像
     */
    public static boolean isRTL(@NonNull Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        boolean supportRTL = (applicationInfo.flags & FLAG_SUPPORTS_RTL) == FLAG_SUPPORTS_RTL;
        boolean isRTL = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
        return supportRTL && isRTL;
    }
}
