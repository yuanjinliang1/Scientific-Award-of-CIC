package com.dicipulus.app.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
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
/////������ ����ȡ�����¼����һ��
public class NinethMajorOrgContributorPdf {
	public static void buildNinethMajorOrgContributorPdf(String applierUid,Document document) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		
		NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		List<NinethMajorOrgContributor> ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(applierUid);
		Collections.sort(ninethMajorOrgContributor, new Comparator<NinethMajorOrgContributor>(){
			public int compare(NinethMajorOrgContributor ninethMajorOrgContributor1,NinethMajorOrgContributor ninethMajorOrgContributor2){
				return ninethMajorOrgContributor1.getRankOfOrg()-(ninethMajorOrgContributor2.getRankOfOrg());
			}
		});
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("�š���Ҫ��ɵ�λ�����",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
	for(int i=0;i<ninethMajorOrgContributor.size();i++){
		float[] width1={0.15f,0.25f,0.15f,0.15f,0.15f,0.15f}; 
		PdfPTable table=new PdfPTable(width1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(5f);
		PdfPCell cell=new PdfPCell();
		cell.setMinimumHeight(35f);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
        cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		cell.setPhrase(new Phrase("��λ����",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getNameOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(ninethMajorOrgContributor.get(i).getRankOfOrg()),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("����������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getLegalRepresentative(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("���ڵ�",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getLocationOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��λ����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getTypeOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("����",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getFaxOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getZipCodeOfOrg(),fontChinese));
		table.addCell(cell);
		if(!applicationType.equals("�Ƽ�������")){
			cell.setPhrase(new Phrase("�й�ͨ��ѧ�������Ա",fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getIsOrgMemberOfCIC(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase("�����Ա֤���",fontChinese));
			table.addCell(cell);
		}
		cell.setColspan(3);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getorgMemberIDOfCIC(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("ͨѶ��ַ",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getAddressOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("��ϵ��",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContactNameOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��λ�绰",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContactPhoneOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("�ƶ��绰",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getMobileOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("��������",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getEmailOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(6);
		cell.setHorizontalAlignment(cell.ALIGN_LEFT);
		cell.setPhrase(new Phrase("�Ա���Ŀ�Ĺ��ף�",fontChinese));
		table.addCell(cell);
		
		cell.setVerticalAlignment(cell.ALIGN_TOP);
		cell.setMinimumHeight(250f);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContributionToProject(),fontChinese));
		table.addCell(cell);
		cell.setMinimumHeight(80f);
		cell.addElement(new Paragraph("          ����������λͬ����ɵ�λ���������ع����йز��ż��й�ͨ��ѧ����ڿƼ���������ع涨�Ͷ��Ƽ������ľ���Ҫ�󣬳�ŵ�������������ɣ���֤���ṩ���йز�����ʵ��Ч���Ҳ������κ�Υ�����л����񹲺͹����ع������ܷ����͡���ѧ�������ܹ涨������ط��ɷ��漰�ַ�����֪ʶ��Ȩ�����Ρ����в�����ٻ�Υ����Ϊ��Ը��е���Ӧ���β�������Ӧ������������飬��֤������ϵ��鴦������",fontChinese));
		cell.addElement(new Paragraph("          ����������ǩ�֣�                                                 ��λ�����£�",fontChinese));
		cell.addElement(new Paragraph("                       ��     ��     ��                                                         ��      ��     ��",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
		}
	}


}
