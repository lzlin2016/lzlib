package com.lib.lzlin.api.utils.netUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: Fresco, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class FrescoUtils {
	public static void displayImage(String url, SimpleDraweeView draweeView) {
		Uri uri = Uri.parse(url);
		draweeView.setImageURI(uri);
//		draweeView.setBackgroundResource(R.drawable.icon_loading_fail); // TODO lz 定制, 修改可点击重试
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).build();
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setImageRequest(request)
//				.setTapToRetryEnabled(true)	// TODO lz 定制, 修改可点击重试
				.setAutoPlayAnimations(true)
				.build()
				;
		draweeView.setController(controller);
	}

	public static void displayImageCorners(String url, SimpleDraweeView draweeView, float radius) {
		Uri uri = Uri.parse(url);
		draweeView.setImageURI(uri);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).build();
		DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
				.setAutoPlayAnimations(true).build();
		draweeView.setController(controller);
		RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius);
		draweeView.getHierarchy().setRoundingParams(roundingParams);
	}

	/**
	 * 获取bitMap 对象
	 * @param url
	 * @param context
     */
	static Bitmap mBitmap = null;
	public static Bitmap getBitmap(String url, Context context) {
		ImageRequest imageRequest = ImageRequestBuilder
				.newBuilderWithSource( Uri.parse(url))
//				.newBuilderWithSource( Uri.parse(getFeedItem(position).feedImageUrl.get(index)))
				.setProgressiveRenderingEnabled(true)
				.build();

		ImagePipeline imagePipeline = Fresco.getImagePipeline();
		DataSource<CloseableReference<CloseableImage>>
				dataSource = imagePipeline.fetchDecodedImage(imageRequest,context);

		dataSource.subscribe(new BaseBitmapDataSubscriber() {

								 @Override
								 public void onNewResultImpl(@Nullable Bitmap bitmap) {
									 mBitmap = bitmap;
								 }

								 @Override
								 public void onFailureImpl(DataSource dataSource) {
								 }
							 },
				CallerThreadExecutor.getInstance());
		return mBitmap;
	}

	public static void displayImageRounded(String url, SimpleDraweeView draweeView) {
		Uri uri = Uri.parse(url);
		draweeView.setImageURI(uri);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).build();
		DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
				.setAutoPlayAnimations(true).build();
		draweeView.setController(controller);
		RoundingParams roundingParams = RoundingParams.asCircle();
		draweeView.getHierarchy().setRoundingParams(roundingParams);
	}

	//设置本地图片圆形
	public static void displayLocalImageRounded(int it, SimpleDraweeView draweeView) {
		draweeView.setImageResource(it);
		ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(it).build();
		DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
				.setAutoPlayAnimations(true).build();
		draweeView.setController(controller);
		RoundingParams roundingParams = RoundingParams.asCircle();
		draweeView.getHierarchy().setRoundingParams(roundingParams);
	}

	// TODO  测试清空缓存
	/**
	 * 同时清理内存缓存和硬盘缓存
	 * ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
	 * imagePipeline.clearCaches();
	 */
	public static void clearCache() {
		ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
		// 清空内存缓存(包括Bitmap缓存和未解码图片的缓存)
		imagePipeline.clearMemoryCaches();
		// 清空硬盘缓存,一般在设置面功用户手动清理
		imagePipeline.clearDiskCaches();
		// 同时清理内存缓存和硬盘缓存
		imagePipeline.clearCaches();

	}

	/**
	 * 清空内存缓存(包括Bitmap缓存和未解码图片的缓存)
	 * ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
	 * imagePipeline.clearMemoryCaches();
	 */
	public static void clearMemoryCache() {
		ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
		// 清空内存缓存(包括Bitmap缓存和未解码图片的缓存)
		imagePipeline.clearMemoryCaches();
	}

	/**
	 * 清空硬盘缓存,一般在设置面功用户手动清理
	 * ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
	 * imagePipeline.clearDiskCaches();
	 */
	public static void clearDiskCache() {
		ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
		// 清空硬盘缓存,一般在设置面功用户手动清理
		imagePipeline.clearDiskCaches();

	}

}
