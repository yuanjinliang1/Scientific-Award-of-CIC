package com.dicipulus.app.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.SixthApplyUnitSituationJdbc;
import com.dicipulus.app.JDBC.SixthEconomicAndSocialBenefitJdbc;
import com.dicipulus.app.JDBC.SixthPaperMonographNTJdbc;
import com.dicipulus.app.applicationModel.SixthApplyUnitSituation;
import com.dicipulus.app.applicationModel.SixthEconomicAndSocialBenefit;
import com.dicipulus.app.applicationModel.SixthPaperMonographNT;
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

public class SixthPdf {
	public static void buildSixthPdf(String applierUid,Document document) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		if(applicationType.equals("��Ȼ��ѧ��")) buildSixthPaperMonographPdf(applierUid,document);
		else buildSixthApplyUnitSituation(applierUid,document);
	}

	public static void buildSixthPaperMonographPdf(String applierUid,Document document) throws DocumentException, IOException{

		SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
		List<SixthPaperMonographNT> sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
		Iterator<SixthPaperMonographNT> it=sixthPaperMonographNT.iterator();
		
		while(it.hasNext()){
			String temp=it.next().getRepresentativePaperMonograph();
			if(temp.equals("��")) it.remove();
		}
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("��������ר��Ŀ¼",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1. ����������ר��Ŀ¼��������8ƪ��",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		float[] width1={0.08f,0.15f,0.08f,0.13f,0.08f,0.08f,0.08f,0.08f,0.08f,0.08f,0.08f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("���",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("����ר������/����/����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("Ӱ������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("���ҳ�루xx��xx��xxҳ��",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("����ʱ�� �� �� ��",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("ͨѶ����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("��һ����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("SCI��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("�����ܴ���",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("֪ʶ��Ȩ�Ƿ���������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		int i=0;
		while(i<8 && i<sixthPaperMonographNT.size()){
			cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
			cell.setMinimumHeight(50f);
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getName(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getInfluenceFactor(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getYearPage(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getPublishTime(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getCorrespondenceAuthor(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getFirstAuthor(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getDomesticAuthor(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(String.valueOf(sixthPaperMonographNT.get(i).getReferenceBySCI()),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(String.valueOf(sixthPaperMonographNT.get(i).getTotalReference()),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getIntellectualRightBelongToNation(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			i++;
		}
		if(i<7){
			for(;i<8;i++){
				
				cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
				cell.setMinimumHeight(50f);
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
		cell=new PdfPCell(new Phrase("�ϼ�",fontChinese));
		cell.setColspan(8);
		cell.setMinimumHeight(50f);
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
		document.add(table);
		paragraph=new Paragraph("����˵����������д����",subTitle2);
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("��",subTitle2);///
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("��ŵ����������ר�����ڱ����������������δ������Ŀ��Ҫ����˵����ߵ�ͬ�⡣֪ʶ��Ȩ��������У��Ҳ��������顣",subTitle2);
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("��һ�����ǩ����",subTitle2);
		paragraph.setFirstLineIndent(300f);
		document.add(paragraph);
		
		document.newPage();
		paragraph=new Paragraph("2.��Ҫ����ר��Ŀ¼��������20ƪ��������ȫ������������ר����",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		float[] width2={0.1f,0.2f,0.1f,0.15f,0.15f,0.1f,0.1f,0.1f};
		table=new PdfPTable(width2);
		table.setWidthPercentage(100);
		cell=new PdfPCell(new Phrase("���",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("����ר������/����/����",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("Ӱ������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("���ҳ�루xx��xx��xxҳ��",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("����ʱ��\n �� �� ��",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("SCI��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("�����ܴ���",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("֪ʶ��Ȩ�Ƿ���������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
		
		i=0;
		while(i<20 && i<sixthPaperMonographNT.size()){
			cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
			cell.setMinimumHeight(30f);
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getName(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getInfluenceFactor(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getYearPage(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getPublishTime(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(String.valueOf(sixthPaperMonographNT.get(i).getReferenceBySCI()),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(String.valueOf(sixthPaperMonographNT.get(i).getTotalReference()),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			cell=new PdfPCell(new Phrase(sixthPaperMonographNT.get(i).getIntellectualRightBelongToNation(),fontChinese));
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
	        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			table.addCell(cell);
			i++;
		}
		if(i<19){
			for(;i<20;i++){
				cell=new PdfPCell(new Phrase(String.valueOf(i+1),fontChinese));
				cell.setMinimumHeight(30f);
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
		cell=new PdfPCell(new Phrase("�ϼ�",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setColspan(5);
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
		document.add(table);
		document.newPage();
	
	}
	
	public static void buildSixthApplyUnitSituation(String applierUid,Document document) throws DocumentException, IOException{
		SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
		List<SixthApplyUnitSituation> sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getAllSixthApplyUnitSituation(applierUid);
		
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("�����ƹ�Ӧ�����������Ч������Ч��",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1. �ƹ�Ӧ�����",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.15f,0.1f,0.3f,0.3f,0.15f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("Ӧ�õ�λ����",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ӧ�ü���",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ӧ�õ���ֹʱ��",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ӧ�õ�λ��ϵ��/�绰",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ӧ�����",fontChinese));
		table.addCell(cell);
		for(SixthApplyUnitSituation sixth:sixthApplyUnitSituation){
			cell.setPhrase(new Phrase(sixth.getUnitName(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(sixth.getApplyTechnology(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(sixth.getStartDate(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(sixth.getContactAndPhoneNumber(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(sixth.getApplySituation(),fontChinese));
			table.addCell(cell);
		}
		document.add(table);
		document.newPage();
		
		paragraph=new Paragraph("2.�����꾭��Ч��",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
		SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=sixthEconomicAndSocialBenefitJdbc.getSixthEconomicAndSocialBenefit(applierUid);
		table=new PdfPTable(5);
		table.setWidthPercentage(100);
		cell.setRowspan(2);
		cell.setPhrase(new Phrase("��Ȼ��",fontChinese));
		table.addCell(cell);
		cell.setRowspan(1);
		cell.setColspan(2);
		cell.setPhrase(new Phrase("��ɵ�λ",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("����Ӧ�õ�λ",fontChinese) );
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("�������۶�",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("�������۶�",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getFirstYear(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSales1(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfit1(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit1(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit1(),fontChinese) );
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getSecondYear(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSales2(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfit2(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit2(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit2(),fontChinese) );
		table.addCell(cell);

		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getThirdYear(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSales3(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfit3(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit3(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit3(),fontChinese) );
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("�ۼ�",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getTotalOfAppendSales(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getTotalOfAppendProfit(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getTotalOfAppendSalesByOtherUnit(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase(sixthEconomicAndSocialBenefit.getTotalOfAppendProfitByOtherUnit(),fontChinese) );
		table.addCell(cell);
		
		cell.setColspan(5);
		cell.setHorizontalAlignment(cell.ALIGN_LEFT);
		cell.setPhrase(new Phrase("��Ҫ����Ч��ָ����й�˵����\n"+sixthEconomicAndSocialBenefit.getMainEconomicProfitIntroduction(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������Ч��ָ����й�˵����\n"+sixthEconomicAndSocialBenefit.getOtherEconomicProfitIntroduction(),fontChinese) );
		table.addCell(cell);
		document.add(table);
		document.newPage();
		
		paragraph=new Paragraph("3.���Ч��",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		paragraph=new Paragraph(sixthEconomicAndSocialBenefit.getSocialBenefit(),subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		paragraph.setFirstLineIndent(10f);
		document.add(paragraph);
		document.newPage();
	}
}