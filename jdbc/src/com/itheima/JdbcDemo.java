package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo {


	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// JDBC ドライブロード
			Class.forName("com.mysql.jdbc.Driver");

			// JDBCでＤＢに接続する。
			connection = DriverManager.getConnection("jdbc:mysql://192.168.0.12:3306/mybatis?characterEncoding=utf-8", "root", "Mysql_pwd!");
			// SQL文の設定
			String sql = "select * from user where username = ?";
			// statement
			preparedStatement = connection.prepareStatement(sql);
			// パラメータの設定
			preparedStatement.setString(1, "千葉　太郎");
			// DB検索を実施する。
			resultSet = preparedStatement.executeQuery();
			// 検索結果を取得する。
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + "  " + resultSet.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// リソースを解除
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
