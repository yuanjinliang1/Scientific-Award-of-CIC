package com.dicipulus.app.fileController;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
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
	
	@RequestMapping(value="/download-excel/{year}",produces = "application/vnd.ms-excel",method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  downloadExcel(HttpServletRequest request, @PathVariable("year") int year) throws DocumentException, IOException, RowsExceededException, WriteException{
		logger.info("downloadExcel");
		CombineExcel.buildExcel(year);
		String pathOfExcel=MyProperties.getRootPath()+"/admin/"+year+".pdf";
		File fileOfExcel = new File(pathOfExcel);
		return new FileSystemResource(fileOfExcel);
	}
}
