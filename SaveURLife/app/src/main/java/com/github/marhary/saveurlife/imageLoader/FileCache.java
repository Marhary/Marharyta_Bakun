package com.github.marhary.saveurlife.imageLoader;

import android.content.Context;
import android.os.Environment;

import java.io.File;

class FileCache {

    private File cacheDir;

    FileCache(Context context) {

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(Environment.getExternalStorageDirectory(), "MyList");
        } else {
            cacheDir = context.getCacheDir();
        }

        if(!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    File getFile(String url) {

        String filename = String.valueOf(url.hashCode());

        return new File(cacheDir, filename);
    }

    void clear() {
        File[] files = cacheDir.listFiles();
        if(files == null) {
            return;
        }

        for(File f : files) {
            f.delete();
        }
    }
}
