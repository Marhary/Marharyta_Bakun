package com.github.marhary.saveurlife.imageLoader;

import android.graphics.Bitmap;
import android.util.Log;

import com.github.marhary.saveurlife.auth.IConstant;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class MemoryCache {

    private static final String TAG = IConstant.MEMORY_CACHE;

    private Map<String, Bitmap> cache = Collections
            .synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));

    private long size = 0;

    private long limit = 1000000;

    MemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long new_limit) {
        limit = new_limit;
        Log.i(TAG, IConstant.WILL_USE + limit / 1024. / 1024. + IConstant.MB);
    }

    Bitmap get(String id) {
        try {
            if(!cache.containsKey(id))
                return null;

            return cache.get(id);

        } catch(NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    void put(String id, Bitmap bitmap) {

        try{

            if(cache.containsKey(id))
                size -= getSizeInBytes(cache.get(id));

            cache.put(id, bitmap);
            size += getSizeInBytes(bitmap);
            checkSize();

        } catch(Throwable e) {
            e.printStackTrace();
        }
    }

    private void checkSize() {
        Log.i(TAG, IConstant.CACHE_SIZE + size + IConstant.LENGTH + cache.size());

        if(size > limit) {
            Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();

            while(iter.hasNext()) {
                Entry<String, Bitmap> entry = iter.next();
                size -= getSizeInBytes(entry.getValue());
                iter.remove();
                if(size <= limit)
                    break;
            }
            Log.i(TAG, IConstant.CLEAN + cache.size());
        }
    }

    void clear() {
        try {
            cache.clear();
            size = 0;
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        if(bitmap == null)
            return 0;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}

