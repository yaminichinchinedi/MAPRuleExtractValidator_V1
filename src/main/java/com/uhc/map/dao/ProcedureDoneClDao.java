/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.dao;

import java.util.List;

import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.TestReport;

public interface ProcedureDoneClDao {

	public List<ProcedureDoneCl> getProcedureDoneList(TestReport testReport);
}
