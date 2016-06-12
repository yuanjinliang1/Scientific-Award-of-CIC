package com.dicipulus.app.download;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.FirstProjectBasicSituationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.ManageExcelJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.Excel;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.model.MyProperties;

public class CombineExcel {
	public static void buildExcel(int year) throws IOException, RowsExceededException, WriteException{
		
		String pathOfExcel=MyProperties.getRootPath()+"/admin/"+year+".pdf";
		
		File folder = new File(MyProperties.getRootPath()+"/admin/");
		if(folder.exists()==false){
			folder.mkdirs();
		}
		
		
		ManageExcelJdbc manageExcelJdbc=InitJdbc.initManageExcelJdbc();
		List<Excel> manageExcelAll=manageExcelJdbc.getExcelByYear(year);
		List<Excel> manageExcel=new ArrayList<Excel>();
		System.out.println(manageExcel.size()+"!!!");
		for(Excel ex:manageExcelAll){
			if(ex.getProjectStatus().equals("���Ƽ�")||ex.getProjectStatus().equals("�ѽ���")){
				manageExcel.add(ex);
			}
		}
		
		
		
		//��ͷ
		WritableWorkbook book=Workbook.createWorkbook(new File(pathOfExcel));
		try {//HACK ugly try-catch block, try to encapsulate it with a method and throw the exception gracefully
		WritableSheet sheet=book.createSheet("��ϸ��", 0);
		sheet.mergeCells(0, 0, 19, 0);
		Label label=new Label(0,0,year+"���й�ͨ��ѧ���ѧ�������ռ���ϸ��");
		WritableCellFormat format=new WritableCellFormat();
		format.setAlignment(jxl.format.Alignment.CENTRE);
		label.setCellFormat(format);
		sheet.addCell(label);
		label=new Label(0,1,"��ʽ���");
		sheet.addCell(label);
		label=new Label(1,1,"����");
		sheet.addCell(label);
		label=new Label(2,1,"����");
		sheet.addCell(label);
		label=new Label(3,1,"����");
		sheet.addCell(label);
		label=new Label(4,1,"��Ŀ����");
		sheet.addCell(label);
		label=new Label(5,1,"��Ҫ��ɵ�λ");
		sheet.addCell(label);
		label=new Label(6,1,"��һ��ɵ�λ");
		sheet.addCell(label);
		label=new Label(7,1,"�걨����");
		sheet.addCell(label);
		label=new Label(8,1,"�Ƽ���λ");
		sheet.addCell(label);
		label=new Label(9,1,"��Ҫ�����");
		sheet.addCell(label);
		label=new Label(10,1,"ѧ�ƴ���");
		sheet.addCell(label);
		label=new Label(11,1,"��Ŀ��ϵ��");
		sheet.addCell(label);
		label=new Label(12,1,"�绰");
		sheet.addCell(label);
		label=new Label(13,1,"Email");
		sheet.addCell(label);
		label=new Label(14,1,"��ַ");
		sheet.addCell(label);
		label=new Label(15,1,"�Ƽ���λ��ϵ��");
		sheet.addCell(label);
		label=new Label(16,1,"��ϵ��ʽ");
		sheet.addCell(label);
		label=new Label(17,1,"��ַ");
		sheet.addCell(label);
		label=new Label(18,1,"�ɹ��Ǽ�");
		sheet.addCell(label);
		label=new Label(19,1,"�Ƽ��ȼ�");
		sheet.addCell(label);
		label=new Label(20,1,"��ע");
		sheet.addCell(label);
		int row=2;
		for(Excel temp:manageExcel){
			label=new Label(0,row,temp.getFormalityExaminationResult());
			System.out.println(temp.getFormalityExaminationResult());
			sheet.addCell(label);
			label=new Label(1,row,temp.getPrimaryExaminationResult());
			System.out.println(temp.getPrimaryExaminationResult());
			sheet.addCell(label);
			label=new Label(2,row,temp.getFinalExaminationResult());
			System.out.println(temp.getFinalExaminationResult()+"FinalExamninationResult!!");
			sheet.addCell(label);
			label=new Label(3,row,"");
			sheet.addCell(label);
			label=new Label(4,row,temp.getProjectName());
			System.out.println(temp.getProjectName());
			sheet.addCell(label);
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			List<NinethMajorOrgContributor> ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(temp.getApplierUid());
			Collections.sort(ninethMajorOrgContributor, new Comparator<NinethMajorOrgContributor>(){
				public int compare(NinethMajorOrgContributor ninethMajorOrgContributor1,NinethMajorOrgContributor ninethMajorOrgContributor2){
					return ninethMajorOrgContributor1.getRankOfOrg()-(ninethMajorOrgContributor2.getRankOfOrg());
				}
			});
			String MajorOrgContributor="";
			for(NinethMajorOrgContributor org:ninethMajorOrgContributor){
				MajorOrgContributor=MajorOrgContributor+" "+org.getNameOfOrg();
			}
			label=new Label(5,row,MajorOrgContributor);
			sheet.addCell(label);
			label=new Label(6,row,ninethMajorOrgContributor.get(0).getNameOfOrg());
			sheet.addCell(label);
			label=new Label(7,row,temp.getApplicationType());
			System.out.println(temp.getApplicationType());
			sheet.addCell(label);
			label=new Label(8,row,temp.getRefereeString());
			System.out.println(temp.getRefereeString());
			sheet.addCell(label);
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			List<EighthMajorContributor> eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributors(temp.getApplierUid());
			Collections.sort(eighthMajorContributor,new Comparator<EighthMajorContributor>(){
				public int compare(EighthMajorContributor eighthMajorContributor1, EighthMajorContributor eighthMajorContributor2){
					return eighthMajorContributor1.getRankOfContributor().compareTo(eighthMajorContributor2.getRankOfContributor());
				}
			});
			String MajorContributor="";
			for(EighthMajorContributor major:eighthMajorContributor){
				MajorContributor=MajorContributor+" "+major.getNameOfContributor();
			}
			label=new Label(9,row,MajorContributor);
			sheet.addCell(label);
			label=new Label(10,row,"ѧ�ƴ���");
			sheet.addCell(label);
			label=new Label(11,row,temp.getApplierContactName());
			System.out.println(temp.getApplierContactName());
			sheet.addCell(label);
			label=new Label(12,row,temp.getApplierContactPhone());
			System.out.println(temp.getApplierContactPhone());
			sheet.addCell(label);
			label=new Label(13,row,temp.getApplierContactEmail());
			System.out.println(temp.getApplierContactEmail());
			sheet.addCell(label);
			label=new Label(14,row,temp.getAddressOfOrg());
			sheet.addCell(label);
			label=new Label(15,row,temp.getRefereeContactName());
			System.out.println(temp.getRefereeContactName());
			sheet.addCell(label);
			label=new Label(16,row,temp.getRefereeContactPhone());
			System.out.println(temp.getRefereeContactPhone());
			sheet.addCell(label);
			label=new Label(17,row,temp.getPostAddress());
			System.out.println(temp.getPostAddress());
			sheet.addCell(label);
			label=new Label(18,row,"");
			sheet.addCell(label);
			label=new Label(19,row,temp.getReferingScienceTechnologyAwardRank());
			System.out.println(temp.getReferingScienceTechnologyAwardRank());
			sheet.addCell(label);
			row++;
		}
		book.write();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//in order to prevent file-not-closing problems
			//HACK ugly try-catch block, try to encapsulate it with a method and throw the exception gracefully
			book.close();
		}
	}
}
