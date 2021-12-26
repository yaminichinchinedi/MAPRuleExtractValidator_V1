/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.uhc.map.dao.AncillaryInfoClDao;
import com.uhc.map.daoImpl.AncillaryInfoClDaoImpl;
import com.uhc.map.dto.AncillaryInfoCldto;
import com.uhc.map.dtoImpl.AncillaryInfoCldtoImpl;
import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.AncillaryInfoClService;

public class AncillaryInfoClServiceImpl implements AncillaryInfoClService {

	public AncillaryInfoClServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public TestReport validateAncillaryInfoCL(TestReport testReport, ProcedureCode procedureCode) throws Exception {
		// TODO Auto-generated method stub
		TestReport output = new TestReport();
		AncillaryInfoClDao ancillaryInfoCldao = new AncillaryInfoClDaoImpl();
		AncillaryInfoCldto ancillaryInfoCldto1 = new AncillaryInfoCldtoImpl();
		List<AncillaryInfoCl> ancillaryInfoCldaoList = new ArrayList<AncillaryInfoCl>();
		ancillaryInfoCldaoList = ancillaryInfoCldao.getKeywordsList(testReport);
		List<AncillaryInfoCl> ancillaryInfoCldtoList = new ArrayList<AncillaryInfoCl>();
		ancillaryInfoCldtoList = ancillaryInfoCldto1.retriveAncillaryInfoCl(procedureCode, testReport);
		output.setAncillaryInfoClCount(
				"AncillaryInfo Check list table has no of records: " + ancillaryInfoCldtoList.size());

		List<String> ancillaryInfoClMissingList = new ArrayList<String>();
		List<String> ancillaryInfoClExtraList = new ArrayList<String>();
		List<String> excelancilist = new ArrayList<String>();
		List<String> tableancilist = new ArrayList<String>();

		for (AncillaryInfoCl ancillaryinfoclmiss : ancillaryInfoCldaoList) {

			if (ancillaryinfoclmiss.getVariances() != null && ancillaryinfoclmiss.getVariances() != ""
					&& !ancillaryinfoclmiss.getVariances().isEmpty()) {
				if (ancillaryinfoclmiss.getVariances().contains("|")) {
					String var = ancillaryinfoclmiss.getVariances().trim();
					StringTokenizer st1 = new StringTokenizer(var, "|");
					while (st1.hasMoreTokens()) {
						String valu=st1.nextToken().trim();
						excelancilist.add(valu);
						//System.out.println("Tokenized string is " + st1.nextToken());
					}

				} else
					excelancilist.add(ancillaryinfoclmiss.getVariances().trim());

			}
		}
		for (AncillaryInfoCl ancillaryinfocletc : ancillaryInfoCldtoList) {

			if (ancillaryinfocletc.getVariances() != null && ancillaryinfocletc.getVariances() != ""
					&& !ancillaryinfocletc.getVariances().isEmpty()) {
				if (ancillaryinfocletc.getVariances().contains("|")) {
					String vare = ancillaryinfocletc.getVariances().trim();
					StringTokenizer st2 = new StringTokenizer(vare, "|");
					while (st2.hasMoreTokens())
					{
						String val=st2.nextToken().trim();
						tableancilist.add(val);
					}
				} else
					tableancilist.add(ancillaryinfocletc.getVariances().trim());

			}
		}
		if (ancillaryInfoCldaoList != null && ancillaryInfoCldaoList.size() > 0) {
			if (ancillaryInfoCldtoList != null && ancillaryInfoCldtoList.size() > 0) {
				for (String ancimiss : excelancilist)
					if (!tableancilist.contains(ancimiss))
						ancillaryInfoClMissingList.add(ancimiss);

				for (String ancietc : tableancilist)
					if (!excelancilist.contains(ancietc))
						ancillaryInfoClExtraList.add(ancietc);
			}
		}

		if (ancillaryInfoClMissingList == null || ancillaryInfoClMissingList.size() <= 0)
			output.setAncillaryInfoClMissingSentence("No Missing AncillaryInfo keywords Found");
		else
			output.setAncillaryInfoClMissingSentence(
					"The AncillaryInfo keywords that are missing in CL Table are " + ancillaryInfoClMissingList);

		if (ancillaryInfoClExtraList == null || ancillaryInfoClExtraList.size() <= 0)
			output.setAncillaryInfoClExtraSentence("No Extra AncillaryInfo keywords Found");
		else
			output.setAncillaryInfoClExtraSentence(
					"The AncillaryInfo keywords that are coming extra/Invalid in CL Table are "
							+ ancillaryInfoClExtraList);

		return output;

	}

}
