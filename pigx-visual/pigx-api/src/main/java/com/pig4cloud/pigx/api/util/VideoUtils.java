package com.pig4cloud.pigx.api.util;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public final class VideoUtils {

	//参数：视频路径和缩略图保存路径
	public static void fetchFrame(String videofile, String framefile)
	        throws Exception {
	    File targetFile = new File(framefile);
	    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
	    ff.start();
	    int length = ff.getLengthInFrames();
	    int i = 0;
	    Frame f = null;
	    while (i < length) {
	        // 去掉前5帧，避免出现全黑的图片，依自己情况而定
	        f = ff.grabImage();
	        if ((i > 5) && (f.image != null)) {
	            break;
	        }
	        i++;
	    }
	    ImageIO.write(FrameToBufferedImage(f), "jpg", targetFile);
	    ff.flush();
	    ff.stop();
	}
	 
	public static BufferedImage FrameToBufferedImage(Frame frame) {
	    //创建BufferedImage对象
	    Java2DFrameConverter converter = new Java2DFrameConverter();
	    BufferedImage bufferedImage = converter.getBufferedImage(frame);
	    return bufferedImage;
	}
	
}