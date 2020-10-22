import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;


public class ConnectDB {
	public Connection getConnection() throws SQLException {
		SQLiteConfig config = new SQLiteConfig();
		// config.setReadOnly(true);
		config.setSharedCache(true);
		config.enableRecursiveTriggers(true);

		SQLiteDataSource ds = new SQLiteDataSource(config);
		ds.setUrl("jdbc:sqlite:Bank.db");
		return ds.getConnection();
	}

	// insert data
	public void insert(Connection con, String acc, String pass, double bal) throws SQLException {
		String sql = "insert into customer (account_number,password,balance) values(?,?,?)";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setString(idx++, acc);
		pst.setString(idx++, pass);
		pst.setDouble(idx, bal);
		pst.executeUpdate();
	}

	public int customer_count(Connection con) throws SQLException {
		String sql = "select count(*) as row_count from Customer";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		return rs.getInt("row_count");
	}

	public ResultSet selectAll(Connection con) throws SQLException {
		String sql = "select * from Customer";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		return rs;
	}
	
	public boolean exist_account(Connection con,String acc) throws SQLException {
		String sql = "select * from Customer where account_number='"+acc+"'";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		if (rs.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean check_account(Connection con, String acc, String pass) throws SQLException {
		String sql = "select * from Customer where account_number='"+acc+"' and password ='"+pass+"'";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		if (rs.next()) {
			return true;
		}
		else {
			return false;	
		}
	}
	
	public double getbalance(Connection con, String acc) throws SQLException {
		String sql = "select balance  from Customer Where account_number='"+acc+"'";
		Statement stat = null;
		ResultSet rs = null;
		stat = con.createStatement();
		rs = stat.executeQuery(sql);
		return rs.getDouble("balance");
	}
	
	// update data
	public void update(Connection con, String acc, double bal) throws SQLException {
		String sql = "update customer set balance = ? where account_number = ?";
		PreparedStatement pst = null;
		pst = con.prepareStatement(sql);
		int idx = 1;
		pst.setDouble(idx++, bal);
		pst.setString(idx++, acc);
		pst.executeUpdate();
	}

}
