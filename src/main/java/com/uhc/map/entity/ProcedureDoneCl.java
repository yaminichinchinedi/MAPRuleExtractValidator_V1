/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.entity;

public class ProcedureDoneCl {

	public ProcedureDoneCl() {
		// TODO Auto-generated constructor stub
	}

	private int procedureDoneClKey;

	public int getProcedureDoneClKey() {
		return procedureDoneClKey;
	}

	public void setProcedureDoneClKey(int procedureDoneClKey) {
		this.procedureDoneClKey = procedureDoneClKey;
	}

	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String cpt;
	private String description;

	/*
	 * ProcedureDoneCl(String cpt) { this.cpt = cpt;
	 * 
	 * 
	 * }
	 * 
	 * @Override public boolean equals(Object obj) { // TODO Auto-generated method
	 * stub if(obj instanceof ProcedureDoneCl) { ProcedureDoneCl temp =
	 * (ProcedureDoneCl) obj; if(this.cpt == temp.cpt) return true; } return false;
	 * 
	 * }
	 * 
	 * @Override public int hashCode() { // TODO Auto-generated method stub
	 * 
	 * return (this.cpt.hashCode()); }
	 */

}
