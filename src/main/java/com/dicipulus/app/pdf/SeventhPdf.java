package com.dicipulus.app.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.SeventhIntellectualPropertyDocJdbc;
import com.dicipulus.app.JDBC.SeventhPaperCitedByOthersJdbc;
import com.dicipulus.app.applicationModel.SeventhIntellectualPropertyDoc;
import com.dicipulus.app.applicationModel.SeventhPaperCitedByOthers;
import com.dicipulus.app.model.Applier;
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

public class SeventhPdf {
	public static void buildSeventhPdf(String applierUid,Document document) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		
		if(applicationType.equals("��Ȼ��ѧ��")) buildSeventhPaperCitedByOthers(applierUid, document);
		else buildSeventhIntellectualProperty(applierUid, document);
	}

	public static void buildSeventhIntellectualProperty(String applierUid,Document document) throws DocumentException, IOException{
		SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
		List<SeventhIntellectualPropertyDoc> seventhIntellectualPropertyDoc=seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(applierUid);
		
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("�ߡ���Ҫ֪ʶ��Ȩ֤��Ŀ¼��������10����",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.1f,0.15f,0.1f,0.15f,0.1f,0.15f,0.1f,0.1f,0.1f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(5f);
		PdfPCell cell=new PdfPCell(new Phrase("֪ʶ��Ȩ���",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase("֪ʶ��Ȩ��������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ң�������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��Ȩ��",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��Ȩ����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("֤����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ȩ����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("����ר����Ч״̬",fontChinese));
		table.addCell(cell);
		for(SeventhIntellectualPropertyDoc seven:seventhIntellectualPropertyDoc){
			cell.setPhrase(new Phrase(seven.getTypeOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getNameOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getLocationOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getAuthorizationCodeOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getAuthorizationDateOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getCertificateNumberOfIP(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(seven.getHolderOfIP(),fontChinese));
			table.addCell(cell);cell.setPhrase(new Phrase(seven.getInventorOfIP(),fontChinese));
			table.addCell(cell);cell.setPhrase(new Phrase(seven.getIsValidIP(),fontChinese));
			table.addCell(cell);
		}
		document.add(table);
		paragraph=new Paragraph("��ŵ������֪ʶ��Ȩ�����Ƽ������������������δ������Ŀ��Ҫ����˵�Ȩ���ˣ�����ר��ָ�����ˣ���ͬ�⡣",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		paragraph.setFirstLineIndent(10f);
		document.add(paragraph);
		paragraph=new Paragraph("��һ�����ǩ����",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setFirstLineIndent(100f);
		document.add(paragraph);
		document.newPage();
		
		
	}
	public static void buildSeventhPaperCitedByOthers(String applierUid,Document document) throws DocumentException, IOException{

		
		SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
		List<SeventhPaperCitedByOthers> seventhPaperCitedByOthers=seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(applierUid);
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("�ߡ�����������ר�����������õ������������8ƪ��",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.1f,0.15f,0.30f,0.25f,0.20f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(5f);
		PdfPCell cell=new PdfPCell(new Phrase("���",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("��������������ר�����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("������Ŀ/����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("���Ŀ���/Ӱ������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("���ķ���ʱ�䣨�� �� �գ�",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		int i=0;
		for(;i<seventhPaperCitedByOthers.size();i++){
			cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
			cell.setMinimumHeight(80f);
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(seventhPaperCitedByOthers.get(i).getDoiOfPaper(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(seventhPaperCitedByOthers.get(i).getTitleAndAuthorOfPaper(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(seventhPaperCitedByOthers.get(i).getJournalAndIF(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(DateFormat.getDateInstance().format(seventhPaperCitedByOthers.get(i).getPublishDate()),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
		}
		if(i<7){
			for(;i<8;i++){

				cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
				cell.setMinimumHeight(80f);
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
				table.addCell(cell);
				cell=new PdfPCell(new Phrase("",fontChinese));
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
				table.addCell(cell);
				cell=new PdfPCell(new Phrase("",fontChinese));
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
				table.addCell(cell);
				cell=new PdfPCell(new Phrase("",fontChinese));
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
				table.addCell(cell);
				cell=new PdfPCell(new Phrase("",fontChinese));
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
				table.addCell(cell);
			
			}
		}
		document.add(table);
		document.newPage();
	
	}
}
