package eTicaret.admin;

public class Kullanici {
	public int Id;
	public String Ad;
	public String Soyad;
	public String Email;
	public String Password;
	
	public Kullanici(int id, String ad, String soyad, String email, String password) {
		Id= id;
		Ad = ad;
		Soyad = soyad;
		Email = email;
		Password = password;
	}
}
