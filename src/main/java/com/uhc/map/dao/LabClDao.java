/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.dao;

import java.util.List;

import com.uhc.map.entity.LabCl;
import com.uhc.map.entity.RxCl;
import com.uhc.map.entity.TestReport;

public interface LabClDao {

	public List<LabCl> getLOINCList(TestReport testReport);
}
