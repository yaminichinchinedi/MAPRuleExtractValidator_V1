/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.RuleExtractValidator;

import com.uhc.map.*;
import com.uhc.map.entity.*;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.LabClService;
import com.uhc.map.service.*;
import com.uhc.map.serviceImpl.*;
import com.uhc.map.dao.*;
import com.uhc.map.daoImpl.*;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

public class RuleExtractValidator {

	public RuleExtractValidator() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @Value("${file.path}") private String TestReportfilepath;
	 */
	public static void main(String args[]) throws Exception {

		// TODO Auto-generated method stub
		try {

			AncillaryInfoClService ancillaryInfoClService = null;
			DxClService dxClService;
			RxClService rxClService;
			ProcedureDoneClService procedureDoneClService;
			LabClService labClService;

			procedureDoneClService = new ProcedureDoneClServiceImpl();
			ancillaryInfoClService = new AncillaryInfoClServiceImpl();
			dxClService = new DxClServiceImpl();
			rxClService = new RxClServiceImpl();
			labClService = new LabClServiceImpl();

			TestReport procedureDoneClServiceResults = new TestReport();
			TestReport ancillaryInfoClServiceResults = new TestReport();
			TestReport dxClServiceResults = new TestReport();
			TestReport    = new TestReport();
			TestReport labClServiceResults = new TestReport();

			// String TestReportfilepath

			String TestReportfilepath = "";
			InputStream input = RuleExtractValidator.class.getClassLoader()
					.getResourceAsStream("application.properties");

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find application.properties");

			}

			else {
				// load a properties file from class path, inside static method
				prop.load(input);
				TestReportfilepath = prop.getProperty("file.path");
				// System.out.println("file path is "+TestReportfilepath);
			}
			// String TestReportfilepath =
			// "C:\\\\Users\\\\ych\\\\Desktop\\\\MAP\\\\RuleExtractValidationSuite\\\\TestReport.xlsx";

			TestReportService testReportService = new TestReportServiceImpl();
			TestReport testReport = new TestReport();
			testReport = testReportService.getInput(TestReportfilepath);

			ProcedureCode procedureCode = new ProcedureCode();
			procedureCode.setDescription(testReport.getProcedurename());

			procedureDoneClServiceResults = procedureDoneClService.validateProcedureDoneCL(testReport, procedureCode);
			ancillaryInfoClServiceResults = ancillaryInfoClService.validateAncillaryInfoCL(testReport, procedureCode);
			dxClServiceResults = dxClService.validateDxCL(testReport, procedureCode);
			rxClServiceResults = rxClService.validateRxCL(testReport, procedureCode);
			labClServiceResults = labClService.validateLabCL(testReport, procedureCode);

			testReport.setAncillaryInfoClCount(ancillaryInfoClServiceResults.getAncillaryInfoClCount());
			testReport.setProcedureDoneClCount(procedureDoneClServiceResults.getProcedureDoneClCount());
			testReport.setDxClCount(dxClServiceResults.getDxClCount());
			testReport.setRxClCount(rxClServiceResults.getRxClCount());
			testReport.setLabClCount(labClServiceResults.getLabClCount());

			testReport.setAncillaryInfoClMissingSentence(
					ancillaryInfoClServiceResults.getAncillaryInfoClMissingSentence());
			testReport.setProcedureDoneClMissingSentence(
					procedureDoneClServiceResults.getProcedureDoneClMissingSentence());
			testReport.setDxClMissingSentence(dxClServiceResults.getDxClMissingSentence());
			testReport.setRxClMissingSentence(rxClServiceResults.getRxClMissingSentence());
			testReport.setLabClMissingSentence(labClServiceResults.getLabClMissingSentence());

			testReport.setAncillaryInfoClExtraSentence(ancillaryInfoClServiceResults.getAncillaryInfoClExtraSentence());
			testReport.setProcedureDoneClExtraSentence(procedureDoneClServiceResults.getProcedureDoneClExtraSentence());
			testReport.setDxClExtraSentence(dxClServiceResults.getDxClExtraSentence());
			testReport.setRxClExtraSentence(rxClServiceResults.getRxClExtraSentence());
			testReport.setLabClExtraSentence(labClServiceResults.getLabClExtraSentence());

			testReportService.generateReport(testReport, TestReportfilepath);
			System.out.println("Report Generated Successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			System.out.println(
					"Please make sure to close the Excel sheets while running the application again. Thank you!");
		}

	}

}
