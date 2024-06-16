package eTicaret.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eTicaret.admin.model.Urun;
import eTicaret.configuration.DatabaseConfiguration;

public class UrunDao {
	private static final String INSERT_URUN_SQL = "INSERT INTO urunler (urunAdi, urunAciklamasi, urunFiyati, urunStokMiktari, kategoriId) VALUES (?, ?, ?, ?, ?);";
	private static final String SELECT_URUN_SQL = "SELECT urunId, urunAdi, urunAciklamasi, urunFiyati, urunStokMiktari, kategoriId FROM urunler WHERE urunId = ?;";
	private static final String LS_ALL_URUN_SQL = "SELECT * FROM urunler;";
	private static final String DELETE_URUN_SQL = "DELETE FROM urunler WHERE urunId = ?;";
	private static final String UPDATE_URUN_SQL = "UPDATE urunler SET urunAdi = ?, urunAciklamasi = ?, urunFiyati = ?, urunStokMiktari = ?, kategoriId = ? WHERE urunId = ?;";

	public void create(Urun urun) throws SQLException {
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_URUN_SQL)) {
			preparedStatement.setString(1, urun.getAd());
			preparedStatement.setString(2, urun.getAciklama());
			preparedStatement.setDouble(3, urun.getFiyat());
			preparedStatement.setInt(4, urun.getStokMiktar());
			preparedStatement.setInt(5, urun.getKategoriId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Urun read(int id) {
		Urun urun = null;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_URUN_SQL)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String ad = rs.getString("urunAdi");
				String aciklama = rs.getString("urunAciklamasi");
				double fiyat = rs.getDouble("urunFiyati");
				int stokMiktar = rs.getInt("urunStokMiktari");
				int kategoriId = rs.getInt("kategoriId");
				urun = new Urun(id, ad, aciklama, fiyat, stokMiktar, kategoriId);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return urun;
	}

	public List<Urun> list() {
		List<Urun> urunler = new ArrayList<>();
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LS_ALL_URUN_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("urunId");
				String ad = rs.getString("urunAdi");
				String aciklama = rs.getString("urunAciklamasi");
				double fiyat = rs.getDouble("urunFiyati");
				int stokMiktar = rs.getInt("urunStokMiktari");
				int kategoriId = rs.getInt("kategoriId");
				urunler.add(new Urun(id, ad, aciklama, fiyat, stokMiktar, kategoriId));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return urunler;
	}

	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_URUN_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(Urun urun) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DatabaseConfiguration.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_URUN_SQL)) {
			statement.setString(1, urun.getAd());
			statement.setString(2, urun.getAciklama());
			statement.setDouble(3, urun.getFiyat());
			statement.setInt(4, urun.getStokMiktar());
			statement.setInt(5, urun.getKategoriId());
			statement.setInt(6, urun.getId());
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
