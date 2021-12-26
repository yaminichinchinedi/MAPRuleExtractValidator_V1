/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.uhc.map.dao.ProcedureDoneClDao;
import com.uhc.map.daoImpl.ProcedureDoneClDaoImpl;
import com.uhc.map.dto.ProcedureDoneCldto;
import com.uhc.map.dtoImpl.ProcedureDoneCldtoImpl;
import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.DxCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.ProcedureDoneClService;

public class ProcedureDoneClServiceImpl implements ProcedureDoneClService {

	public ProcedureDoneClServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public TestReport validateProcedureDoneCL(TestReport testReport, ProcedureCode procedureCode) throws Exception {
		// TODO Auto-generated method stub

		TestReport output = new TestReport();
		ProcedureDoneClDao procedureDoneCldao = new ProcedureDoneClDaoImpl();
		ProcedureDoneCldto procedureDoneCldto1 = new ProcedureDoneCldtoImpl();
		List<ProcedureDoneCl> procedureDoneCldaoList = new ArrayList<ProcedureDoneCl>();
		procedureDoneCldaoList = procedureDoneCldao.getProcedureDoneList(testReport);
		List<ProcedureDoneCl> procedureDoneCldtoList = new ArrayList<ProcedureDoneCl>();
		procedureDoneCldtoList = procedureDoneCldto1.retriveProcedureDoneCl(procedureCode, testReport);
		output.setProcedureDoneClCount(
				"Proceduredone Check list table has no of records: " + procedureDoneCldtoList.size());

		List<String> procedureDoneClMissingList = new ArrayList<String>();
		List<String> procedureDoneClExtraList = new ArrayList<String>();
		List<String> excelcptlist = new ArrayList<String>();
		List<String> tablecptlist = new ArrayList<String>();

		for (ProcedureDoneCl proceduredoneclmiss : procedureDoneCldaoList)
			if (proceduredoneclmiss.getCpt() != null && proceduredoneclmiss.getCpt() != ""
					&& !proceduredoneclmiss.getCpt().isEmpty())
				excelcptlist.add(proceduredoneclmiss.getCpt());

		for (ProcedureDoneCl proceduredonecletc : procedureDoneCldtoList)
			if (proceduredonecletc.getCpt() != null && proceduredonecletc.getCpt() != ""
					&& !proceduredonecletc.getCpt().isEmpty())
				tablecptlist.add(proceduredonecletc.getCpt());

		if (procedureDoneCldaoList != null && procedureDoneCldaoList.size() > 0) {
			if (procedureDoneCldtoList != null && procedureDoneCldtoList.size() > 0) {

				for (String cptmiss : excelcptlist)
					if (!tablecptlist.contains(cptmiss))
						procedureDoneClMissingList.add(cptmiss);

				for (String cptetc : tablecptlist)
					if (!excelcptlist.contains(cptetc))
						procedureDoneClExtraList.add(cptetc);
			}
		}

		if (procedureDoneClMissingList == null || procedureDoneClMissingList.size() <= 0)
			output.setProcedureDoneClMissingSentence("No Missing ProcedureDone codes Found");
		else
			output.setProcedureDoneClMissingSentence(
					"The ProcedureDone cpt codes that are missing in CL Table are " + procedureDoneClMissingList);

		if (procedureDoneClExtraList == null || procedureDoneClExtraList.size() <= 0)
			output.setProcedureDoneClExtraSentence("No Extra ProcedureDone codes Found");
		else
			output.setProcedureDoneClExtraSentence(
					"The ProcedureDone cpt codes that are coming extra/Invalid in CL Table are "
							+ procedureDoneClExtraList);

		return output;

	}
}
