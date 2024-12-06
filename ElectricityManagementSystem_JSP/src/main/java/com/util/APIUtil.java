package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class APIUtil {
	
	public static HttpURLConnection createConnection(String Url, String method) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(Url);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static String getResponse(HttpURLConnection con) {
		StringBuilder str = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output = null;
			while ((output = reader.readLine()) != null)
				str.append(output);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (con != null)
			{
				con.disconnect();
			}
		}
		return str.toString();
	}
}
