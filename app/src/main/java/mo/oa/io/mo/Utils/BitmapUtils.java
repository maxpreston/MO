package mo.oa.io.mo.Utils;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片处理类 主要作用为进行大图片的压缩，以减小内存占用 在引用对应Bitmap的Activity
 * onDestroy时，调用析构函数释放Bitmap在native层所占的内存
 */

public class BitmapUtils {
	static BitmapUtils bu;
	Context context;
	Bitmap oldBitmap;
	Bitmap newBitmap;
	InputStream is;

	public BitmapUtils(Context context) {
		this.context = context;
	}

	/**
	 * TODO 加上锁定，提升运行效率。
	 * 
	 * @author 邹奇 双重锁定。只有在bu还没有被初始化的时候才会new BitmapUtils(context)，然后加上同步锁。
	 *         等bu一旦初始化完成了，就再也走不到new
	 *         BitmapUtils(context)这行代码了，这样执行getInstance方法也不会受到同步锁的影响，
	 *         效率上会有一定的提升。
	 * @param context
	 * @return
	 */
	public static BitmapUtils getInstance(Context context) {
		if (bu == null) {
			synchronized (BitmapUtils.class) {
				if (bu == null) {
					bu = new BitmapUtils(context);
				}
			}
		}
		return bu;
	}

	public Bitmap compressBitmap(int pictureId, Bitmap.Config config) {
		is = context.getResources().openRawResource(pictureId);
		oldBitmap = BitmapFactory.decodeStream(is);
		newBitmap = oldBitmap.copy(config, true);
		if (newBitmap == null) {
			return oldBitmap;
		} else {
			return newBitmap;
		}
	}

	/**
	 * 释放oldBitmap/newBitmap在native层的内存空间 注意：该函数需要在位图以及没有被其他模块引用的情况下调用
	 * 建议：在Activity/Fragment onDestroy()时调用
	 */
	public void releaseNativeBitmap() {
		// HRY修改于@2015/10/04
		if (oldBitmap != null && !oldBitmap.isRecycled()) {
			oldBitmap.recycle();
		}
		if (newBitmap != null && !newBitmap.isRecycled()) {
			newBitmap.recycle();
		}
		// 尝试告诉GC进行内存回收
		System.gc();
		if (bu != null) {
			bu = null;
		}
	}

	/**
	 * 关闭输入流
	 */
	public void closeAll() {
		// HRY修改于@2015/10/04
		// 位图处理完成后，立即关闭输入流
		try {
			if (is != null) {
				is.close();
				is = null;
			}
		} catch (Exception e) {
			// 置空处理
			is = null;
			e.printStackTrace();
		}
	}
}
