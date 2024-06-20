package eTicaret.admin.model;

import java.util.List;

public class SiparisForm {
	private List<Kullanici> kullanicilar;
	private List<Urun> urunler;
	private List<Odeme> odemeler;
	
	public SiparisForm() {
		
	}
	
	public SiparisForm(List<Kullanici> kullanicilar, List<Urun> urunler, List<Odeme> odemeler) {
		super();
		this.kullanicilar = kullanicilar;
		this.urunler = urunler;
		this.odemeler = odemeler;
	}
	
	public List<Kullanici> getKullanicilar() {
		return kullanicilar;
	}
	public void setKullanicilar(List<Kullanici> kullanicilar) {
		this.kullanicilar = kullanicilar;
	}
	public List<Urun> getUrunler() {
		return urunler;
	}
	public void setUrunler(List<Urun> urunler) {
		this.urunler = urunler;
	}
	public List<Odeme> getOdemeler() {
		return odemeler;
	}
	public void setOdemeler(List<Odeme> odemeler) {
		this.odemeler = odemeler;
	}
}
