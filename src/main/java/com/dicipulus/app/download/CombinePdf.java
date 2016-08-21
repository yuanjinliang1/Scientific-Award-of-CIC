package com.dicipulus.app.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dicipulus.app.fileController.DownloadPdf;
import com.dicipulus.app.model.MyProperties;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class CombinePdf {
	private static final Logger logger = LoggerFactory.getLogger(CombinePdf.class);
	public static void buildPdf(String applierUid) throws DocumentException, IOException, StringIndexOutOfBoundsException{
		Document document=new Document(PageSize.A4,50,50,50,50);
		String pathOfPdf=MyProperties.getRootPath()+applierUid+"/pdf/"+applierUid+".pdf";
		
		File folder = new File(MyProperties.getRootPath()+applierUid+"/pdf/");
		if(folder.exists()==false){
			folder.mkdirs();
		}
		
		PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(pathOfPdf));
		document.open();
		try{
			FirstProjectBasicSituationPdf.buildFirstProjectBasicSituationPdf(applierUid, document);
			SecondRefereeUnitOpinionPdf.buildSecondRefereeUnitOpinionPdf(applierUid, document);
			ThirdProjectBriefIntroductionPdf.buildThirdProjectBriefIntroductionPdf(applierUid, document,writer);
			ForthPdf.buildForthPdf(applierUid, document,writer);
			FifthObjectEvaluationPdf.buildFifthObjectEvaluationPdf(applierUid, document);
			SixthPdf.buildSixthPdf(applierUid, document);
			SeventhPdf.buildSeventhPdf(applierUid, document);
			EighthPdf.buildEighthPdf(applierUid, document);
			NinethMajorOrgContributorPdf.buildNinethMajorOrgContributorPdf(applierUid, document);
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		finally {
			document.close();
		}
	}
}
