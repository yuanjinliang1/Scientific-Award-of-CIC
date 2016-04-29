package com.dicipulus.app.applicationModel;

import java.util.Date;
import java.util.List;

import com.dicipulus.app.model.*;

/*
 * 科技进步奖， 一、项目基本情况
 */

public class FisrtProjectBasicSituationTA {

	protected String projectId;// 项目ID

	/*
	 * 表的内容
	 */
	protected Referee referee;// 推荐单位或推荐专家
	protected String projectName;// 项目名称
	protected List<MajorContributor> majorContributors;// 主要完成人
	protected List<MajorContributingOrg> majorContributingOrg;// 主要完成单位
	protected String secretLevel = "非密";// 项目密级
	// protected List<String> subjectCategoryNames;
	protected List<String> subjectCategoryIds;// 学科分类（只存代码，名称在jsp中生成）
	protected String economicField;// 所属国民经济行业
	protected String nationalFocusField;// 所属国家重点发展领域
	protected String taskSource;// 任务来源
	protected String NameAndCodeOfPlansOrFundations;// 具体计划、基金的名称和编号
	protected String technicalReportNumber;// 已呈交的科技报告编号
	protected int numOfInventionPatent;// 授权发明专利项数
	protected int numOfOtherIntellectualProperty;// 授权的其它知识产权项数
	protected Date startDate;// 项目开始时间
	protected Date finishDate;// 项目完成时间
	protected RefereeContactPerson refereeContactPerson;// 推荐单位联系人，包括姓名、电话、邮箱等
	protected ApplierContactPerson applierContactPerson;// 项目联系人，包括姓名、电话。邮箱等

}