package com.notejava.module.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@At("admin/file")
public class FileUploadModule {
	/**
	 * 上传头像并返回头像图片名称
	 * @param tfs
	 * @return
	 */
	@At("/upload")
	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:fileUpload" })
	public Object upload(@Param("userImage") TempFile[] tfs){
		if(tfs.length==0){
			throw new RuntimeException("文件为空");
		}
		InputStream in = null;
		OutputStream out = null;
		String fileName = null;
		try {
			in = tfs[0].getInputStream();
			String uploadUrl = Mvcs.getServletContext().getRealPath("/")+File.separator+"static"+File.separator+"userImages";
			fileName = "userImage_"+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")+".jpg";
			out = new FileOutputStream(new File(uploadUrl+File.separator+fileName));
			byte[] byteArray = new byte[1024];
			while(in.read(byteArray)!=-1){
				out.write(byteArray, 0, byteArray.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return fileName;
	}
}
