package com.pig4cloud.pigx.api.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	/**
	    *   把文件拷贝到指定的路径
	 * 
	 * @param files
	 * @param path
	 * @return
	 */
	public static List<String> copyFilesToPath(MultipartFile[] files,String path){
		List<String> list=new ArrayList<String>();
		BufferedInputStream inBuff=null;
		BufferedOutputStream outBuff=null;
		//如果路径不存在,创建路径
		if(!new File(path).exists()) {
			new File(path).mkdirs();
		}
		for(MultipartFile f:files) {
			String realPath=path+f.getOriginalFilename();
			list.add(realPath);
			try {
			// 新建文件输入流并对它进行缓冲
			inBuff=new BufferedInputStream(f.getInputStream());
			// 新建文件输出流并对它进行缓冲
			outBuff=new BufferedOutputStream(new FileOutputStream(new File(realPath)));
			// 缓冲数组
			byte[] b=new byte[1024 * 5];
			int len;
			while((len=inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
			}
		}catch(Exception e) {
			e.printStackTrace();
			}finally {
				// 刷新此缓冲的输出流
				try {
					inBuff.close();
					outBuff.flush();
					outBuff.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
