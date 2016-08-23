package com.dicipulus.app.download;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.ThirdProjectBriefIntroductionJdbc;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.dicipulus.app.model.MyProperties;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.qrcode.Encoder;
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
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;

public class ThirdProjectBriefIntroductionPdf {
	private static final Logger logger = LoggerFactory.getLogger(ThirdProjectBriefIntroductionPdf.class);
	@SuppressWarnings("deprecation")
	public static void buildThirdProjectBriefIntroductionPdf(String applierUid,Document document,PdfWriter writer) throws DocumentException, IOException{
//		Document document=new Document(PageSize.A4,50,50,50,50);
//		PdfWriter.getInstance(document, new FileOutputStream("/Users/cyq/Desktop/PDFtest.pdf"));
//		document.open();
		ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
		ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=thirdProjectBriefIntroductionJdbc.getThirdProjectBriefIntroduction(applierUid);
		BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subTitle =  new  Font(baseFont  ,16, Font.BOLD, BaseColor.BLACK);
		Font fontChinese =  new  Font(baseFont  ,  14 , Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph=new Paragraph("三、项目简介",subTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		paragraph=new Paragraph("(限1200字)",fontChinese);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(5f);
		document.add(paragraph);
		//uncomment this
//		paragraph=new Paragraph(thirdProjectBriefIntroduction.getBriefIntroduction(),fontChinese);////
//		paragraph.setSpacingBefore(5f);
//		document.add(paragraph);
		
		
		 // CSS
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile=XMLWorkerHelper.getCSS(new FileInputStream(MyProperties.getRootPath()+"/system/fonts/bootstrap.min.css"));
		cssResolver.addCss(cssFile);
		 cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(
        		("body {font-family:SimSun}"+" table, td, th { border: 1px solid black;}"+
        		" table {border-collapse: collapse;}"+" td {vertical-align: bottom;}"+
        		"p {line-height:100%;padding:3px;margin:0px}")
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
       
        String str="<body>"+thirdProjectBriefIntroduction.getBriefIntroduction()+"</body>";
        String xhtml=toXHTML(str);
        p.parse(new ByteArrayInputStream(xhtml.getBytes(StandardCharsets.UTF_8)), Charset.forName("UTF-8"));
        //XMLWorkerHelper.getInstance().parseXHtml(writer, document,new ByteArrayInputStream(xhtml.getBytes(StandardCharsets.UTF_8)), Charset.forName("UTF-8"));
        
		document.newPage();
		
//		document.close();	
	}
	protected static String toXHTML( String html ) {
	    final org.jsoup.nodes.Document document = Jsoup.parse( html );
	    document.outputSettings().syntax( org.jsoup.nodes.Document.OutputSettings.Syntax.xml );

	    return document.html();
	}
	
	protected static class Base64ImageProvider extends AbstractImageProvider {
		 
        @Override
        public Image retrieve(String src) {
            int pos = src.indexOf("base64,");
            try {
                if (src.startsWith("data") && pos > 0) {
                    byte[] img = Base64.decode(src.substring(pos + 7));
                    return Image.getInstance(img);
                }
                else {
                    return Image.getInstance(src);
                }
            } catch (BadElementException ex) {
                return null;
            } catch (IOException ex) {
                return null;
            }
        }
 
        @Override
        public String getImageRootPath() {
            return null;
        }
    }
}
