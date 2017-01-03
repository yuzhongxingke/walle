package com.meituan.android.walle;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.util.Map;

public final class WalleChannelReader {
    private WalleChannelReader() {
        super();
    }

    /**
     * get channel
     *
     * @param context context
     * @return channel, null if not fount
     */
    @Nullable
    public final static String getChannel(@NonNull Context context) {
        ChannelInfo channelInfo = getChannelInfo(context);
        if (channelInfo == null) {
            return null;
        }
        return channelInfo.getChannel();
    }

    /**
     * get channel info (include channle & extraInfo)
     *
     * @param context context
     * @return channel info
     */
    @Nullable
    public final static ChannelInfo getChannelInfo(@NonNull Context context) {
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return ChannelReader.get(new File(apkPath));
    }

    /**
     * get value by key
     *
     * @param context context
     * @param key     the key you store
     * @return value
     */
    @Nullable
    public final static String get(@NonNull Context context, @NonNull String key) {
        Map<String, String> channelMap = getChannelInfoMap(context);
        if (channelMap == null) {
            return null;
        }
        return channelMap.get(key);
    }

    /**
     * get all channl info with map
     *
     * @param context context
     * @return map
     */
    @Nullable
    public final static Map<String, String> getChannelInfoMap(@NonNull Context context) {
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return ChannelReader.getMap(new File(apkPath));
    }

    @Nullable
    private final static String getApkPath(@NonNull Context context) {
        String apkPath = null;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            apkPath = applicationInfo.sourceDir;
        } catch (Throwable e) {
        }
        return apkPath;
    }
}
