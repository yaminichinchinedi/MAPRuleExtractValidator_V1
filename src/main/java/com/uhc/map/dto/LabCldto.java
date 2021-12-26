/* #######################################################
# Name: Automation script_rulesvalidator                         #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.dto;

import java.util.List;

import com.uhc.map.entity.LabCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;

public interface LabCldto {

	public List<LabCl> retriveLabCl(ProcedureCode procedurecode, TestReport testReport) throws ClassNotFoundException;
}
