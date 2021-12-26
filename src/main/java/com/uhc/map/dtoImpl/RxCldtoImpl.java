/* #######################################################
# Name: Automation script_rulevalidator                          #
# Date:12-14-2019                                        #
# Author: Yamini                                       #
# Description: This program will help automating ....  #
###################################################### */
package com.uhc.map.dtoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uhc.map.dto.RxCldto;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.ProcedureDoneCl;
import com.uhc.map.entity.RxCl;
import com.uhc.map.entity.TestReport;

public class RxCldtoImpl implements RxCldto {

	public RxCldtoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<RxCl> retriveRxCl(ProcedureCode procedurecode, TestReport testReport) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		List<RxCl> rxClTable = new ArrayList<RxCl>();

		Connection conn = null;
		ResultSet resultSet = null;
		String procedure = procedurecode.getDescription();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String user = testReport.getUser();
			String password = testReport.getPwd();
			String dbURL = testReport.getUrl();
			conn = DriverManager.getConnection(dbURL, user, password);
			if (conn == null) {
				System.out.println("Connection Failed");
			} else {

				Statement s1 = conn.createStatement();
				ResultSet rs = s1.executeQuery(
						"select * from RxCl where  RxClKey IN (SELECT RxClKey FROM ProcedureCodeRxCl WHERE ProcedureCodeKey IN (SELECT ProcedureCodeKey FROM ProcedureCode WHERE description IN ('"
								+ procedure + "')))");

				if (rs != null) {
					while (rs.next()) {
						/*
						 * System.out.println("step2"); for(int i = 0; i <results.size() ;i++) {
						 * System.out.println("step3"); for(int j = 0; j <results.size();j++) {
						 */

						RxCl rxcl = new RxCl();
						if (rs.getInt(1) != 0)
							rxcl.setRxClKey(rs.getInt(1));
						if (rs.getString(2) != null)
							rxcl.setGpi(rs.getString(2).trim());

						rxClTable.add(rxcl);
						// results.set(j, rs.getString(i))(j, rs.getString(i));
						// results[j]=rs.getString(i);
						// System.out.println( parequest.getParequestkey(j)));
					}
					// System.out.println("\nRXCL Check list table has no of records:
					// "+rxClTable.size());
				}
				rs.close();
				s1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("Checklist table retrieved successfully");

		return rxClTable;
	}

}
