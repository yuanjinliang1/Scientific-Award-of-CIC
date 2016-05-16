package com.dicipulus.app.pdf;

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
		if(applicationType.equals("自然科学类")) buildSixthPaperMonographPdf(applierUid,document);
		else buildSixthApplyUnitSituation(applierUid,document);
	}

	public static void buildSixthPaperMonographPdf(String applierUid,Document document) throws DocumentException, IOException{

		SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
		List<SixthPaperMonographNT> sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
		Iterator<SixthPaperMonographNT> it=sixthPaperMonographNT.iterator();
		
		while(it.hasNext()){
			String temp=it.next().getRepresentativePaperMonograph();
			if(temp.equals("no")) it.remove();
		}
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font subTitle2 =  new  Font(baseFont  ,14, Font.NORMAL, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("六、论文专著目录",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1. 代表性论文专著目录（不超过8篇）",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		float[] width1={0.08f,0.15f,0.08f,0.13f,0.08f,0.08f,0.08f,0.08f,0.08f,0.08f,0.08f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("序号",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("论文专刊名称/刊名/作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("影响因子",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("年卷页码（xx年xx卷xx页）",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("发表时间 年 月 日",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("通讯作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("第一作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("国内作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("SCI他引次数",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("他引总次数",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("知识产权是否归国内所有",fontChinese));
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
		cell=new PdfPCell(new Phrase("合计",fontChinese));
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
		paragraph=new Paragraph("补充说明（视情填写）：",subTitle2);
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("空",subTitle2);///
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("承诺：上述论文专著用于报奖的情况，已征得未列入项目主要完成人的作者的同意。知识产权归国内所有，且不存在争议。",subTitle2);
		paragraph.setFirstLineIndent(30f);
		document.add(paragraph);
		paragraph=new Paragraph("第一完成人签名：",subTitle2);
		paragraph.setFirstLineIndent(300f);
		document.add(paragraph);
		
		document.newPage();
		paragraph=new Paragraph("2.主要论文专著目录（不超过20篇，含上述全部代表性论文专著）",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		float[] width2={0.1f,0.2f,0.1f,0.15f,0.15f,0.1f,0.1f,0.1f};
		table=new PdfPTable(width2);
		table.setWidthPercentage(100);
		cell=new PdfPCell(new Phrase("序号",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("论文专刊名称/刊名/作者",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("影响因子",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("年卷页码（xx年xx卷xx页）",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("发表时间\n 年 月 日",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("SCI他引次数",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("他引总次数",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell=new PdfPCell(new Phrase("知识产权是否归国内所有",fontChinese));
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
		cell=new PdfPCell(new Phrase("合计",fontChinese));
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
		Paragraph paragraph=new Paragraph("六、推广应用情况、经济效益和社会效益",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1. 推广应用情况",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		float[] width1={0.15f,0.1f,0.3f,0.3f,0.15f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("应用单位名称",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase("应用技术",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("应用的起止时间",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("应用单位联系人/电话",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("应用情况",fontChinese));
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
		
		paragraph=new Paragraph("2.近三年经济效益",subTitle2);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
		
		SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
		SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=sixthEconomicAndSocialBenefitJdbc.getSixthEconomicAndSocialBenefit(applierUid);
		table=new PdfPTable(5);
		table.setWidthPercentage(100);
		cell.setRowspan(2);
		cell.setPhrase(new Phrase("自然年",fontChinese));
		table.addCell(cell);
		cell.setRowspan(1);
		cell.setColspan(2);
		cell.setPhrase(new Phrase("完成单位",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("其他应用单位",fontChinese) );
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("新增销售额",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("新增利润",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("新增销售额",fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("新增利润",fontChinese) );
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
		
		cell.setPhrase(new Phrase("累计",fontChinese) );
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
		cell.setPhrase(new Phrase("主要经济效益指标的有关说明：\n"+sixthEconomicAndSocialBenefit.getMainEconomicProfitIntroduction(),fontChinese) );
		table.addCell(cell);
		cell.setPhrase(new Phrase("其他经济效益指标的有关说明：\n"+sixthEconomicAndSocialBenefit.getOtherEconomicProfitIntroduction(),fontChinese) );
		table.addCell(cell);
		document.add(table);
		document.newPage();
		
		paragraph=new Paragraph("3.社会效益",subTitle2);
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
