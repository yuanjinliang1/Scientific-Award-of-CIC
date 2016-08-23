package com.dicipulus.app.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.FirstProjectBasicSituationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
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

public class FirstProjectBasicSituationPdf {
	public static void buildFirstProjectBasicSituationPdf(String applierUid,Document document) 
			throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		FirstProjectBasicSituation 	firstProjectBasicSituation=firstProjectBasicSituationJdbc.getFirstProjectBasicSituation(applierUid);

		EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
		List<EighthMajorContributor> eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributors(applierUid);	
		Collections.sort(eighthMajorContributor,new Comparator<EighthMajorContributor>(){
			public int compare(EighthMajorContributor eighthMajorContributor1, EighthMajorContributor eighthMajorContributor2){
				return eighthMajorContributor1.getRankOfContributor()-(eighthMajorContributor2.getRankOfContributor());
			}
		});
		
		NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		List<NinethMajorOrgContributor> ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(applierUid);
		Collections.sort(ninethMajorOrgContributor, new Comparator<NinethMajorOrgContributor>(){
			public int compare(NinethMajorOrgContributor ninethMajorOrgContributor1,NinethMajorOrgContributor ninethMajorOrgContributor2){
				return ninethMajorOrgContributor1.getRankOfOrg()-(ninethMajorOrgContributor2.getRankOfOrg());
			}
		});
		
	    BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
        Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK); 
        
        Font title =  new  Font(baseFont  ,18, Font.BOLD, BaseColor.BLACK); 
        Paragraph paragraph1=null;
        if(applicationType.equals("自然科学类")) paragraph1 = new Paragraph("中国通信学会科学技术奖（自然科学类）推荐书",title);
        else if(applicationType.equals("科技进步类")) paragraph1 = new Paragraph("中国通信学会科学技术奖（科技进步类）推荐书",title);
        else if(applicationType.equals("技术发明类")) paragraph1 = new Paragraph("中国通信学会科学技术奖（技术发明类）推荐书",title);
        paragraph1.setAlignment(Element.ALIGN_CENTER);//生成文本在中间
        document.add(paragraph1);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        paragraph1=new Paragraph("( "+year+"年份)",fontChinese); 
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingBefore(10f);
        document.add(paragraph1);
        Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
        paragraph1=new Paragraph("一、项目进本情况",subTitle); 
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingBefore(15f);
        paragraph1.setSpacingAfter(15f);
        document.add(paragraph1);

        float[] width0={0.30f,0.70f};
        PdfPTable table=new PdfPTable(width0);
        table.setSpacingBefore(15f);
        PdfPCell cell=new PdfPCell(new Phrase("推荐单位（盖章）\n或专家推荐",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(50f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getRefereeString(),fontChinese));
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("项目名称",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(50f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getProjectName(),fontChinese));
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("主要完成人",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(50f);
        table.addCell(cell);
        StringBuffer majorContributors=new StringBuffer();
        for(EighthMajorContributor Contributors:eighthMajorContributor){
        	majorContributors.append(",").append(Contributors.getNameOfContributor());
        }
        cell.setPhrase(new Phrase(majorContributors.toString().substring(1),fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_LEFT);
        table.addCell(cell);//
        table.setWidthPercentage(100);
       	
        if(applicationType.equals("技术发明类")||applicationType.equals("科技进步类")){
        	cell=new PdfPCell(new Phrase("主要完成单位",fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            cell.setMinimumHeight(50f);
            table.addCell(cell);
            StringBuffer majorOrgContributors=new StringBuffer();
            for(NinethMajorOrgContributor orgContributors:ninethMajorOrgContributor){
            	majorOrgContributors.append(",").append(orgContributors.getNameOfOrg());
            }
            cell.setHorizontalAlignment(cell.ALIGN_LEFT);
            cell.setPhrase(new Phrase(majorOrgContributors.toString().substring(1),fontChinese));
            table.addCell(cell);
            cell=new PdfPCell(new Phrase("项目密级",fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            cell.setMinimumHeight(25f);
            table.addCell(cell);
            cell.setPhrase(new Phrase(firstProjectBasicSituation.getSecretLevel(),fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            table.addCell(cell);//
            
        }
        document.add(table);
        float[] width1={0.25f,0.05f,0.4f,0.15f,0.15f};
        table=new PdfPTable(width1);
        cell=new PdfPCell((new Phrase("学科分类名称",fontChinese)));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setRowspan(3);
        
       
       
        table.addCell(cell);
        table.addCell("  1");
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getSubjectCategoryName1(),fontChinese)));//
        cell=new PdfPCell(new Phrase("代码",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getSubjectCategoryId1()));
        table.addCell(cell);
        table.addCell("  2");
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getSubjectCategoryName2(),fontChinese)));//
        cell=new PdfPCell(new Phrase("代码",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getSubjectCategoryId2()));
        table.addCell(cell);
        table.addCell("  3");
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getSubjectCategoryName3(),fontChinese)));//
        
        cell=new PdfPCell(new Phrase("代码",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getSubjectCategoryId3()));
        table.addCell(cell);
        table.setWidthPercentage(100);
        document.add(table);
        float[] width2={0.3f,0.7f};
        table=new PdfPTable(width2);
        if(applicationType.equals("自然科学类")){
        	cell=new PdfPCell(new Phrase("所属科学技术领域",fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            cell.setMinimumHeight(25f);
            table.addCell(cell);
            table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getTechnologicalField(),fontChinese)));//
        
        }
        else {
        	cell=new PdfPCell(new Phrase("所属国民经济行业",fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            cell.setMinimumHeight(25f);
            table.addCell(cell);
            table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getEconomicField(),fontChinese)));//
            cell=new PdfPCell(new Phrase("所属国家重点发展领域",fontChinese));
            cell.setHorizontalAlignment(cell.ALIGN_CENTER);
            cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
            cell.setMinimumHeight(25f);
            table.addCell(cell);
            table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getNationalFocusField(),fontChinese)));//
        }
        cell=new PdfPCell(new Phrase("任务来源1",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getTaskSource1(),fontChinese)));//
 
        cell=new PdfPCell(new Phrase("任务来源2",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getTaskSource2(),fontChinese)));//
        
        cell=new PdfPCell(new Phrase("任务来源3",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getTaskSource3(),fontChinese)));//
       
        table.setWidthPercentage(100);
        document.add(table);
        table=new PdfPTable(1);
        cell=new PdfPCell(new Phrase("具体计划、基金的名称和编号：\n"+firstProjectBasicSituation.getNameAndCodeOfPlansOrFundations(),fontChinese));
        cell.setMinimumHeight(130f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("已呈交的科技报告编号：\n"+firstProjectBasicSituation.getTechnicalReportNumber(),fontChinese));
        cell.setMinimumHeight(130f);
        table.addCell(cell);
        table.setWidthPercentage(100);
        document.add(table);
        if(applicationType.equals("技术发明类")){
        	float[] width4={0.2f,0.40f,0.2f,0.3f};
        	table=new PdfPTable(width4);
        	table.setWidthPercentage(100);
        	
        	cell.setMinimumHeight(25f);
        	cell.setPhrase(new Phrase("授权发明专利（项）",fontChinese));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(firstProjectBasicSituation.getNumOfInventionPatent()),fontChinese));  
        	table.addCell(cell);
        	cell.setPhrase(new Phrase("授权的其他知识产权（项）",fontChinese));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(firstProjectBasicSituation.getNumOfOtherIntellectualProperty()),fontChinese));  
        	table.addCell(cell);
        }
        float[] width3={0.2f,0.40f,0.40f};
        table=new PdfPTable(width3);
        cell=new PdfPCell(new Phrase("项目起止日期",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("起始：    "+String.valueOf(firstProjectBasicSituation.getStartDate()),fontChinese));
        //cell=new PdfPCell(new Phrase("起始：    "+DateFormat.getDateInstance().format(firstProjectBasicSituation.getStartDate()),fontChinese));
        
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("完成：    "+String.valueOf(firstProjectBasicSituation.getFinishDate()),fontChinese));
        //cell=new PdfPCell(new Phrase("完成：    "+DateFormat.getDateInstance().format(firstProjectBasicSituation.getFinishDate()),fontChinese));
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);//
        table.setWidthPercentage(100);
        document.add(table);
        float[] width4={0.18f,0.12f,0.1f,0.2f,0.1f,0.3f};
        table=new PdfPTable(width4);
        cell=new PdfPCell(new Phrase("推荐单位联系人",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        table.addCell(new PdfPCell(new Phrase(firstProjectBasicSituation.getRefereeContactName(),fontChinese)));//
        cell=new PdfPCell(new Phrase("电话",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        table.addCell(firstProjectBasicSituation.getRefereeContactPhone());//
        cell=new PdfPCell(new Phrase("邮箱",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        table.addCell(firstProjectBasicSituation.getRefereeContactEmail());//
        cell=new PdfPCell(new Phrase("项目联系人",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f);
        table.addCell(cell);
        cell.setPhrase(new Phrase(firstProjectBasicSituation.getApplierContactName(),fontChinese));
        table.addCell(cell);//
        cell=new PdfPCell(new Phrase("电话",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        table.addCell(firstProjectBasicSituation.getApplierContactPhone());//
        cell=new PdfPCell(new Phrase("邮箱",fontChinese));
        cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        table.addCell(firstProjectBasicSituation.getApplierContactEmail());//
        table.setWidthPercentage(100);
        document.add(table);
        paragraph1=new Paragraph("中国通信学会制",fontChinese);
        paragraph1.setSpacingBefore(15f);
        paragraph1.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph1);
        document.newPage();
	
	}
}
