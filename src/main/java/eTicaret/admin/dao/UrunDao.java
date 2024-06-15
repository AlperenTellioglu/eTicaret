package eTicaret.admin.dao;

public class UrunDao {
	private static final String INSERT_URUN_SQL = "INSERT INTO urunler (aciklama, fiyat, stokMiktar, kategoriId) VALUES (?, ?, ?, ?);";
	private static final String SELECT_URUN_SQL = "SELECT id, aciklama, fiyat, stokMiktar, kategoriId FROM urunler WHERE id = ?;";
	private static final String LS_ALL_URUN_SQL = "SELECT * FROM urunler;";
	private static final String DELETE_URUN_SQL = "DELETE FROM urunler WHERE id = ?;";
	private static final String UPDATE_URUN_SQL = "UPDATE urunler SET aciklama = ?, fiyat = ?, stokMiktar = ?, kategoriId = ? WHERE id = ?;";
}
