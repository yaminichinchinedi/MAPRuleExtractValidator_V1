/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.service;

import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;

public interface LabClService {
	public TestReport validateLabCL(TestReport tep, ProcedureCode poc) throws Exception;
}
