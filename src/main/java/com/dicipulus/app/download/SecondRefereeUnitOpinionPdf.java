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
		
		Paragraph paragraph=new Paragraph("二、推荐单位意见",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("(专家推荐不填此栏)",fontChinese);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		
		float[] width1={0.2f,0.8f};
		PdfPTable table=new PdfPTable(width1);
		table.setSpacingBefore(10f);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("推荐单位",fontChinese));
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
		cell=new PdfPCell(new Phrase("通讯地址",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getPostAddress(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("邮政编码",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getZipCode(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("联系人",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getContact(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("联系电话",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getPhoneNumber(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("电子邮箱",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(secondRefereeUnitOpinion.getEmail(),fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("传真",fontChinese));
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
		cell.addElement(new Phrase("推荐意见：",fontChinese));
		cell.addElement(new Paragraph(secondRefereeUnitOpinion.getRecommendOpinion(),fontChinese));///
		cell.addElement(new Paragraph("\n\n\n\n推荐该项目为中国通信学会技术奖"+secondRefereeUnitOpinion.getReferingScienceTechnologyAwardRank(),fontChinese));///
		table.addCell(cell);
		document.add(table);
		table=new PdfPTable(1);
		table.setWidthPercentage(100);
		cell=new PdfPCell();
		cell.setMinimumHeight(150f);
		Paragraph p=new Paragraph("           声明：本单位遵守国家有关部门及中国通信学会关于科技奖励的相关规定和对推荐工作的具体要求，承诺遵守评审工作纪律，所提供的推荐材料真实有效，且不存在任何违反《中华人民共和国保守国家秘密法》和《科学技术保密规定》等相关法律法规及侵犯他人知识产权的情形。如有材料虚假或违纪行为，愿意承担相应责任并接受相应处理。如产生争议，保证积极调查处理。",fontChinese);
		cell.addElement(p);
		cell.addElement(new Phrase("        法人代表签名：                                "+"推荐单位（盖章）：",fontChinese));
		cell.addElement(new Phrase("                   年    月    日                                   "+"    年    月    日",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
//		document.close();
	
	}
}
