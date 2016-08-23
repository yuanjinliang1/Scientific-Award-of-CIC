package com.dicipulus.app.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.SecondRefereeUnitOpinionJdbc;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SecondRefereeUnitOpinionPdf {
	public static void buildSecondRefereeUnitOpinionPdf(String applierUid,Document document) throws DocumentException, IOException{
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
		SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
//		Document document=new Document(PageSize.A4,50,50,50,50);
//		PdfWriter.getInstance(document, new FileOutputStream("/Users/cyq/Desktop/PDFtest.pdf"));
//		document.open();
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK); 
		//Font other =  new  Font(baseFont  ,  15 , Font.BOLD, BaseColor.BLACK); 
		
		Paragraph paragraph=new Paragraph("�����Ƽ���λ���",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("(ר���Ƽ��������)",fontChinese);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		
		float[] width1={0.2f,0.8f};
		PdfPTable table=new PdfPTable(width1);
		table.setSpacingBefore(10f);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("�Ƽ���λ",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getRefereeUnitName(),fontChinese));//
		table.addCell(cell);
		document.add(table);
		
		float[] width2={0.2f,0.4f,0.2f,0.2f};
		table=new PdfPTable(width2);
		table.setWidthPercentage(100);
		cell=new PdfPCell(new Phrase("ͨѶ��ַ",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getPostAddress(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getZipCode(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("��ϵ��",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getContact(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("��ϵ�绰",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getPhoneNumber(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getEmail(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getFax(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
		document.add(table);
		
		table=new PdfPTable(1);
		table.setWidthPercentage(100);
		cell=new PdfPCell();
		cell.setMinimumHeight(400f);
		cell.addElement(new Phrase("�Ƽ������",fontChinese));
		cell.addElement(new Paragraph(secondRefereeUnitOpinion.getRecommendOpinion(),fontChinese));///
		cell.addElement(new Paragraph("\n\n\n\n�Ƽ�����ĿΪ�й�ͨ��ѧ�Ἴ����"+secondRefereeUnitOpinion.getReferingScienceTechnologyAwardRank(),fontChinese));///
		table.addCell(cell);
		document.add(table);
		table=new PdfPTable(1);
		table.setWidthPercentage(100);
		cell=new PdfPCell();
		cell.setMinimumHeight(150f);
		Paragraph p=new Paragraph("           ����������λ���ع����йز��ż��й�ͨ��ѧ����ڿƼ���������ع涨�Ͷ��Ƽ������ľ���Ҫ�󣬳�ŵ�������������ɣ����ṩ���Ƽ�������ʵ��Ч���Ҳ������κ�Υ�����л����񹲺͹����ع������ܷ����͡���ѧ�������ܹ涨������ط��ɷ��漰�ַ�����֪ʶ��Ȩ�����Ρ����в�����ٻ�Υ����Ϊ��Ը��е���Ӧ���β�������Ӧ������������飬��֤�������鴦��",fontChinese);
		cell.addElement(p);
		cell.addElement(new Phrase("        ���˴���ǩ����                                "+"�Ƽ���λ�����£���",fontChinese));
		cell.addElement(new Phrase("                   ��    ��    ��                                   "+"    ��    ��    ��",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
//		document.close();
	
	}
}
