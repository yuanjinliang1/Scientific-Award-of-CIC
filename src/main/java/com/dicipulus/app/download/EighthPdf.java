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
//δ���list�е�����
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
		Paragraph paragraph=new Paragraph("�ˡ���Ҫ��������",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
	for(int i=0;i<eighthMajorContributor.size();i++){
		float[] width1={0.14f,0.14f,0.08f,0.08f,0.14f,0.14f,0.14f,0.14f};
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		PdfPCell cell=new PdfPCell(new Phrase("����",fontChinese));
		cell.setMinimumHeight(30f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(eighthMajorContributor.get(i).getNameOfContributor(),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("�Ա�",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(eighthMajorContributor.get(i).getGenderOfContributor(),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("����",fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase(String.valueOf(eighthMajorContributor.get(i).getRankOfContributor()),fontChinese));///
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
        table.addCell(cell);
        cell=new PdfPCell(new Phrase("����",fontChinese));///
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
		cell.setPhrase(new Phrase("�й�ͨ��ѧ���Ա",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getIsMemberOfCIC(),fontChinese));

		table.addCell(cell);
		cell.setPhrase(new Phrase("��Ա֤��",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMemberIdOfCIc(),fontChinese));
		cell.setColspan(3);
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setMinimumHeight(30f);
		cell.setPhrase(new Phrase("��������",fontChinese));
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getBirthdayOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("������",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getBirthPlaceOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("����",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getNationOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("���֤��",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getCitizenIdOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("�����Ա",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getIsReturnedFormOverseas(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ʱ��",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getReturnDate(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("����ְ��",fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getTechnicalTitleOfContributor(),fontChinese));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ѧ��",fontChinese));;
		
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getHighestEducationOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ѧλ",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getHighestDegreeOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��ҵѧУ",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getUniversityOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��ҵʱ��",fontChinese));;
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getGraduateDateOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��ѧרҵ",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMajorOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getEmailOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("�칫�绰",fontChinese));;
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getOfficePhoneOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("�ƶ��绰",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getMobileOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ͨѶ��ַ",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getAddressOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("��������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getZipCodeOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("������λ",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getWorkUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("����ְ��",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getAdministrativePositionOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("������λ",fontChinese));
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getSubWorkUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getPolicitalPartyOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��ɵ�λ",fontChinese));
		cell.setRowspan(2);
		table.addCell(cell);
		cell.setColspan(3);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getCompleteUnitOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ڵ�",fontChinese));
		cell.setRowspan(1);
		cell.setColspan(1);
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getLocationOfContributor(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��λ����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(eighthMajorContributor.get(i).getTypeOfUnit(),fontChinese));
		table.addCell(cell);
		document.add(table);
		float[] width3={0.3f,0.7f};
		table=new PdfPTable(width3);
		table.setWidthPercentage(100);
		cell.setMinimumHeight(30f);
		cell.setPhrase(new Phrase("�μӱ���Ŀ����ֹʱ��",fontChinese));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(String.valueOf(eighthMajorContributor.get(i).getStartDateOfParticipation())+" �� "+String.valueOf(eighthMajorContributor.get(i).getEndDateOfParticipation()),fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setColspan(2);
		cell.setMinimumHeight(180f);
		cell.addElement(new Paragraph("�Ա���Ŀ��Ҫѧ�����ף�",fontChinese));
		cell.addElement(new Paragraph(eighthMajorContributor.get(i).getContributionOfContributor(),fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.setMinimumHeight(110f);
		cell.setColspan(2);
		cell.addElement(new Paragraph("�����й�ͨ��ѧ��Ƽ����������",fontChinese));
		cell.addElement(new Paragraph(eighthMajorContributor.get(i).getFormerRewardOfCIC(),fontChinese));
		table.addCell(cell);
		document.add(table);
		
		float[] width4={0.6f,0.4f};
		table=new PdfPTable(width4);
		table.setWidthPercentage(100);
		cell=new PdfPCell();
		cell.addElement(new Paragraph("          ����������ͬ�����������,���ع����йز��ż��й�ͨ��ѧ����ڿƼ�������ع涨�Ͷ��Ƽ������ľ���Ҫ�󣬳�ŵ�������������ɣ���֤���ṩ���йز�����ʵ��Ч���Ҳ������κ�Υ�����л����񹲺͹����ع������ܷ����͡���ѧ�������ܹ涨������ط��ɷ��漰�ַ�����֪ʶ��Ȩ�����Ρ����в�����ٻ�Υ����Ϊ��Ը��е���Ӧ���β�������Ӧ������������飬��֤������ϵ��鴦������\n",fontChinese));
		cell.addElement(new Paragraph("\n                    ����ǩ��",fontChinese));
		cell.addElement(new Paragraph("                                                                     ��     ��     ��",fontChinese));
		table.addCell(cell);
		cell=new PdfPCell();
		cell.addElement(new Paragraph("          ��ɵ�λ����������λȷ�ϸ�����������������ʵ��Ч���Ҳ������κ�Υ�����л����񹲺͹����ع������ܷ����͡���ѧ�������ܹ涨������ط��ɷ��漰�ַ�����֪ʶ��Ȩ�����Ρ���������飬Ը�������ϵ��鴦������",fontChinese));
		cell.addElement(new Paragraph("          ������λ����������λ�Ը�����˱��������顣",fontChinese));
		cell.addElement(new Paragraph("               ��λ�����£�",fontChinese));
		cell.addElement(new Paragraph("                                                ��     ��     ��",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
		}
	
	}
}
