package eTicaret.admin.model;

public class Urun {
	private int id;
	private String ad;
	private String aciklama;
	private double fiyat;
	private int stokMiktar;
	private int kategoriId;
	
	private String kategoriAdi;

	public Urun(int id, String ad, String aciklama, double fiyat, int stokMiktar, int kategoriId) {
		setId(id);
		setAd(ad);
		setAciklama(aciklama);
		setFiyat(fiyat);
		setStokMiktar(stokMiktar);
		setKategoriId(kategoriId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
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

	public String getKategoriAdi() {
		return kategoriAdi;
	}

	public void setKategoriAdi(String kategoriAdi) {
		this.kategoriAdi = kategoriAdi;
	}
}
