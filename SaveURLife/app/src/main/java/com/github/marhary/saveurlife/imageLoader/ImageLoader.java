package com.github.marhary.saveurlife.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.github.marhary.saveurlife.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    private MemoryCache memoryCache = new MemoryCache();

    private FileCache fileCache;

    private Map<ImageView, String> imageViews = Collections
            .synchronizedMap(new WeakHashMap<ImageView, String>());

    private ExecutorService executorService;

    private Handler handler = new Handler();

    ImageLoader(Context context) {

        fileCache = new FileCache(context);

        executorService = Executors.newFixedThreadPool(5);
    }

    private final int stub_int = R.mipmap.note_launcher;

    void displayImage(String url, ImageView imageView) {

        imageViews.put(imageView, url);

        Bitmap bitmap = memoryCache.get(url);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            queueImage(url, imageView);

            imageView.setImageResource(stub_int);
        }
    }

    private void queueImage(String url, ImageView imageView) {

        ImageToLoad p = new ImageToLoad(url, imageView);

        executorService.submit(new ImagesLoader(p));
    }

    private class ImageToLoad {
        String url;
        ImageView imageView;

        ImageToLoad(String u, ImageView i) {
            url = u;
            imageView = i;
        }
    }

    private class ImagesLoader implements Runnable {

        ImageToLoad photoToLoad;

        ImagesLoader(ImageToLoad ptl) {
            this.photoToLoad = ptl;
        }

        @Override
        public void run() {
            try {

                if (imageViewReused(photoToLoad))
                    return;

                Bitmap bmp = getBitmap(photoToLoad.url);

                memoryCache.put(photoToLoad.url, bmp);

                if (imageViewReused(photoToLoad))
                    return;

                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);

                handler.post(bd);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getBitmap(String url) {

        File f = fileCache.getFile(url);

        Bitmap b = decodeFile(f);

        if (b != null)
            return b;

        try {

            Bitmap bitmap;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();

            OutputStream os = new FileOutputStream(f);

            Utils.copyStream(is, os);

            os.close();
            conn.disconnect();

            bitmap = decodeFile(f);

            return bitmap;

        } catch (Throwable e) {
            e.printStackTrace();

            if (e instanceof OutOfMemoryError)
                memoryCache.clear();

            return null;
        }
    }

    private Bitmap decodeFile(File f) {

        try {

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();

            final int REQUIRED_SIZE = 85;

            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;

            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }

                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean imageViewReused(ImageToLoad ptl) {

        String tag = imageViews.get(ptl.imageView);

        return tag == null || !tag.equals(ptl.url);
    }

    private class BitmapDisplayer implements Runnable {

        Bitmap bitmap;
        ImageToLoad photoToLoad;

        BitmapDisplayer(Bitmap b, ImageToLoad p) {
            bitmap = b;
            photoToLoad = p;
        }

        @Override
        public void run() {
            if (imageViewReused(photoToLoad))
                return;

            if (bitmap != null) {
                photoToLoad.imageView.setImageBitmap(bitmap);
            } else {
                photoToLoad.imageView.setImageResource(stub_int);
            }
        }
    }

    void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }
}