package com.dicipulus.app.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.ThirdProjectBriefIntroductionJdbc;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ThirdProjectBriefIntroductionPdf {
	public static void buildThirdProjectBriefIntroductionPdf(String applierUid,Document document) throws DocumentException, IOException{
//		Document document=new Document(PageSize.A4,50,50,50,50);
//		PdfWriter.getInstance(document, new FileOutputStream("/Users/cyq/Desktop/PDFtest.pdf"));
//		document.open();
		ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
		ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=thirdProjectBriefIntroductionJdbc.getThirdProjectBriefIntroduction(applierUid);
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  14 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("二、项目简介",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("(限1200字)",fontChinese);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(thirdProjectBriefIntroduction.getBriefIntroduction(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
//		document.close();	
	}
}
