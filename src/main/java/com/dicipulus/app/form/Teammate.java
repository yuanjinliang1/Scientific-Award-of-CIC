package com.dicipulus.app.form;

import com.dicipulus.app.form.Constants.Degree;
import com.dicipulus.app.form.Constants.EducationBackground;
import com.dicipulus.app.form.Constants.Gender;

/**
 * @author chenyouqi
 *完成人信息类
 */
public class Teammate {
	protected String name;
	protected Gender gender;
	protected int rank;
	protected String nationality;
	protected boolean isMemberOfCic;
	protected String certificationIdOfCic;
	protected String birthYearMonth;
	protected String birthPlace;
	protected String nation;//民族
	protected String identityCardId;
	protected boolean isBackState;
	protected String backTime;
	protected String technicalTitle;//技术职称
	protected EducationBackground educationBackGround;
	protected Degree degree;
	protected String graduatedUniversity;
	protected String graduatedDate;
	protected String major;//所属专业
	protected String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public boolean isMemberOfCic() {
		return isMemberOfCic;
	}
	public void setMemberOfCic(boolean isMemberOfCic) {
		this.isMemberOfCic = isMemberOfCic;
	}
	public String getCertificationIdOfCic() {
		return certificationIdOfCic;
	}
	public void setCertificationIdOfCic(String certificationIdOfCic) {
		this.certificationIdOfCic = certificationIdOfCic;
	}
	public String getBirthYearMonth() {
		return birthYearMonth;
	}
	public void setBirthYearMonth(String birthYearMonth) {
		this.birthYearMonth = birthYearMonth;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdentityCardId() {
		return identityCardId;
	}
	public void setIdentityCardId(String identityCardId) {
		this.identityCardId = identityCardId;
	}
	public boolean isBackState() {
		return isBackState;
	}
	public void setBackState(boolean isBackState) {
		this.isBackState = isBackState;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public String getTechnicalTitle() {
		return technicalTitle;
	}
	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}
	public EducationBackground getEducationBackGround() {
		return educationBackGround;
	}
	public void setEducationBackGround(EducationBackground educationBackGround) {
		this.educationBackGround = educationBackGround;
	}
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public String getGraduatedUniversity() {
		return graduatedUniversity;
	}
	public void setGraduatedUniversity(String graduatedUniversity) {
		this.graduatedUniversity = graduatedUniversity;
	}
	public String getGraduatedDate() {
		return graduatedDate;
	}
	public void setGraduatedDate(String graduatedDate) {
		this.graduatedDate = graduatedDate;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
