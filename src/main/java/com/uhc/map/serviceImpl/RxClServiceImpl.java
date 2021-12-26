/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.uhc.map.dao.RxClDao;
import com.uhc.map.daoImpl.RxClDaoImpl;
import com.uhc.map.dto.RxCldto;
import com.uhc.map.dtoImpl.RxCldtoImpl;
import com.uhc.map.entity.DxCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.RxCl;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.RxClService;

public class RxClServiceImpl implements RxClService {

	public RxClServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	public TestReport validateRxCL(TestReport testReport, ProcedureCode procedureCode) throws Exception {
		// TODO Auto-generated method stub
		TestReport output = new TestReport();

		RxClDao rxCldao = new RxClDaoImpl();
		RxCldto rxCldto1 = new RxCldtoImpl();
		List<RxCl> rxCldaoList = new ArrayList<RxCl>();
		rxCldaoList = rxCldao.getGPIList(testReport);
		List<RxCl> rxCldtoList = new ArrayList<RxCl>();
		rxCldtoList = rxCldto1.retriveRxCl(procedureCode, testReport);
		output.setRxClCount("Rx Check list table has no of records: " + rxCldtoList.size());

		List<String> rxClMissingList = new ArrayList<String>();
		List<String> rxClExtraList = new ArrayList<String>();
		List<String> excelgpilist = new ArrayList<String>();
		List<String> tablegpilist = new ArrayList<String>();

		for (RxCl rxclmiss : rxCldaoList)
			if (rxclmiss.getGpi() != null && rxclmiss.getGpi() != "" && !rxclmiss.getGpi().isEmpty())
				excelgpilist.add(rxclmiss.getGpi().trim());

		for (RxCl rxcletc : rxCldtoList)
			if (rxcletc.getGpi() != null && rxcletc.getGpi() != "" && !rxcletc.getGpi().isEmpty())
				tablegpilist.add(rxcletc.getGpi().trim());

		if (rxCldaoList != null && rxCldaoList.size() > 0) {
			if (rxCldtoList != null && rxCldtoList.size() > 0) {
				for (String gpimiss : excelgpilist)
					if (!tablegpilist.contains(gpimiss))
						rxClMissingList.add(gpimiss);

				for (String gpietc : tablegpilist)
					if (!excelgpilist.contains(gpietc))
						rxClExtraList.add(gpietc);

			}
		}

		if (rxClMissingList == null || rxClMissingList.size() <= 0)
			output.setRxClMissingSentence("No Missing Rx codes Found");
		else
			output.setRxClMissingSentence("The Rx GPI codes that are missing in CL Table are " + rxClMissingList);

		if (rxClExtraList == null || rxClExtraList.size() <= 0)
			output.setRxClExtraSentence("No Extra Rx codes Found");

		else
			output.setRxClExtraSentence(
					"The Rx GPI codes that are coming extra/Invalid in CL Table are " + rxClExtraList);

		return output;
	}

}
