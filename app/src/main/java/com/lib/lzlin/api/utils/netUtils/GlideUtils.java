package com.lib.lzlin.api.utils.netUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.lib.lzlin.api.R;
import com.lib.lzlin.api.utils.commonUtils.CommonUtils;

import java.util.concurrent.ExecutionException;

/**
 * GlideUtils
 * 
 * 类的描述: Glide工具类
 * 创建时间: 2017/8/31  15:01
 * 修改备注:
 */

public class GlideUtils {
    private static GlideUtils instance=null;
    private GlideUtils(){}
    public synchronized static GlideUtils getInstance(){
        if (instance==null){
            synchronized (GlideUtils.class){
                if (instance==null){
                    instance=new GlideUtils();
                }
            }
        }
        return instance;
    }

    //原图，是我博客的头像
    /**
     * 占位图 加载失败 小熊圆形图
     * @param context
     * @param path
     * @param img
     */
    public void GlideImage(Context context, String path, ImageView img){
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).crossFade(1000).into(img);
    }

    /**
     * yyl占位图 加载失败 矩形图
     * @param context
     * @param path
     * @param img
     */
    public void GlideRectImage(Context context, String path, ImageView img){
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).crossFade(1000).into(img);
    }

    //原图 -> 圆图
    public void GlideImage(Activity activity, String path, Transformation transformation, ImageView img) {
        Glide.with(activity).load(path).bitmapTransform(transformation).crossFade(1000).into(img);
    }
    // 设置背景图
    public void GlideImage(Activity activity, String path, ImageView img, int bgResId){
        img.setBackgroundResource(bgResId);
        Glide.with(activity).load(path).crossFade(1000).into(img);
    }
    public void GlideImage(Activity activity, String path, Transformation transformation, ImageView img, int bgResId) {
        img.setBackgroundResource(bgResId);
        Glide.with(activity).load(path).bitmapTransform(transformation).crossFade(1000).into(img);
    }

    /**
     * Glide 通过 URL 获取 Bitmap 对象
     * @param ctx
     * @param url
     * @return
     */
    public static Bitmap getBitmapByUrl(Context ctx, String url, int width, int heigh) {
        Bitmap myBitmap = null;
        try {
            myBitmap = Glide
                    .with(ctx)
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(CommonUtils.px2dip(ctx, width), CommonUtils.px2dip(ctx, heigh))
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return myBitmap;
    }

//    //原图的毛玻璃、高斯模糊效果
//    public void BlurTrans(Activity activity, String path, Transformation transformation, ImageView img){
//        Glide.with(this).load(url).bitmapTransform(new BlurTransformation(this, 25)).crossFade(1000).into(image3);
//
//    }
//
//    //原图基础上复合变换成圆图 +毛玻璃（高斯模糊）
//    ImageView image4 = (ImageView) findViewById(R.id.image4);
//        Glide.with(this).load(url).bitmapTransform(new BlurTransformation(this, 25), new CropCircleTransformation(this)).crossFade(1000).into(image4);
//
//    //原图处理成圆角，如果是四周都是圆角则是RoundedCornersTransformation.CornerType.ALL
//    ImageView image5 = (ImageView) findViewById(R.id.image5);
//        Glide.with(this).load(url).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM)).crossFade(1000).into(image5);
//}

}
