package com.shortURL;

import java.util.HashMap;
import java.util.Random;

public class myURLShortner {
	private HashMap<String, String> keyLongURL;
	private HashMap<String, String> keyShortURL;
	private String domain;
	private char myChars[];
	private Random myRand;
	private int keyLength;

	myURLShortner() {
		keyLongURL = new HashMap<String, String>();
		keyShortURL = new HashMap<String, String>();
		myRand = new Random();
		keyLength = 8;
		myChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			myChars[i] = (char) j;
		}
		domain = "www.myurl.com/";
	}
	myURLShortner(int length, String newDomain) {
		this();
		this.keyLength = length;
		if (!newDomain.isEmpty()) {
			newDomain = removehttp(newDomain);
			domain = newDomain;
		}
	}

	public String shortenURL(String longURL) {
		String shortURL = "";
			longURL = removehttp(longURL);
			if (keyShortURL.containsKey(longURL)) {
				shortURL = domain + "/" + keyShortURL.get(longURL);
			} else {
				shortURL = domain + "/" + getKey(longURL);
			}
		return shortURL;
	}
	public String expandURL(String shortURL) {
		String longURL = "";
		String key = shortURL.substring(domain.length() + 1);
		longURL = keyLongURL.get(key);
		return longURL;
	}

	String removehttp(String url) {
		if (url.substring(0, 7).equals("http://"))
			url = url.substring(7);

		if (url.substring(0, 8).equals("https://"))
			url = url.substring(8);

		if (url.charAt(url.length() - 1) == '/')
			url = url.substring(0, url.length() - 1);
		return url;
	}

	private String getKey(String longURL) {
		String key;
		key = generateKey();
		keyLongURL.put(key, longURL);
		keyShortURL.put(longURL, key);
		return key;
	}

	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += myChars[myRand.nextInt(62)];
			}
			if (!keyLongURL.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}

	
}