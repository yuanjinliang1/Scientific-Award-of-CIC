package com.dicipulus.app.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FourthFormJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.model.Applier;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ForthPdf {
	public static void buildForthPdf(String applierUid,Document document) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		
		FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
		FourthForm fourthForm=fourthFormJdbc.getFourthForm(applierUid);

		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  14 , Font.NORMAL, BaseColor.BLACK);
	if(applicationType.equals("自然科学类")){
		Paragraph paragraph=new Paragraph("四、重要科学发现",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.重要科学发现（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.研究局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	}
	else if(applicationType.equals("科技进步类")){

		Paragraph paragraph=new Paragraph("四、主要科技创新",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.重要科技创新（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.科技局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
	}
	else if(applicationType.equals("技术发明类")){
		Paragraph paragraph=new Paragraph("四、重要科学发现",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.主要技术发明（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.技术局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
		
	}

	
	}
}
