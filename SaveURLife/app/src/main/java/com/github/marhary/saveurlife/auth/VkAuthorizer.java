package com.github.marhary.saveurlife.auth;


import android.net.Uri;

import java.util.Locale;

public class VkAuthorizer {
    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId = -1;


    public String getUrl(int appId) {
        String link = String.format(Locale.getDefault(),IConstant.VK_URL , appId);
        return link;
    }

    public int getUserId() {
        return userId;
    }

    public int getIdFromUrl(String url) {
        url = url.replace('#','?');
        Uri uri = Uri.parse(url);
        userId = Integer.parseInt(uri.getQueryParameter("user_id"));
        return userId;
    }
}
