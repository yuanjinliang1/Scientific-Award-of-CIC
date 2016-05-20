package com.dicipulus.app.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dicipulus.app.JDBC.FifthObjectiveEvaluationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class FifthObjectEvaluationPdf {
	public static void buildFifthObjectEvaluationPdf(String applierUid,Document document) throws DocumentException, IOException{
//		Document document=new Document(PageSize.A4,50,50,50,50);
//		PdfWriter.getInstance(document, new FileOutputStream("/Users/cyq/Desktop/PDFtest.pdf"));
//		document.open();
		FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
		FifthObjectiveEvaluation FifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("五、客观评价",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("(限2页)",fontChinese);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(FifthObjectiveEvaluation.getObjectiveEvaluation(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
//		document.close();
	}
}
