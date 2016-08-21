package com.dicipulus.app.fileController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileSystemUtils;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FileMeta;
import com.dicipulus.app.formController.FormUlti;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.MyProperties;
import com.dicipulus.app.model.Person;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory
			.getLogger(UploadController.class);
	String rootPath = MyProperties.getRootPath();

	/**
	 * get the view of uploading page
	 */
	@RequestMapping(value = "/upload/{applierUid}/{index}", method = RequestMethod.GET)
	public ModelAndView uploadView(HttpServletRequest request,
			ModelAndView modelAndView, @PathVariable String applierUid,
			@PathVariable int index) {
		logger.info("uploadView");
		Person person = FormUlti.getPersonInRequest(request);
		if (person == null) {
			modelAndView.setViewName("redirect:/error?message=null-session");
			return modelAndView;
		}
		if (person.getUid().equals(applierUid) == false) {
			modelAndView
					.setViewName("redirect:/error?message=no-authentication");
			return modelAndView;
		}

		ApplierJdbc applierJdbc = InitJdbc.initApplierJdbc();
		Applier applier = applierJdbc.getApplierByUid(applierUid);
		modelAndView.addObject("applier", applier);
		modelAndView.addObject("index", index);
		modelAndView.setViewName("uploadFiles");
		return modelAndView;
	}
	/**
	 * post the uploaded files to server
	 */
	@RequestMapping(value = "/upload/{applierUid}/{index}", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(
			MultipartHttpServletRequest request, HttpServletResponse response,
			@PathVariable String applierUid, @PathVariable int index) throws IOException {
		logger.info("upload");
		InitUploadFolder initUploadFolder=new InitUploadFolder();
		initUploadFolder.initUploadFolder(applierUid);
		String pathNow = rootPath + applierUid + "/uploaded/" + index;
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());

				// copy file to local disk (make sure the path
				// "e.g. D:/temp/files" exists)
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(pathNow
						+ "/" + mpf.getOriginalFilename()));

			} catch (IOException e) {
				e.printStackTrace();
			}
			// 2.4 add to files
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		
		File folder = new File(pathNow);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileMeta = new FileMeta();
				fileMeta.setFileName(listOfFiles[i].getName());
				fileMeta.setFileSize(listOfFiles[i].length()/1024 + " Kb");
				fileMeta.setFileType(Files.probeContentType(listOfFiles[i].toPath()));
				files.add(fileMeta);
			} else if (listOfFiles[i].isDirectory()) {
				// do nothing
			}
		}
		logger.info(files.toString());
		return files;
	}
	
	/**
	 * post the attached picture to server
	 */
	@RequestMapping(value = "/attached/{applierUid}/{index}", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody String attach(
			MultipartHttpServletRequest request, HttpServletResponse response,
			@PathVariable String applierUid, @PathVariable int index) throws IOException {
		logger.info("attach");
		InitUploadFolder initUploadFolder=new InitUploadFolder();
		initUploadFolder.initUploadFolder(applierUid);
		initUploadFolder.initAttachedFolder(applierUid);
		String pathNow = rootPath + applierUid + "/attached/" + index;
		String fileName;
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			fileName=mpf.getOriginalFilename();
			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());

				// copy file to local disk (make sure the path
				// "e.g. D:/temp/files" exists)
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(pathNow
						+ "/" + mpf.getOriginalFilename()));

			} catch (IOException e) {
				e.printStackTrace();
			}
			// 2.4 add to files
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		
		File folder = new File(pathNow);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileMeta = new FileMeta();
				fileMeta.setFileName(listOfFiles[i].getName());
				fileMeta.setFileSize(listOfFiles[i].length()/1024 + " Kb");
				fileMeta.setFileType(Files.probeContentType(listOfFiles[i].toPath()));
				files.add(fileMeta);
			} else if (listOfFiles[i].isDirectory()) {
				// do nothing
			}
		}
		logger.info(files.toString());
		return "http://localhost:8080/app/attached/"+applierUid+"/"+index+"/"+fileName;
	}
	
	/**
	 * return the uploaded file list to viewer
	 */
	@RequestMapping(value = "/upload-check/{applierUid}/{index}", method = RequestMethod.GET)
	public @ResponseBody LinkedList<FileMeta> uploadCheck( HttpServletResponse response,
			@PathVariable String applierUid, @PathVariable int index) throws IOException {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		InitUploadFolder initUploadFolder=new InitUploadFolder();
		initUploadFolder.initUploadFolder(applierUid);
		String pathNow = rootPath + applierUid + "/uploaded/" + index;
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		
		File folder = new File(pathNow);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileMeta = new FileMeta();
				fileMeta.setFileName(listOfFiles[i].getName());
				fileMeta.setFileSize(listOfFiles[i].length() / 1024 + " Kb");
				fileMeta.setFileType(Files.probeContentType(listOfFiles[i].toPath()));
				files.add(fileMeta);
			} else if (listOfFiles[i].isDirectory()) {
				// do nothing
			}
		}
		logger.info(files.toString());
		return files;
	}
	private LinkedList<FileMeta> getFileList(String applierUid,int index) throws IOException{
		InitUploadFolder initUploadFolder=new InitUploadFolder();
		initUploadFolder.initUploadFolder(applierUid);
		String pathNow = rootPath + applierUid + "/uploaded/" + index;
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		
		File folder = new File(pathNow);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileMeta = new FileMeta();
				fileMeta.setFileName(listOfFiles[i].getName());
				fileMeta.setFileSize(listOfFiles[i].length() / 1024 + " Kb");
				fileMeta.setFileType(Files.probeContentType(listOfFiles[i].toPath()));
				files.add(fileMeta);
			} else if (listOfFiles[i].isDirectory()) {
				// do nothing
			}
		}
		logger.info(files.toString());
		return files;
	}
	
	/**
	 * delete all the files in this page
	 */
	@RequestMapping(value = "/delete/{applierUid}/{index}", method = RequestMethod.GET)
	public String deleteFile(HttpServletResponse response,
			@PathVariable String applierUid, @PathVariable int index) {
		logger.info("deleteAllFile");
		String pathNow = rootPath + applierUid + "/uploaded/" + index;
		DelteFiles.deleteFiles(pathNow);
		String uploadURL="redirect:/upload/"+applierUid+"/"+index;
		return uploadURL;
	}
	
	/**
	 * delete one of the file in this page
	 */
	@RequestMapping(value = "/delete/{applierUid}/{index}/{fileLead}", method = RequestMethod.GET)
	public @ResponseBody LinkedList<FileMeta> deleteSingleFile(HttpServletResponse response,
			@PathVariable String applierUid, @PathVariable int index,@PathVariable int fileLead) throws IOException {
		logger.info("deleteSingleFile");
		String pathNow = rootPath + applierUid + "/uploaded/" + index;
		DelteFiles.deleteSingleFiles(pathNow,fileLead);
		String uploadURL="redirect:/upload/"+applierUid+"/"+index;
		return getFileList(applierUid,index);
	}
	

}
