package com.dicipulus.app.fileController;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.model.MyProperties;

@Controller
public class InitUploadFolder {
	private static final Logger logger = LoggerFactory
			.getLogger(InitUploadFolder.class);
	String rootPath = MyProperties.getRootPath();

	@RequestMapping(value = "/upload/{applierUid}", method = RequestMethod.GET)
	public String initUploadFolder(HttpServletRequest request,
			@PathVariable String applierUid) {
		logger.info("initUpload");

		String applierPath = rootPath + applierUid + "/uploaded/";
		for (int i = 1; i <= 5; i++) {
			File file=new File(applierPath + i);
			if(file.exists()==false){
				logger.info(file.toString()+" "+file.mkdirs());
			}
		}
		String uploadURL="redirect:/upload/"+applierUid+"/1";
		return uploadURL;
	}
	
	public String initUploadFolder(String applierUid) {
		logger.info("initUpload");

		String applierPath = rootPath + applierUid + "/uploaded/";
		for (int i = 1; i <= 5; i++) {
			File file=new File(applierPath + i);
			if(file.exists()==false){
				logger.info(file.toString()+" "+file.mkdirs());
			}
		}
		String uploadURL="redirect:/upload/"+applierUid+"/1";
		return uploadURL;
	}
}
