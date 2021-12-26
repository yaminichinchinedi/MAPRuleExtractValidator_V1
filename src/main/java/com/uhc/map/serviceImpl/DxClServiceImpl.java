/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.uhc.map.dao.DxClDao;
import com.uhc.map.daoImpl.DxClDaoImpl;
import com.uhc.map.dto.DxCldto;
import com.uhc.map.dtoImpl.DxCldtoImpl;
import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.DxCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;
import com.uhc.map.service.DxClService;

public class xxDxClServiceImpl implements DxClService {

	public DxClServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public TestReport validateDxCL(TestReport testReport, ProcedureCode procedureCode) throws Exception {
		// TODO Auto-generated method stub
		TestReport output = new TestReport();

		DxClDao dxCldao = new DxClDaoImpl();
		DxCldto dxCldto1 = new DxCldtoImpl();
		List<DxCl> dxCldaoList = new ArrayList<DxCl>();
		dxCldaoList = dxCldao.getDxList(testReport);
		List<DxCl> dxCldtoList = new ArrayList<DxCl>();
		dxCldtoList = dxCldto1.retriveDxCl(procedureCode, testReport);
		output.setDxClCount("Dx Check list table has no of records: " + dxCldtoList.size());

		List<String> dxClMissingList = new ArrayList<String>();
		List<String> dxClExtraList = new ArrayList<String>();
		List<String> excelicdlist = new ArrayList<String>();
		List<String> tableicdlist = new ArrayList<String>();

		for (DxCl dxclmiss : dxCldaoList)
			if (dxclmiss.getIcd10() != null && dxclmiss.getIcd10() != "" && !dxclmiss.getIcd10().isEmpty())
				excelicdlist.add(dxclmiss.getIcd10());

		for (DxCl dxcletc : dxCldtoList)
			if (dxcletc.getIcd10() != null && dxcletc.getIcd10() != "" && !dxcletc.getIcd10().isEmpty())
				tableicdlist.add(dxcletc.getIcd10());

		if (dxCldaoList != null && dxCldaoList.size() > 0) {
			if (dxCldtoList != null && dxCldtoList.size() > 0) {

				for (String icdmiss : excelicdlist)
					if (!tableicdlist.contains(icdmiss))
						dxClMissingList.add(icdmiss);

				for (String icdetc : tableicdlist)
					if (!excelicdlist.contains(icdetc))
						dxClExtraList.add(icdetc);

			}
		}

		if (dxClMissingList == null || dxClMissingList.size() <= 0)
			output.setDxClMissingSentence("No Missing Dx codes Found");

		else
			output.setDxClMissingSentence("The Dx codes that are missing in CL Table are " + dxClMissingList);

		if (dxClExtraList == null || dxClExtraList.size() <= 0)
			output.setDxClExtraSentence("No Extra Dx codes Found");

		else
			output.setDxClExtraSentence("The Dx codes that are coming extra/Invalid in CL Table are " + dxClExtraList);

		return output;
	}

}
