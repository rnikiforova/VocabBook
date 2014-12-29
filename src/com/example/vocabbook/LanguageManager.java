package com.example.vocabbook;

public final class LanguageManager {
	
	private static final String en = "en";
	private static final String fr = "fr";
	private static final String de = "de";
	private static final String bg = "bg";
	
	private LanguageManager() {
		
	}
	
	public static String getLanguageCode(String lang) throws Exception {
		String langLower = lang.toLowerCase();
		String result = "";
		
		if(lang.length() == 0 || lang == null) {
			throw new Exception("Language cannot be null");
		}
		
		switch(langLower) {
			case "english": result = en; break;
			case "german": result = de; break;
			case "french": result = fr; break;
			case "bulgarian": result = bg; break;
			default : result = en; break;
		}
		
		return result;
	}
}
