package com.github.marhary.saveurlife.auth;


import java.util.Locale;

public class VkAuthorizer {
    public static void setUserId(int userId) {
        VkAuthorizer.userId = userId;
    }

    private static int userId = -1;

    public String getUrl(int appId, String... scope) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < scope.length - 1; i++)
            stringBuilder.append(scope[i]).append(",");
        String stringScope = stringBuilder.append(scope[scope.length - 1]).toString();
        String link = String.format(Locale.getDefault(), "https://oauth.vk.com/authorize?client_id=%d&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=%s&response_type=token&v=5.60&revoke=1", appId, stringScope);
        return link;
    }

    public static int getUserId() {
        return userId;
    }

    public int parseUrl(String url) {
        String[] buffer = url.split("=");
        userId = Integer.parseInt(buffer[3]);
        return userId;
    }
}
