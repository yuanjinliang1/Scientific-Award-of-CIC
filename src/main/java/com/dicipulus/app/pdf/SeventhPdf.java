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
		
		if(applicationType.equals("自然科学类")) buildSeventhPaperCitedByOthers(applierUid, document);
		else buildSeventhIntellectualProperty(applierUid, document);
	}

	public static void buildSeventhIntellectualProperty(String applierUid,Document document) throws DocumentException, IOException{
		SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
		List<SeventhIntellectualPropertyDoc> seventhIntellectualPropertyDoc=seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(applierUid);
		
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("七、主要知识产权证明目录（不超过10件）",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.1f,0.15f,0.1f,0.15f,0.1f,0.15f,0.1f,0.1f,0.1f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(5f);
		PdfPCell cell=new PdfPCell(new Phrase("知识产权类别",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase("知识产权具体名称",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("国家（地区）",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("授权号",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("授权日期",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("证书编号",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("权利人",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("发明人",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("发明专利有效状态",fontChinese));
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
		paragraph=new Paragraph("承诺：上述知识产权用于推荐报奖的情况，已征得未列入项目主要完成人的权利人（发明专利指发明人）的同意。",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		paragraph.setFirstLineIndent(10f);
		document.add(paragraph);
		paragraph=new Paragraph("第一完成人签名：",subTitle2);
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
		Paragraph paragraph=new Paragraph("七、代表性论文专著被他人引用的情况（不超过8篇）",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.1f,0.15f,0.30f,0.25f,0.20f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(5f);
		PdfPCell cell=new PdfPCell(new Phrase("序号",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("被引代表性论文专著序号",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("引文题目/作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("引文刊名/影响因子",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("引文发表时间（年 月 日）",fontChinese));
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
