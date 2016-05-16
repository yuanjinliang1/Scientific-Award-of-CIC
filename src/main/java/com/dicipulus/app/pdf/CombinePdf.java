package com.dicipulus.app.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class CombinePdf {
	public static void buildPdf(String applierUid) throws DocumentException, IOException{
		Document document=new Document(PageSize.A4,50,50,50,50);
		PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("/Users/cyq/Desktop/PDFtest.pdf"));
		document.open();
		FirstProjectBasicSituationPdf.buildFirstProjectBasicSituationPdf(applierUid, document);
		SecondRefereeUnitOpinionPdf.buildSecondRefereeUnitOpinionPdf(applierUid, document);
		ThirdProjectBriefIntroductionPdf.buildThirdProjectBriefIntroductionPdf(applierUid, document);
		ForthPdf.buildForthPdf(applierUid, document);
		FifthObjectEvaluationPdf.buildFifthObjectEvaluationPdf(applierUid, document);
		SixthPdf.buildSixthPdf(applierUid, document);
		SeventhPdf.buildSeventhPdf(applierUid, document);
		EighthPdf.buildEighthPdf(applierUid, document);
		NinethMajorOrgContributorPdf.buildNinethMajorOrgContributorPdf(applierUid, document);
		document.close();
	}
}
