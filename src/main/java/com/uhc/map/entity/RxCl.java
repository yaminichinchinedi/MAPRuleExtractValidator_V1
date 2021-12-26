/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.entity;

public class RxCl {

	public RxCl() {
		// TODO Auto-generated constructor stub
	}

	private int rxClKey;
	private String gpi;

	public int getRxClKey() {
		return rxClKey;
	}

	public void setRxClKey(int rxClKey) {
		this.rxClKey = rxClKey;
	}

	public String getGpi() {
		return gpi;
	}

	public void setGpi(String gpi) {
		this.gpi = gpi;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	private String drugName;
	private String ndc;

}
