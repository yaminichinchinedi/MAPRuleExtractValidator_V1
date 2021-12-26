/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.entity;

public class DxCl {

	public DxCl() {
		// TODO Auto-generated constructor stub
	}

	public int getDxClKey() {
		return dxClKey;
	}

	public void setDxClKey(int dxClKey) {
		this.dxClKey = dxClKey;
	}

	private int dxClKey;
	private String icd10;

	public String getIcd10() {
		return icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;

}
