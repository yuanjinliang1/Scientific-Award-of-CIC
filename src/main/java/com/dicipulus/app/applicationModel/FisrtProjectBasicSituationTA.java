package com.dicipulus.app.applicationModel;

import java.util.Date;
import java.util.List;

import com.dicipulus.app.model.*;

/*
 * �Ƽ��������� һ����Ŀ�������
 */

public class FisrtProjectBasicSituationTA {

	protected String projectId;// ��ĿID

	/*
	 * �������
	 */
	protected Referee referee;// �Ƽ���λ���Ƽ�ר��
	protected String projectName;// ��Ŀ����
	protected List<MajorContributor> majorContributors;// ��Ҫ�����
	protected List<MajorContributingOrg> majorContributingOrg;// ��Ҫ��ɵ�λ
	protected String secretLevel = "����";// ��Ŀ�ܼ�
	// protected List<String> subjectCategoryNames;
	protected List<String> subjectCategoryIds;// ѧ�Ʒ��ֻࣨ����룬������jsp�����ɣ�
	protected String economicField;// �������񾭼���ҵ
	protected String nationalFocusField;// ���������ص㷢չ����
	protected String taskSource;// ������Դ
	protected String NameAndCodeOfPlansOrFundations;// ����ƻ�����������ƺͱ��
	protected String technicalReportNumber;// �ѳʽ��ĿƼ�������
	protected int numOfInventionPatent;// ��Ȩ����ר������
	protected int numOfOtherIntellectualProperty;// ��Ȩ������֪ʶ��Ȩ����
	protected Date startDate;// ��Ŀ��ʼʱ��
	protected Date finishDate;// ��Ŀ���ʱ��
	protected RefereeContactPerson refereeContactPerson;// �Ƽ���λ��ϵ�ˣ������������绰�������
	protected ApplierContactPerson applierContactPerson;// ��Ŀ��ϵ�ˣ������������绰�������

}