package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import bean.Bar;

public class BarDao {
	public String query() {
		ArrayList<Bar> barArr = new ArrayList<Bar>();
		try {
			// JDBC方式连接SQLSERVER数据库
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:sqlserver://mydbinstance.ckdcs3qtrx5i.us-east-2.rds.amazonaws.com:1433;databaseName=test",
					"liji", "lj199703");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bar");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Bar bar = new Bar();
				bar.setName(rs.getString("name"));
				bar.setNum(rs.getInt("num"));
				barArr.add(bar);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String str = gson.toJson(barArr);

		return str;
	}

	public void update(String name, Integer num) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:sqlserver://mydbinstance.ckdcs3qtrx5i.us-east-2.rds.amazonaws.com:1433;databaseName=test",
					"liji", "lj199703");
			PreparedStatement stmt = conn
					.prepareStatement("update bar set num='" + num + "' where name='" + name + "'");
			stmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
