package eTicaret.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eTicaret.admin.model.Kategori;
import eTicaret.configuration.DatabaseConfiguration;

public class KategoriDao {
	private static final String INSERT_KATEGORI_SQL = "INSERT INTO kategoriler (kategoriAdi) VALUES (?)";
	private static final String SELECT_KATEGORI_SQL = "SELECT kategoriId, kategoriAdi FROM kategoriler WHERE kategoriId = ?";
	private static final String LS_ALL_KATEGORI_SQL = "SELECT * FROM kategoriler;";
	private static final String DELETE_KATEGORI_SQL = "DELETE FROM kategoriler WHERE kategoriId = ?;";
	private static final String UPDATE_KATEGORI_SQL = "UPDATE kategoriler SET kategoriAdi = ? WHERE kategoriId = ?;";

	private Connection connection;
	
	public KategoriDao(Connection connection) {
		this.connection = connection;
	}
	
	public void create(Kategori kategori) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KATEGORI_SQL)) {
			preparedStatement.setString(1, kategori.getAd());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Kategori read(int id) {
		Kategori kategori = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KATEGORI_SQL)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String ad = rs.getString("kategoriAdi");
				kategori = new Kategori(id, ad);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kategori;
	}

	public List<Kategori> list() {
		List<Kategori> kategoriler = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(LS_ALL_KATEGORI_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("kategoriId");
				String ad = rs.getString("kategoriAdi");;
				kategoriler.add(new Kategori(id, ad));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kategoriler;
	}

	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (PreparedStatement statement = connection.prepareStatement(DELETE_KATEGORI_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(Kategori kategori) throws SQLException {
		boolean rowUpdated;
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_KATEGORI_SQL)) {
			statement.setString(1, kategori.getAd());
			statement.setInt(2, kategori.getId());
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
