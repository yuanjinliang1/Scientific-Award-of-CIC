package com.dicipulus.app.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
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
//未完成list中的内容
public class EighthPdf {
	public static void buildEighthPdf(String applierUid,Document document) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
			
		EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
		List<EighthMajorContributor> eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributors(applierUid);	
		Collections.sort(eighthMajorContributor,new Comparator<EighthMajorContributor>(){
			public int compare(EighthMajorContributor eighthMajorContributor1, EighthMajorContributor eighthMajorContributor2){
				return eighthMajorContributor1.getRankOfContributor()-(eighthMajorContributor2.getRankOfContributor());
			}
		});
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("八、主要完成人情况",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
	for(int i=0;i<eighthMajorContributor.size();i++){
		float[] width1={0.14f,0.14f,0.08f,0.08f,0.14f,0.14f,0.14f,0.14f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("姓名",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(eighthMajorContributor.get(i).getNameOfContributor(),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("性别",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(eighthMajorContributor.get(i).getGenderOfContributor(),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("排名",fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(String.valueOf(eighthMajorContributor.get(i).getRankOfContributor()),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("国籍",fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(eighthMajorContributor.get(i).getNationalityOfContributor(),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
        document.add(table);
        
        float[] width2={0.14f,0.3f,0.14f,0.14f,0.14f,0.14f};
		table=new PdfPTable(width2);
		table.setWidthPercentage(100);
		cell.setMinimumHeight(60f);
		cell.setPhrase(new Phrase("中国通信学会会员",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getIsMemberOfCIC(),fontChinese));

		table.addCell(cell);
		cell.setPhrase(new Phrase("会员证号",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMemberIdOfCIc(),fontChinese));
		cell.setColspan(3);
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setMinimumHeight(30f);
		cell.setPhrase(new Phrase("出生年月",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getBirthdayOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("出生地",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getBirthPlaceOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("民族",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getNationOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("身份证号",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getCitizenIdOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("归国人员",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getIsReturnedFormOverseas(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("归国时间",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getReturnDate(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("技术职称",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getTechnicalTitleOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("最高学历",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getHighestEducationOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("最高学位",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getHighestDegreeOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("毕业学校",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getUniversityOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("毕业时间",fontChinese));;
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getGraduateDateOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("所学专业",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMajorOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("电子邮箱",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getEmailOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("办公电话",fontChinese));;
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getOfficePhoneOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("移动电话",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMobileOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("通讯地址",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getAddressOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("邮政编码",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getZipCodeOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("工作单位",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getWorkUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("行政职务",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getAdministrativePositionOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("二级单位",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getSubWorkUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("党派",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getPolicitalPartyOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("完成单位",fontChinese));
		cell.setRowspan(2);
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getCompleteUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("所在地",fontChinese));
		cell.setRowspan(1);
		cell.setColspan(1);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getLocationOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("单位性质",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getTypeOfUnit(),fontChinese));
		table.addCell(cell);
		document.add(table);
		float[] width3={0.3f,0.7f};
		table=new PdfPTable(width3);
		table.setWidthPercentage(100);
		cell.setMinimumHeight(30f);
		cell.setPhrase(new Phrase("参加本项目的起止时间",fontChinese));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(String.valueOf(eighthMajorContributor.get(i).getStartDateOfParticipation())+" 至 "+String.valueOf(eighthMajorContributor.get(i).getEndDateOfParticipation()),fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setColspan(2);
		cell.setMinimumHeight(180f);
		cell.addElement(new Paragraph("对本项目主要学术贡献：",fontChinese));
		cell.addElement(new Paragraph(eighthMajorContributor.get(i).getContributionOfContributor(),fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setMinimumHeight(110f);
		cell.setColspan(2);
		cell.addElement(new Paragraph("曾获中国通信学会科技奖励情况：",fontChinese));
		cell.addElement(new Paragraph(eighthMajorContributor.get(i).getFormerRewardOfCIC(),fontChinese));
		table.addCell(cell);
		document.add(table);
		
		float[] width4={0.6f,0.4f};
		table=new PdfPTable(width4);
		table.setWidthPercentage(100);
		cell=new PdfPCell();
		cell.addElement(new Paragraph("          声明：本人同意完成人排名,遵守国家有关部门及中国通信学会关于科技奖励相关规定和对推荐工作的具体要求，承诺遵守评审工作纪律，保证所提供的有关材料真实有效，且不存在任何违反《中华人民共和国保守国家秘密法》和《科学技术保密规定》等相关法律法规及侵犯他人知识产权的情形。如有材料虚假或违纪行为，愿意承担相应责任并接受相应处理。如产生争议，保证积极配合调查处理工作。\n",fontChinese));
		cell.addElement(new Paragraph("\n                    本人签名",fontChinese));
		cell.addElement(new Paragraph("                                                                     年     月     日",fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.addElement(new Paragraph("          完成单位声明：本单位确认该完成人情况表内容真实有效，且不存在任何违反《中华人民共和国保守国家秘密法》和《科学技术保密规定》等相关法律法规及侵犯他人知识产权的情形。如产生争议，愿意积极配合调查处理工作。",fontChinese));
		cell.addElement(new Paragraph("          工作单位声明：本单位对该完成人报奖无异议。",fontChinese));
		cell.addElement(new Paragraph("               单位（盖章）",fontChinese));
		cell.addElement(new Paragraph("                                                年     月     日",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
		}
	
	}
}
