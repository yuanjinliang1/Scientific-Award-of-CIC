package com.dicipulus.app.download;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FourthFormJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.MyProperties;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class ForthPdf {
	public static void buildForthPdf(String applierUid,Document document,PdfWriter writer) throws DocumentException, IOException{
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(applierUid);
		String applicationType=applier.getApplicationType();
		
		FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
		FourthForm fourthForm=fourthFormJdbc.getFourthForm(applierUid);
		String fontPath=MyProperties.getRootPath()+"/system/fonts/simsun.ttf";
		BaseFont baseFont = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
		//BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  12 , Font.NORMAL, BaseColor.BLACK);
	if(applicationType.equals("自然科学类")){
		Paragraph paragraph=new Paragraph("四、重要科学发现",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.重要科学发现（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		addHTML(applierUid, document, writer, fourthForm);
//		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
//		paragraph.setSpacingBefore(5f);
//		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.研究局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	}
	else if(applicationType.equals("科技进步类")){

		Paragraph paragraph=new Paragraph("四、主要科技创新",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.重要科技创新（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
//		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
//		paragraph.setSpacingBefore(5f);
//		document.add(paragraph);
		addHTML(applierUid, document, writer, fourthForm);
		document.newPage();
		paragraph=new Paragraph("2.科技局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
	}
	else if(applicationType.equals("技术发明类")){
		Paragraph paragraph=new Paragraph("四、重要科学发现",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("1.主要技术发明（限5页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		addHTML(applierUid, document, writer, fourthForm);
//		 // CSS
//		CSSResolver cssResolver = new StyleAttrCSSResolver();
//		CssFile cssFile=XMLWorkerHelper.getCSS(new FileInputStream(MyProperties.getRootPath()+"/system/fonts/bootstrap.min.css"));
//		cssResolver.addCss(cssFile);
//        cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(
//        		("body {font-family:SimSun}"+" table, td, th { border: 1px solid black;}"+
//        		" table {border-collapse: collapse;}"+" td {vertical-align: bottom;}")
//        		.getBytes()));
//        cssResolver.addCss(cssFile);
// 
//        // HTML
//        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
//        String pathOfFont=MyProperties.getRootPath()+"/system/fonts/";
//        fontProvider.register(pathOfFont+"simsun.ttf");
//        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
//        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
//        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
//        // Pipelines
//        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
//        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
//        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
//        //XML Worker
//        XMLWorker worker = new XMLWorker(css, true);
//        XMLParser p = new XMLParser(worker);
       
//        String str="<body>"+fourthForm.getFourthForm1()+"</body>";
//        String xhtml=ThirdProjectBriefIntroductionPdf.toXHTML(str);
//        p.parse(new ByteArrayInputStream(xhtml.getBytes(StandardCharsets.UTF_8)), Charset.forName("UTF-8"));
		
//		paragraph=new Paragraph(fourthForm.getFourthForm1(),fontChinese);////
//		paragraph.setSpacingBefore(5f);
//		document.add(paragraph);
		document.newPage();
		paragraph=new Paragraph("2.技术局限性（限1页）",fontChinese);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph(fourthForm.getFourthForm2(),fontChinese);////
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		document.newPage();
	
		
	}
	}
	private static void addHTML(String applierUid,Document document,PdfWriter writer,FourthForm fourthForm) throws IOException{
		 // CSS
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile=XMLWorkerHelper.getCSS(new FileInputStream(MyProperties.getRootPath()+"/system/fonts/bootstrap.min.css"));
		cssResolver.addCss(cssFile);
		cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(
        		("body {font-family:SimSun}"+" table, td, th { border: 1px solid black;}"+
        		" table {border-collapse: collapse;}"+" td {vertical-align: bottom;}"+
        		"p {font-size:12pt}")
        		.getBytes()));
        cssResolver.addCss(cssFile);
 
        // HTML
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        String pathOfFont=MyProperties.getRootPath()+"/system/fonts/";
        fontProvider.register(pathOfFont+"simsun.ttf");
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        //XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
       
        String str="<body>"+fourthForm.getFourthForm1()+"</body>";
        String xhtml=ThirdProjectBriefIntroductionPdf.toXHTML(str);
        p.parse(new ByteArrayInputStream(xhtml.getBytes(StandardCharsets.UTF_8)), Charset.forName("UTF-8"));
	}
}
