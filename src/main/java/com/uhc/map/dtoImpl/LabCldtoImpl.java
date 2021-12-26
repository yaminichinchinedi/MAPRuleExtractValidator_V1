/* #######################################################
# Name: Automation script_rulevalidator                         #
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

import com.uhc.map.dto.AncillaryInfoCldto;
import com.uhc.map.dto.LabCldto;
import com.uhc.map.entity.AncillaryInfoCl;
import com.uhc.map.entity.LabCl;
import com.uhc.map.entity.ProcedureCode;
import com.uhc.map.entity.TestReport;

public class LabCldtoImpl implements LabCldto {

	public LabCldtoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<LabCl> retriveLabCl(ProcedureCode procedurecode, TestReport testReport) throws ClassNotFoundException {
		// TODO Auto-generated method stub

		List<LabCl> labClTable = new ArrayList<LabCl>();

		Connection conn = null;
		ResultSet resultSet = null;
		String procedure = procedurecode.getDescription();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String user = testReport.getUser();
			String password = testReport.getPwd();
			String dbURL = testReport.getUrl();
			conn = DriverManager.getConnection(dbURL, user, password);
			if (conn == null)
				System.out.println("Connection Failed");

			else {
				Statement s1 = conn.createStatement();
				ResultSet rs = s1.executeQuery(
						"select * from LabCl where  LabClKey IN (SELECT LabClKey FROM ProcedureCodeLabCl WHERE ProcedureCodeKey IN (SELECT ProcedureCodeKey FROM ProcedureCode WHERE description IN ('"
								+ procedure + "')))");

				if (rs != null) {
					while (rs.next()) {
						/*
						 * System.out.println("step2"); for(int i = 0; i <results.size() ;i++) {
						 * System.out.println("step3"); for(int j = 0; j <results.size();j++) {
						 */

						LabCl loinc = new LabCl();
						if (rs.getString(1) != null)
							loinc.setLabClKey(rs.getInt(1));
						if (rs.getString(2) != null)
							loinc.setName(rs.getString(2).trim());
						if (rs.getString(3) != null)
							loinc.setLabTestname(rs.getString(3).trim());
						if (rs.getString(4) != null)
							loinc.setNlpkey(rs.getString(4).trim());
						if (rs.getString(5) != null)
							loinc.setLoinc(rs.getString(5).trim());
						if (rs.getString(6) != null)
							loinc.setCpt(rs.getString(6).trim());
						if (rs.getString(7) != null)
							loinc.setName_exp(rs.getString(7).trim());

						labClTable.add(loinc);
						// results.set(j, rs.getString(i))(j, rs.getString(i));
						// results[j]=rs.getString(i);
						// System.out.println( parequest.getParequestkey(j)));
					}

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

		return labClTable;

	}

}
