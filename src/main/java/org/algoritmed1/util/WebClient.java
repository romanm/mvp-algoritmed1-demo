package org.algoritmed1.util;
//package hol2eih4;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component("webClient")
@Component
public class WebClient {
	private static final Logger logger = LoggerFactory.getLogger(WebClient.class);
	private @Value("${config.insurance.server}") String configInsuranceServer;

	String url = "http://hol.curepathway.com/pushedWebNewDrug";

	ObjectMapper mapper = new ObjectMapper();


	void test2(){
		Map<String, Object> drug = new HashMap<>();
		pushMapToUrl(url, drug);
	}

	public void hello(){
		System.out.println("----------------------");
		logger.info("hello web client");
		System.out.println("----------------------");
	}

	/**
	 * Зчитування даних з сервера 
	 * @param url REST адреса для зчитування даних
	 * @return данні згідно наданого запиту
	 */
	public Map<String, Object> getFromUrl(String url) {
		Map mapResponsed = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
//			private final String USER_AGENT = "Mozilla/5.0";
			//		con.setRequestProperty("User-Agent", USER_AGENT);
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);
			InputStream requestBody = con.getInputStream();
			mapResponsed = mapper.readValue(requestBody, Map.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapResponsed;
	}

	private HttpURLConnection sendToUrl(String requestMethod, String url) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(requestMethod);
			con.setDoOutput(true);
//			con.setRequestProperty("Content-Type", "application/json"); 
//			con.setRequestProperty("charset", "utf-8");
			Map<String, Object> map = new HashMap<>();
			mapper.writeValue(con.getOutputStream(), map);
			return con;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	Map<String, Object> pushMapToUrl(String url, Map<String, Object> mapToPost) {
		logger.debug(url);
		HttpURLConnection con = sendToUrl("POST", mapToPost, url);
		logger.debug(""+con);
		Map<String, Object> mapResponsed = null;
		if(null != con){
			try {
				InputStream requestBody = con.getInputStream();
				mapResponsed = mapper.readValue(requestBody, Map.class);
				logger.debug(""+mapResponsed);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mapResponsed;
	}

	private HttpURLConnection sendToUrl(String requestMethod, Map<String, Object> map, String url) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(requestMethod);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json"); 
			con.setRequestProperty("charset", "utf-8");
			mapper.writeValue(con.getOutputStream(), map);
			return con;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
