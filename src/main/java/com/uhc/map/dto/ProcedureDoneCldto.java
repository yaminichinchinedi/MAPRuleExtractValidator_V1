/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.dto;

import java.util.List;

import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.TestReport;

public interface ProcedureDoneCldto {

	public List<ProcedureDoneCl> retriveProcedureDoneCl(ProcedureCode procedurecode, TestReport testReport)
			throws ClassNotFoundException;
}
