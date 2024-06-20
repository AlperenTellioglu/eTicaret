package eTicaret.admin.model;

public class Odeme {
	private int id;
	private String odemeTuru;
	
	public Odeme() {
		
	}
	
	public Odeme(int id, String odemeTuru){
		setId(id);
		setOdemeTuru(odemeTuru);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOdemeTuru() {
		return odemeTuru;
	}
	public void setOdemeTuru(String odemeTuru) {
		this.odemeTuru = odemeTuru;
	}
}
