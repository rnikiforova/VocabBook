package models;

public class Word {
	private long id;
	private String word;
	private String meaning;
	private String language;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
		
	@Override
	public String toString() {
		return "[" + this.language.toUpperCase() + "] " + this.word + " - " + this.getMeaning();
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
}
