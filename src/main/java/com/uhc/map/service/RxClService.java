/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.service;

import java.util.List;

import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.RxCl;
import com.uhc.map.entity.TestReport;

public interface RxClService {

	public TestReport validateRxCL(TestReport tep, ProcedureCode poc) throws Exception;
}
