package com.dicipulus.app.form;

/**
 * @author chenyouqi
 *��Ȼ��ѧ��
 */
public class NaturalScienceForm extends ApplyForm {
	String importantScienceDiscover;
	String researchLimited;
	PaperMonograph[] representative;
	PaperMonograph[] main;
	PaperMonograph[] referenced;//����������ר���������������
	public String getImportantScienceDiscover() {
		return importantScienceDiscover;
	}
	public void setImportantScienceDiscover(String importantScienceDiscover) {
		this.importantScienceDiscover = importantScienceDiscover;
	}
	public String getResearchLimited() {
		return researchLimited;
	}
	public void setResearchLimited(String researchLimited) {
		this.researchLimited = researchLimited;
	}
	public PaperMonograph[] getRepresentative() {
		return representative;
	}
	public void setRepresentative(PaperMonograph[] representative) {
		this.representative = representative;
	}
	public PaperMonograph[] getMain() {
		return main;
	}
	public void setMain(PaperMonograph[] main) {
		this.main = main;
	}
	public PaperMonograph[] getReferenced() {
		return referenced;
	}
	public void setReferenced(PaperMonograph[] referenced) {
		this.referenced = referenced;
	} 
	
}
