package eTicaret.admin.model;

public class Kullanici {
	private int Id;
	private String Ad;
	private String Soyad;
	private String Email;
	private String Password;
	
	public Kullanici(int id, String ad, String soyad, String email, String password) {
		setId(id);
		setAd(ad);
		setSoyad(soyad);
		setEmail(email);
		setPassword(password);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAd() {
		return Ad;
	}

	public void setAd(String ad) {
		Ad = ad;
	}

	public String getSoyad() {
		return Soyad;
	}

	public void setSoyad(String soyad) {
		Soyad = soyad;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
