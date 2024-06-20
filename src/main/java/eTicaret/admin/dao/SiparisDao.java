package eTicaret.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eTicaret.admin.model.Siparis;
import eTicaret.admin.model.SiparisForm;

public class SiparisDao {

	private static final String INSERT_SIPARIS_SQL = "INSERT INTO siparisler (urunId, kullanici_id, odemeId, adet, siparisTarihi) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_SIPARIS_SQL = "SELECT * FROM siparisler WHERE siparisId = ?";
	private static final String DELETE_SIPARIS_SQL = "DELETE FROM siparisler WHERE siparisId = ?";
	private static final String UPDATE_SIPARIS_SQL = "UPDATE siparisler SET urunId = ?, kullanici_id = ?, odemeId = ?, adet = ?, siparisTarihi = ? WHERE siparisId = ?";
	private static final String LS_ALL_SIPARIS_SQL = "SELECT s.*, u.ad, u.soyad, r.urunAdi, o.odemeTuru FROM siparisler s "
			+ "JOIN kullanicilar u ON s.kullanici_id = u.kullanici_id " + "JOIN urunler r ON s.urunId = r.urunId "
			+ "JOIN odemeler o ON s.odemeId = o.odemeId";
	private static final String SEARCH_SIPARIS_SQL = "SELECT s.*, u.ad, u.soyad, r.urunAdi, o.odemeTuru FROM siparisler s "
			+ "JOIN kullanicilar u ON s.kullanici_id = u.kullanici_id " + "JOIN urunler r ON s.urunId = r.urunId "
			+ "JOIN odemeler o ON s.odemeId = o.odemeId "
			+ "WHERE u.ad LIKE ? OR u.soyad LIKE ? OR r.urunAdi LIKE ? OR o.odemeTuru LIKE ?";

	Connection connection;

	public SiparisDao(Connection connection) {
		this.connection = connection;
	}

	public void create(Siparis siparis) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_SIPARIS_SQL)) {
			statement.setInt(1, siparis.getUrunId());
			statement.setInt(2, siparis.getKullaniciId());
			statement.setInt(3, siparis.getOdemeId());
			statement.setInt(4, siparis.getAdet());
			statement.setDate(5, new java.sql.Date(siparis.getSiparisTarih().getTime()));
			statement.executeUpdate();
		}
	}

	public List<Siparis> list() throws SQLException {
		List<Siparis> siparisler = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(LS_ALL_SIPARIS_SQL);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Siparis siparis = new Siparis();
				siparis.setId(resultSet.getInt("siparisId"));
				siparis.setUrunId(resultSet.getInt("urunId"));
				siparis.setKullaniciId(resultSet.getInt("kullanici_id"));
				siparis.setOdemeId(resultSet.getInt("odemeId"));
				siparis.setAdet(resultSet.getInt("adet"));
				siparis.setSiparisTarih(resultSet.getDate("siparisTarihi"));

				siparis.setKullaniciAdi(resultSet.getString("ad"));
				siparis.setKullaniciSoyadi(resultSet.getString("soyad"));
				siparis.setUrunAdi(resultSet.getString("urunAdi"));
				siparis.setOdemeTuru(resultSet.getString("odemeTuru"));

				siparisler.add(siparis);
			}
		}
		return siparisler;
	}

	public List<Siparis> search(String sorgu) throws SQLException {
		List<Siparis> siparisler = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SEARCH_SIPARIS_SQL)) {
			String esnekSorgu = "%" + sorgu + "%";
			statement.setString(1, esnekSorgu);
			statement.setString(2, esnekSorgu);
			statement.setString(3, esnekSorgu);
			statement.setString(4, esnekSorgu);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("siparisId");
					int urunId = resultSet.getInt("urunId");
					int kullaniciId = resultSet.getInt("kullanici_id");
					int odemeId = resultSet.getInt("odemeId");
					int adet = resultSet.getInt("adet");
					Date siparisTarih = resultSet.getDate("siparisTarihi");

					Siparis siparis = new Siparis();
					siparis.setId(id);
					siparis.setUrunId(urunId);
					siparis.setKullaniciId(kullaniciId);
					siparis.setOdemeId(odemeId);
					siparis.setAdet(adet);
					siparis.setSiparisTarih(siparisTarih);

					siparis.setKullaniciAdi(resultSet.getString("ad"));
					siparis.setKullaniciSoyadi(resultSet.getString("soyad"));
					siparis.setUrunAdi(resultSet.getString("urunAdi"));
					siparis.setOdemeTuru(resultSet.getString("odemeTuru"));

					siparisler.add(siparis);
				}
			}
		}
		return siparisler;
	}

	public void update(Siparis siparis) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_SIPARIS_SQL)) {
			statement.setInt(1, siparis.getUrunId());
			statement.setInt(2, siparis.getKullaniciId());
			statement.setInt(3, siparis.getOdemeId());
			statement.setInt(4, siparis.getAdet());
			statement.setDate(5, new java.sql.Date(siparis.getSiparisTarih().getTime()));
			statement.setInt(6, siparis.getId());
			statement.executeUpdate();
		}
	}

	public void delete(int siparisId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(DELETE_SIPARIS_SQL)) {
			statement.setInt(1, siparisId);
			statement.executeUpdate();
		}
	}

	public Siparis read(int siparisId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(SELECT_SIPARIS_SQL)) {
			statement.setInt(1, siparisId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Siparis siparis = new Siparis();
					siparis.setId(resultSet.getInt("siparisId"));
					siparis.setUrunId(resultSet.getInt("urunId"));
					siparis.setKullaniciId(resultSet.getInt("kullanici_id"));
					siparis.setOdemeId(resultSet.getInt("odemeId"));
					siparis.setAdet(resultSet.getInt("adet"));
					siparis.setSiparisTarih(resultSet.getDate("siparisTarihi"));
					return siparis;
				}
			}
		}
		return null;
	}
	
	// TODO formdaki dropdownlari doldurmak icin bu fonksiyonu implemente et
	public SiparisForm getForm(){
		return null;
	}
}
