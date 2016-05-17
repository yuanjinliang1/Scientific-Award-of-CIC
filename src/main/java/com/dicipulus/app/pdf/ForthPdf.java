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
	if(applicationType.equals("��Ȼ��ѧ��")){
		Paragraph paragraph=new Paragraph("�ġ���Ҫ��ѧ����",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.��Ҫ��ѧ���֣���5ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.�о������ԣ���1ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	}
	else if(applicationType.equals("�Ƽ�������")){

		Paragraph paragraph=new Paragraph("�ġ���Ҫ�Ƽ�����",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.��Ҫ�Ƽ����£���5ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.�Ƽ������ԣ���1ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
	}
	else if(applicationType.equals("����������")){
		Paragraph paragraph=new Paragraph("�ġ���Ҫ��ѧ����",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.��Ҫ������������5ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.���������ԣ���1ҳ��",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
		
	}

	
	}
}
