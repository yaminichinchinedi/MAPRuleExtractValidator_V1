/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.service;

import java.util.List;

import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.TestReport;

public interface TestReportService {

	public TestReport getInput(String filepath) throws Exception;

	public void generateReport(TestReport testReport, String filepath) throws Exception;

}
