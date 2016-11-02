package com.dicipulus.app.fileController;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dicipulus.app.download.CombinePdf;
import com.dicipulus.app.model.MyProperties;
import com.itextpdf.text.DocumentException;

@Controller
@SessionAttributes("person")
public class DownloadZip {
	private static final Logger logger =LoggerFactory.getLogger(DownloadZip.class);
	
	private File compressZip(String source, String destination,String applierUid) throws ZipException{

		
		File sourceFile =new File(source);
		
		ZipFile zipFile =new ZipFile(destination+applierUid+".zip");
		ZipParameters parameters = new ZipParameters();
		 parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		 parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		 parameters.setIncludeRootFolder(true);
		zipFile.createZipFileFromFolder(sourceFile, parameters, false, 0);
		
		File destinationFile= new File(destination+applierUid+".zip");
		return destinationFile;
	}
	
	
	@RequestMapping(value="/download-zip/{applierUid}",produces="application/zip",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadZip(HttpServletRequest request, @PathVariable("applierUid") String applierUid) throws ZipException, StringIndexOutOfBoundsException, DocumentException, IOException{
		logger.info("downloadZip");
		String source =MyProperties.getRootPath()+applierUid+"/uploaded/";
		String destination= MyProperties.getRootPath()+applierUid+"/zip/";
		File destinationFile= new File(destination+applierUid+".zip");
		File destinationFolder = new File(destination);
		if(destinationFolder.exists()==false){
			destinationFolder.mkdirs();
		}
		if(destinationFile.exists()){
			//return new FileSystemResource(destinationFile);
			org.springframework.util.FileSystemUtils.deleteRecursively(destinationFile.getAbsoluteFile());
		}
		CombinePdf.buildPdf(applierUid);
		return new FileSystemResource(compressZip(source,destination,applierUid));
	}

}
