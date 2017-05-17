package com.snxj.volley.untils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.snxj.volley.base.BaseApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BitmapUtils {
    private static String path;

    /**
     * drawable图片转换为bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),
                // drawable.getOpacity() != PixelFormat.OPAQUE ?
                // Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        // canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            if (fileUrl == null) {
                return null;
            }
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

//    /**
//     * bitmap转化为byte数组
//     *
//     * @param bitmap
//     * @return
//     */
//    public static byte[] bitmap2Byte(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
////        byte[] byteArray = stream.toByteArray();
//        return stream.toByteArray();
//    }

//    private static void savePhoto(Bitmap bt, String name,
//                                  SaveImageListener saveImageListener) {
//
//        try {
//            BitmapUtils.saveFile(bt, name, saveImageListener);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String fileName,
                                SaveImageListener saveImageListener) throws Exception {
        String path1 = Environment.getExternalStorageDirectory()
                + File.separator
                + BaseApplication.getApplication().getPackageName();
        File dirFile = new File(path1);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
            if ( dirFile.getParentFile().mkdirs()) {
                LogUtils.i("", "mkdirs success");
            } else {
                LogUtils.i("", "mkdirs fail");
            }
        }
        File myCaptureFile = new File(path1 + File.separator + fileName
                + ".jpg");
        path = myCaptureFile.getPath();
        myCaptureFile.createNewFile();
        if (myCaptureFile.createNewFile()) {
            LogUtils.i("", "createNewFile success");
        } else {
            LogUtils.i("", "createNewFile fail");
        }
        FileOutputStream fos = new FileOutputStream(myCaptureFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        saveImageListener.saveImageDone();
        return myCaptureFile;
    }

    public static String getPath() {
        return path;
    }

    /**
     * my保存文件
     *
     * @param bm
     * @throws IOException
     */
    public static File saveBtmToFile(Bitmap bm) throws Exception {
        // 路径
        File myCaptureFile = new File(Environment.getExternalStorageDirectory()
                + "path", String.valueOf(System.currentTimeMillis())
                + ".jpg");
        if (!myCaptureFile.exists()) {
            File vDirPath = myCaptureFile.getParentFile();
            vDirPath.mkdirs();
            if ( vDirPath.mkdirs()) {
                LogUtils.i("", "mkdirs success");
            } else {
                LogUtils.i("", "mkdirs fail");
            }
        } else {
            if (myCaptureFile.exists()) {
                myCaptureFile.delete();
                if (myCaptureFile.delete()) {
                    LogUtils.i("", "delete success");
                } else {
                    LogUtils.i("", "delete fail");
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(myCaptureFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    public interface SaveImageListener {
         void saveImageDone();
    }

}
