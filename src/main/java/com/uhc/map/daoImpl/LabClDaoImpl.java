/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.daoImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.uhc.map.dao.LabClDao;
import com.uhc.map.entity.LabCl;
import com.uhc.map.entity.TestReport;

public class LabClDaoImpl implements LabClDao {

	public LabClDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	public List<LabCl> getLOINCList(TestReport testReport) {
		// int sheetno=0;
		// TODO Auto-generated method stub
		List<LabCl> codeSetsLabCl = new ArrayList<LabCl>();
		;
		// System.out.println("Codesets sheet file read started");
		// List<ProcedureDoneCl> codeSetsProcedureDone = new
		// ArrayList<ProcedureDoneCl>();
		List<LabCl> finae;
		DataFormatter dataFormatter = new DataFormatter();
		String filepath = testReport.getCodesetsfile();
		int sheetno = testReport.getSheetno();
		// final String
		// codesetsheet="C:\\\\Users\\\\ych\\\\Desktop\\\\MAP\\\\RuleExtractValidationSuite\\\\CodeSets.xlsx";
		// System.out.println(codesetsheet);
		try {
			// SXSSFWorkbook workbook = new SXSSFWorkbook();
			// XSSFSheet sheet = workbook.getSheetAt(0);
			// XSSFRow headerrow = sheet.getRow(0);
			Workbook workbook = WorkbookFactory.create(new File(filepath));

			Sheet sheet = workbook.getSheetAt(sheetno - 1);

			Row headerrow = sheet.getRow(0);

			String columnC = null;
			String columnD = null;
			if (headerrow.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
				columnC = headerrow.getCell(2).getStringCellValue();
				// System.out.println("columnC is "+columnC);
			}
			if (headerrow.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
				columnD = headerrow.getCell(3).getStringCellValue();
				// System.out.println("columnD is "+columnD);
			}

			int numberOfCells = 0;
			boolean isLoinc = false;
			String cellValue;
			String loinc;
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row != sheet.getRow(0)) {
					// get the number of cells in the header row
					LabCl labcl = new LabCl(); // Data structure to hold the data from the xls file.
					Iterator<Cell> cite = row.cellIterator();
					while (cite.hasNext()) {
						Cell c = cite.next();
						cellValue = dataFormatter.formatCellValue(c).trim();
						if (isLoinc && !cellValue.isEmpty() && cellValue != "" && cellValue != null) {
							labcl.setLoinc(cellValue.trim());
							isLoinc = false;

						}

						if ((cellValue.equalsIgnoreCase("LOINC") || cellValue.equalsIgnoreCase("LOINC "))  && !cellValue.isEmpty() && cellValue != ""
								&& cellValue != null) {
							isLoinc = true;

						}

					}

					codeSetsLabCl.add(labcl);

				}

			}

			/*
			 * Iterator<ProcedureDoneCl> i=codeSetsProcedureDone.iterator();
			 * while(i.hasNext()) { ProcedureDoneCl p = i.next();
			 * System.out.println("excel values are: "+p.getCpt()); }
			 * 
			 * 
			 * / for(RegressionTestReportExcel rt: listOfDataFromReport){
			 * System.out.println("FACET IDS ARE: " + rt.getFacetID()); //etc... }
			 */
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			// System.out.println("Code sets sheet file read ended");
			return codeSetsLabCl;
		}

	}

}
