package project.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import project.swing_view.Custom_Update_View;

public class Custom_Update_Controller {

	
	
public void Custom_Modify(String card) {
		
		Properties props = new Properties();
	      props.setProperty("JdbcUrl", "jdbc:oracle:thin:@localhost:1521/XEPDB1");
	      props.setProperty("dataSource.user", "hr");
	      props.setProperty("dataSource.password", "123");
	      props.setProperty("dataSource.databaseName", "XEPDB1");
	      props.put("dataSource.logWriter", new PrintWriter(System.out));

	      HikariConfig config = new HikariConfig(props);
	      HikariDataSource ds = new HikariDataSource(config); 
	      try {
	  
			Connection conn = ds.getConnection();
			String sql = "UPDATE CUSTOM SET name = ?, age = ?, gender =?,"
						+"email = ?, phone = ?, addr = ? where card = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Custom_Update_View.tName.getText());
			pstmt.setInt(2, Integer.parseInt(Custom_Update_View.tAge.getText()));
			pstmt.setString(3, Custom_Update_View.tGender.getText());
			pstmt.setString(4, Custom_Update_View.tEmail.getText());
			pstmt.setString(5, Custom_Update_View.tPhone.getText());
			pstmt.setString(6, Custom_Update_View.tAddr.getText());
			pstmt.setString(7, card);
			pstmt.executeUpdate();
			
			String sql2 = "UPDATE blacklist SET name = ?, age = ?, gender =?,"
						+ "email = ?, phone = ?, addr = ? where card = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, Custom_Update_View.tName.getText());
			pstmt2.setInt(2, Integer.parseInt(Custom_Update_View.tAge.getText()));
			pstmt2.setString(3, Custom_Update_View.tGender.getText());
			pstmt2.setString(4, Custom_Update_View.tEmail.getText());
			pstmt2.setString(5, Custom_Update_View.tPhone.getText());
			pstmt2.setString(6, Custom_Update_View.tAddr.getText());
			pstmt2.setString(7, card);
			pstmt2.executeUpdate();
			
			if(pstmt != null) pstmt.close();
			if(pstmt2 != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
