package com.dicipulus.app.fileController;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dicipulus.app.download.CombineExcel;
import com.dicipulus.app.download.CombinePdf;
import com.dicipulus.app.model.MyProperties;
import com.itextpdf.text.DocumentException;

@Controller
@SessionAttributes("person")
public class DownloadPdf {
	private static final Logger logger = LoggerFactory.getLogger(DownloadPdf.class);
	
	@RequestMapping(value="/download-pdf/{applierUid}",produces = "application/pdf",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  downloadPdf(HttpServletRequest request, @PathVariable("applierUid") String applierUid) throws DocumentException, IOException{
		logger.info("downloadPdf");
		CombinePdf.buildPdf(applierUid);
		String pathOfPdf=MyProperties.getRootPath()+applierUid+"/pdf/"+applierUid+".pdf";
		File fileOfPdf = new File(pathOfPdf);
		return new FileSystemResource(fileOfPdf);
	}
	
	@RequestMapping(value="/download-instruction",produces = "application/pdf",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  downloadInstruction(HttpServletRequest request) throws DocumentException, IOException{
		logger.info("downloadPdf");

		
		String folderPathOfWord=MyProperties.getRootPath()+"system/";
		File dir = new File(folderPathOfWord);
		FileFilter fileFilter = new WildcardFileFilter("中国通信学会奖项申请系统操作手册.pdf");
		File[] files = dir.listFiles(fileFilter);
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(files[0].getName().replace(" ", "_"), "UTF-8"));
		return new FileSystemResource(files[0]);
	}
	
	@RequestMapping(value="/download-excel/{year}",produces = "application/vnd.ms-excel",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  downloadExcel(HttpServletRequest request, @PathVariable("year") int year) throws DocumentException, IOException, RowsExceededException, WriteException{
		logger.info("downloadExcel");
		CombineExcel.buildExcel(year);
		String pathOfExcel=MyProperties.getRootPath()+"/admin/"+year+".pdf";
		File fileOfExcel = new File(pathOfExcel);
		return new FileSystemResource(fileOfExcel);
	}
	
	@RequestMapping(value="/download-word/{index}",produces = "application/msword",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource>  downloadWord(HttpServletRequest request, @PathVariable("index") int index,HttpServletResponse response) throws UnsupportedEncodingException{
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		String folderPathOfWord=MyProperties.getRootPath()+"system/";
		File dir = new File(folderPathOfWord);
		FileFilter fileFilter = new WildcardFileFilter(index+"*.docx");
		File[] files = dir.listFiles(fileFilter);
		logger.info(folderPathOfWord+" size:"+files.length+" name:"+files[0].getName().replace(" ", "_"));
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(files[0].getName().replace(" ", "_"), "UTF-8"));
		return new  ResponseEntity<FileSystemResource>(new FileSystemResource(files[0]),responseHeaders,HttpStatus.OK);
	}
	
	@RequestMapping(value="/attached/{applierUid}/{index}/{fileName}.{extensions}",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  downloadAttached(HttpServletRequest request, @PathVariable String applierUid, 
			@PathVariable("index") int index,  @PathVariable String fileName, @PathVariable String extensions,HttpServletResponse response) throws UnsupportedEncodingException{
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		String folderPathOfAttached = MyProperties.getRootPath() + applierUid + "/attached/" + index;
		File dir = new File(folderPathOfAttached);
		FileFilter fileFilter = new WildcardFileFilter(fileName+"."+extensions);
		File[] files = dir.listFiles(fileFilter);

		//logger.info(folderPathOfAttached+", "+fileName+","+files.length);
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(files[0].getName().replace(" ", "_"), "UTF-8"));
		return new FileSystemResource(files[0]);
	}
	
}
