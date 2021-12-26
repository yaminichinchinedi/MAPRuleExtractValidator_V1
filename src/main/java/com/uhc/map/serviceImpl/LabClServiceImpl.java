/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.uhc.map.dao.AncillaryInfoClDao;
import com.uhc.map.dao.LabClDao;
import com.uhc.map.daoImpl.AncillaryInfoClDaoImpl;
import com.uhc.map.daoImpl.LabClDaoImpl;
import com.uhc.map.dto.AncillaryInfoCldto;
import com.uhc.map.dto.LabCldto;
import com.uhc.map.dtoImpl.AncillaryInfoCldtoImpl;
import com.uhc.map.dtoImpl.LabCldtoImpl;
import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.LabCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.LabClService;

public class LabClServiceImpl implements LabClService {

	public LabClServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public TestReport validateLabCL(TestReport testReport, ProcedureCode procedureCode) throws Exception {
		// TODO Auto-generated method stub
		TestReport output = new TestReport();
		LabClDao labCldao = new LabClDaoImpl();
		LabCldto labCldto1 = new LabCldtoImpl();
		List<LabCl> labCldaoList = new ArrayList<LabCl>();
		labCldaoList = labCldao.getLOINCList(testReport);
		List<LabCl> labCldtoList = new ArrayList<LabCl>();
		labCldtoList = labCldto1.retriveLabCl(procedureCode, testReport);
		output.setLabClCount("Lab Check list table has no of records: " + labCldtoList.size());

		List<String> labClMissingList = new ArrayList<String>();
		List<String> labClExtraList = new ArrayList<String>();
		List<String> excelloinclist = new ArrayList<String>();
		List<String> tableloinclist = new ArrayList<String>();

		for (LabCl loincllaryinfoclmiss : labCldaoList) {

			if (loincllaryinfoclmiss.getLoinc() != null && loincllaryinfoclmiss.getLoinc() != ""
					&& !loincllaryinfoclmiss.getLoinc().isEmpty()) {
				if (loincllaryinfoclmiss.getLoinc().contains("|")) {
					String var = loincllaryinfoclmiss.getLoinc().trim();
					StringTokenizer st1 = new StringTokenizer(var, "|");
					while (st1.hasMoreTokens()) {
						String valu=st1.nextToken().trim();
						excelloinclist.add(valu);
						System.out.println("Tokenized string is " + st1.nextToken());
					}

				} else
					excelloinclist.add(loincllaryinfoclmiss.getLoinc().trim());

			}
		}
		for (LabCl loincllaryinfocletc : labCldtoList) {

			if (loincllaryinfocletc.getLoinc() != null && loincllaryinfocletc.getLoinc() != ""
					&& !loincllaryinfocletc.getLoinc().isEmpty()) {
				if (loincllaryinfocletc.getLoinc().contains("|")) {
					String vare = loincllaryinfocletc.getLoinc().trim();
					StringTokenizer st2 = new StringTokenizer(vare, "|");
					while (st2.hasMoreTokens())
					{
						String val=st2.nextToken().trim();
						tableloinclist.add(val);
					}
				} else
					tableloinclist.add(loincllaryinfocletc.getLoinc().trim());

			}
		}
		if (labCldaoList != null && labCldaoList.size() > 0) {
			if (labCldtoList != null && labCldtoList.size() > 0) {
				for (String loincmiss : excelloinclist)
					if (!tableloinclist.contains(loincmiss))
						labClMissingList.add(loincmiss);

				for (String loincetc : tableloinclist)
					if (!excelloinclist.contains(loincetc))
						labClExtraList.add(loincetc);
			}
		}

		if (labClMissingList == null || labClMissingList.size() <= 0)
			output.setLabClMissingSentence("No Missing Lab codes Found");
		else
			output.setLabClMissingSentence("The Lab codes that are missing in CL Table are " + labClMissingList);

		if (labClExtraList == null || labClExtraList.size() <= 0)
			output.setLabClExtraSentence("No Extra Lab codes Found");
		else
			output.setLabClExtraSentence(
					"The Lab codes that are coming extra/Invalid in CL Table are " + labClExtraList);

		return output;
	}

}
