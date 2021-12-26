/* #######################################################
# Name: Automation script_rulevalidator                         #
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

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.uhc.map.dao.RxClDao;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.RxCl;
import com.uhc.map.entity.TestReport;

public class RxClDaoImpl implements RxClDao {

	public RxClDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	public List<RxCl> getGPIList(TestReport testReport) {
		// int sheetno=0;
		// TODO Auto-generated method stub
		List<RxCl> codeSetsRxCl = new ArrayList<RxCl>();
		;
		// System.out.println("Codesets sheet file read started");
		// List<ProcedureDoneCl> codeSetsProcedureDone = new
		// ArrayList<ProcedureDoneCl>();
		List<RxCl> finae;
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
			boolean isGpi = false;
			String cellValue = "";
			String gpi = "";
			String gpitype = "";
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row != sheet.getRow(0)) {
					// get the number of cells in the header row
					RxCl rxcl = new RxCl(); // Data structure to hold the data from the xls file.
					Iterator<Cell> cite = row.cellIterator();
					while (cite.hasNext()) {
						Cell c = cite.next();
						cellValue = dataFormatter.formatCellValue(c).trim();
						if (isGpi && !cellValue.isEmpty() && cellValue != "" && cellValue != null) {  
							if (gpitype.equalsIgnoreCase("GPI8")) {  //Added
								
								if(cellValue.length()>8)
								gpi = cellValue.substring(0, 8);
								
								gpi=cellValue;
							}
							// gpi=StringUtils.substring(cellValue, 0, 7);
							else if (gpitype.equalsIgnoreCase("GPI10")) //Added
							{
								if(cellValue.length()>10)
									gpi = cellValue.substring(0, 10);
									
									gpi=cellValue;
							}
							else if (gpitype.equalsIgnoreCase("GPI12")) { //Added
								if(cellValue.length()>12)
									gpi = cellValue.substring(0, 12);
									
									gpi=cellValue;
							}
						
								else if (gpitype.equalsIgnoreCase("GPI"))
								gpi = cellValue;
							

							rxcl.setGpi(gpi);

							isGpi = false;
						}

						if ((cellValue.equalsIgnoreCase("GPI") || cellValue.equalsIgnoreCase("GPI12")
								|| cellValue.equalsIgnoreCase("GPI10") || cellValue.equalsIgnoreCase("GPI8"))
								&& !cellValue.isEmpty() && cellValue != "" && cellValue != null) {
							isGpi = true;
							gpitype = cellValue;

						}

					}

					codeSetsRxCl.add(rxcl);

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
			return codeSetsRxCl;
		}

	}

}
