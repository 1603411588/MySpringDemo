package com.liuyi.controller;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

@Controller
@RequestMapping("/report")
public class DynamicReportsController {

	@Autowired
	private DataSource dataSource;

	@RequestMapping("/phoneReport")
	public void phoneReport(HttpServletResponse response) throws Exception {
		 //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型   
        response.setContentType("multipart/form-data");   
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)   
        response.setHeader("Content-Disposition", "attachment;fileName="+"a.jpg");   
        
		JasperReportBuilder report = DynamicReports.report();//创建空报表  
		//样式  
		StyleBuilder boldStl = DynamicReports.stl.style().bold();
		StyleBuilder boldCenteredStl = DynamicReports.stl.style(boldStl).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder titleStl = DynamicReports.stl.style(boldCenteredStl).setFontSize(16);
		StyleBuilder columnTitleStl = DynamicReports.stl.style(boldCenteredStl).setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
		report.columns(Columns.column("ID", "id", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER), //列  
				Columns.column("手机号段", "code", DataTypes.stringType()), // //标题  
				Columns.column("运营商", "service", DataTypes.stringType()), //
				Columns.column("省份", "province", DataTypes.stringType()), //
				Columns.column("城市", "city", DataTypes.stringType()), //
				Columns.column("品牌", "type", DataTypes.stringType())) //
				.setColumnTitleStyle(columnTitleStl).setHighlightDetailEvenRows(true) //
				.title(Components.text("手机号段").setStyle(titleStl)) //
				.pageFooter(Components.pageXofY().setStyle(boldCenteredStl))//页角  
				.setDataSource("select * from codesubinfo limit 20", dataSource.getConnection());//数据源  
		//显示报表  
		//report.show();
		//生成PDF文件  
		report.toImage(response.getOutputStream(), ImageType.JPG);
		//report.toPdf(response.getOutputStream());
	}
}
