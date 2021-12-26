/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.entity;

import java.io.Serializable;

import java.util.List;

public class ProcedureCode {

	public ProcedureCode() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private int procedureCodeKey;

	private String cpt;

	private String description;

	private String laterality;

	public int getProcedureCodeKey() {
		return this.procedureCodeKey;
	}

	public void setProcedureCodeKey(int procedureCodeKey) {
		this.procedureCodeKey = procedureCodeKey;
	}

	public String getCpt() {
		return this.cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLaterality() {
		return this.laterality;
	}

	public void setLaterality(String laterality) {
		this.laterality = laterality;
	}

}
