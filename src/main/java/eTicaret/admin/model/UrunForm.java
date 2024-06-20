package eTicaret.admin.model;

import java.util.List;

public class UrunForm {
	private List<Kategori> kategoriler;

	public UrunForm() {

	}

	public UrunForm(List<Kategori> kategoriler) {
		super();
		this.kategoriler = kategoriler;
	}

	public List<Kategori> getKategoriler() {
		return kategoriler;
	}

	public void setKategoriler(List<Kategori> kategoriler) {
		this.kategoriler = kategoriler;
	}
}
