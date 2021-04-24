package com.shortURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inp;
		myURLShortner u = new myURLShortner(5, "www.myurl.com/");
		while(true) {
			System.out.println("Give an URL to shorten or Enter 1 to exit\n");
			inp=br.readLine();
			if(inp.equals("1")) {
				break;
			}
			else {
				String shortURL=u.shortenURL(inp);
				System.out.println("URL:" + inp + "\nshort url: "+ shortURL + "\nExpanded URL: "
						+ u.expandURL(shortURL));
			}
		}
	}
}
