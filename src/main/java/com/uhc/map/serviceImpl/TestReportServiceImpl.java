/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.TestReportService;

public class TestReportServiceImpl implements TestReportService {

	private static final short VERTICAL_CENTER = 0;
	private static final short BOLDWEIGHT_BOLD = 0;
	private static final String BackgroundType = null;
	private String filepath;

	public TestReportServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public TestReport getInput(String filepath) throws Exception {
		// TODO Auto-generated method stub
		TestReport testReport = new TestReport();
		DataFormatter dataFormatter = new DataFormatter();
		try {
			Workbook workbook = WorkbookFactory.create(new File(filepath));
			Sheet sheet = workbook.getSheetAt(0);

			String cellValue;
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// get the number of cells in the header row

				Iterator<Cell> cite = row.cellIterator();
				while (cite.hasNext()) {

					Cell c = cite.next();

					cellValue = dataFormatter.formatCellValue(c).trim();
					if (!cellValue.isEmpty() && cellValue != "" && cellValue != null) {
						if (cellValue.contains("Excel File Location"))
							testReport.setCodesetsfile(cite.next().toString());

						else if (cellValue.contains("Excel File Sheet Number")) {
							String sheetnumber = cite.next().toString();
							testReport.setSheetno(Integer.parseInt(sheetnumber));

						} else if (cellValue.contains("Procedure Name"))
							testReport.setProcedurename(cite.next().toString());

						else if (cellValue.contains("Connection User"))
							testReport.setUser(cite.next().toString());

						else if (cellValue.contains("Connection Password"))
							testReport.setPwd(cite.next().toString());
						else if (cellValue.contains("Connection URL"))
							testReport.setConnectionurl(cite.next().toString());
						else if (cellValue.contains("Database Name"))
							testReport.setDatabasename(cite.next().toString());
						else
							break;

					}

				}

			}
			String urll = testReport.getConnectionurl() + ";databaseName=" + testReport.getDatabasename();
			testReport.setUrl(urll);

			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testReport;

	}

	
	public void generateReport(TestReport testReport, String filepath) throws Exception {
		// TODO Auto-generated method stub


		ArrayList<String> output = new ArrayList<String>();

		try {
			FileInputStream inputStream = new FileInputStream(new File(filepath));
			Workbook workbookoutout = WorkbookFactory.create(inputStream);
			if (!workbookoutout.isSheetHidden(1))
				workbookoutout.removeSheetAt(1);
			Sheet sheetoutout = workbookoutout.createSheet("Results");
			CellStyle cs = workbookoutout.createCellStyle();
			cs.setWrapText(true);
			cs.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			cs.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			cs.setBottomBorderColor(IndexedColors.DARK_GREEN.getIndex());
			cs.setTopBorderColor(IndexedColors.DARK_GREEN.getIndex());
			cs.setLeftBorderColor(IndexedColors.DARK_GREEN.getIndex());
			cs.setRightBorderColor(IndexedColors.DARK_GREEN.getIndex());

			Font cellfont = workbookoutout.createFont();
			cellfont.setColor(IndexedColors.BLACK.getIndex());
			cs.setFont(cellfont);
			CellStyle csheader = workbookoutout.createCellStyle();
			csheader.setAlignment(VERTICAL_CENTER);
			Font headerfont = workbookoutout.createFont();
			headerfont.setBold(true);
			headerfont.setBoldweight(BOLDWEIGHT_BOLD);
			headerfont.setColor(IndexedColors.DARK_BLUE.getIndex());
			headerfont.setFontHeightInPoints((short) 20);
			headerfont.setFontName("Arial");
			csheader.setFont(headerfont);
			csheader.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			int columnCount = 1;
			Row header = sheetoutout.createRow(0);
			Cell headercell = header.createCell(columnCount);
			headercell.setCellStyle(csheader);
			headercell.setCellValue((String) "Report");

			int rowCount = 1;
			output.add(testReport.getAncillaryInfoClCount());
			output.add(testReport.getAncillaryInfoClMissingSentence());
			output.add(testReport.getAncillaryInfoClExtraSentence());

			output.add(testReport.getProcedureDoneClCount());
			output.add(testReport.getProcedureDoneClMissingSentence());
			output.add(testReport.getProcedureDoneClExtraSentence());

			output.add(testReport.getDxClCount());
			output.add(testReport.getDxClMissingSentence());
			output.add(testReport.getDxClExtraSentence());

			output.add(testReport.getRxClCount());
			output.add(testReport.getRxClMissingSentence());
			output.add(testReport.getRxClExtraSentence());

			output.add(testReport.getLabClCount());
			output.add(testReport.getLabClMissingSentence());
			output.add(testReport.getLabClExtraSentence());

			for (String out : output) {
				Row row = sheetoutout.createRow(++rowCount);

				Cell cell = row.createCell(columnCount);
				sheetoutout.setColumnWidth(columnCount, 30000);
				cell.setCellStyle(cs);
				cell.setCellValue((String) out);

				Row row1 = sheetoutout.createRow(++rowCount);
				Cell cell1 = row1.createCell(columnCount);
				cell1.setAsActiveCell();

			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(filepath);
			workbookoutout.write(outputStream);

			workbookoutout.close();
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
