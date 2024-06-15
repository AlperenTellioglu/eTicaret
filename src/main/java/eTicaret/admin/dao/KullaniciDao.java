package eTicaret.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eTicaret.admin.model.Kullanici;
import eTicaret.configuration.DatabaseConfiguration;

public class KullaniciDao {
	private static final String INSERT_KULLANICI_SQL = "INSERT INTO kullanicilar (ad, soyad, email, password) VALUES (?, ?, ?, ?)";
	private static final String SELECT_KULLANICI_SQL = "SELECT kullanici_id, ad, soyad, email, password FROM kullanicilar WHERE kullanici_id = ?";
	private static final String LS_ALL_KULLANICI_SQL = "SELECT * FROM kullanicilar";
	private static final String DELETE_KULLANICI_SQL = "DELETE FROM kullanicilar WHERE kullanici_id = ?";
	private static final String UPDATE_KULLANICI_SQL = "UPDATE kullanicilar SET ad = ?, soyad = ?, email = ?, password = ? WHERE kullanici_id = ?";

	public void create(Kullanici kullanici) throws SQLException {
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KULLANICI_SQL)) {
			preparedStatement.setString(1, kullanici.getAd());
			preparedStatement.setString(2, kullanici.getSoyad());
			preparedStatement.setString(3, kullanici.getEmail());
			preparedStatement.setString(4, kullanici.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Kullanici read(int id) {
		Kullanici kullanici = null;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KULLANICI_SQL)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String email = rs.getString("email");
				String password = rs.getString("password");
				kullanici = new Kullanici(id, ad, soyad, email, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kullanici;
	}

	public List<Kullanici> list() {
		List<Kullanici> users = new ArrayList<>();
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LS_ALL_KULLANICI_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("kullanici_id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String email = rs.getString("email");
				String password = rs.getString("password");
				users.add(new Kullanici(id, ad, soyad, email, password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_KULLANICI_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(Kullanici kullanici) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_KULLANICI_SQL)) {
			statement.setString(1, kullanici.getAd());
			statement.setString(2, kullanici.getSoyad());
			statement.setString(3, kullanici.getEmail());
			statement.setString(4, kullanici.getPassword());
			statement.setInt(5, kullanici.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}