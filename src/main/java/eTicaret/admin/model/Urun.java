package eTicaret.admin.model;

public class Urun {
	private int id;
	private String aciklama;
	private double fiyat;
	private int stokMiktar;
	private int kategoriId;

	public Urun(int id, String aciklama, double fiyat, int stokMiktar, int kategoriId) {
		super();
		this.id = id;
		this.aciklama = aciklama;
		this.fiyat = fiyat;
		this.stokMiktar = stokMiktar;
		this.kategoriId = kategoriId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public double getFiyat() {
		return fiyat;
	}

	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}

	public int getStokMiktar() {
		return stokMiktar;
	}

	public void setStokMiktar(int stokMiktar) {
		this.stokMiktar = stokMiktar;
	}

	public int getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(int kategoriId) {
		this.kategoriId = kategoriId;
	}
}
