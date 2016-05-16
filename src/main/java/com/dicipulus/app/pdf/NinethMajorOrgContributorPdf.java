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
/////有问题 关于取多个记录还是一个
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
		Paragraph paragraph=new Paragraph("九、主要完成单位情况表",subTitle);
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
		cell.setPhrase(new Phrase("单位名称",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getNameOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("排名",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(ninethMajorOrgContributor.get(i).getRankOfOrg()),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("法定代表人",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getLegalRepresentative(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("所在地",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getLocationOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("单位性质",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getTypeOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("传真",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getFaxOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("邮政编码",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getZipCodeOfOrg(),fontChinese));
		table.addCell(cell);
		if(!applicationType.equals("科技进步类")){
			cell.setPhrase(new Phrase("中国通信学会团体会员",fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getIsOrgMemberOfCIC(),fontChinese));
			table.addCell(cell);
			cell.setPhrase(new Phrase("团体会员证书号",fontChinese));
			table.addCell(cell);
		}
		cell.setColspan(3);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getorgMemberIDOfCIC(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("通讯地址",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getAddressOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("联系人",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContactNameOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("单位电话",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContactPhoneOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("移动电话",fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getMobileOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setPhrase(new Phrase("电子邮箱",fontChinese));
		table.addCell(cell);
		cell.setColspan(5);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getEmailOfOrg(),fontChinese));
		table.addCell(cell);
		cell.setColspan(6);
		cell.setHorizontalAlignment(cell.ALIGN_LEFT);
		cell.setPhrase(new Phrase("对本项目的贡献：",fontChinese));
		table.addCell(cell);
		
		cell.setVerticalAlignment(cell.ALIGN_TOP);
		cell.setMinimumHeight(250f);
		cell.setPhrase(new Phrase(ninethMajorOrgContributor.get(i).getContributionToProject(),fontChinese));
		table.addCell(cell);
		cell.setMinimumHeight(80f);
		cell.addElement(new Paragraph("          声明：本单位同意完成单位排名，遵守国家有关部门及中国通信学会关于科技奖励的相关规定和对推荐工作的具体要求，承诺遵守评审工作纪律，保证所提供的有关材料真实有效，且不存在任何违反《中华人民共和国保守国家秘密法》和《科学技术保密规定》等相关法律法规及侵犯他人知识产权的情形。如有材料虚假或违纪行为，愿意承担相应责任并接受相应处理。如产生争议，保证积极配合调查处理工作。",fontChinese));
		cell.addElement(new Paragraph("          法定代表人签字：                                                 单位（盖章）",fontChinese));
		cell.addElement(new Paragraph("                       年     月     日                                                         年      月     日",fontChinese));
		table.addCell(cell);
		document.add(table);
		document.newPage();
		}
	}


}
