package eTicaret.admin.model;

import java.sql.Date;

public class Siparis {
	private int id;
	private int urunId;
	private int kullaniciId;
	private int odemeId;
	private int adet;
	private Date siparisTarih;
	
	private String kullaniciAdi;
	private String kullaniciSoyadi;
	private String urunAdi;
	private String odemeTuru;
	
	public Siparis() {
		
	}
	
	public Siparis(int id, int urunId, int kullaniciId, int odemeId, int adet, Date siparisTarih) {
		super();
		this.id = id;
		this.urunId = urunId;
		this.kullaniciId = kullaniciId;
		this.odemeId = odemeId;
		this.adet = adet;
		this.siparisTarih = siparisTarih;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUrunId() {
		return urunId;
	}
	public void setUrunId(int urunId) {
		this.urunId = urunId;
	}
	public int getKullaniciId() {
		return kullaniciId;
	}
	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}
	public int getOdemeId() {
		return odemeId;
	}
	public void setOdemeId(int odemeId) {
		this.odemeId = odemeId;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public Date getSiparisTarih() {
		return siparisTarih;
	}
	public void setSiparisTarih(Date siparisTarih) {
		this.siparisTarih = siparisTarih;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getKullaniciSoyadi() {
		return kullaniciSoyadi;
	}

	public void setKullaniciSoyadi(String kullaniciSoyadi) {
		this.kullaniciSoyadi = kullaniciSoyadi;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getOdemeTuru() {
		return odemeTuru;
	}

	public void setOdemeTuru(String odemeTuru) {
		this.odemeTuru = odemeTuru;
	}	
}
