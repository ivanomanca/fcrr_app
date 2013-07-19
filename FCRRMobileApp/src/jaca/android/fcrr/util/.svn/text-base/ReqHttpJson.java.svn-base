package jaca.android.fcrr.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import jaca.android.fcrr.util.Url;

public class ReqHttpJson {

	private final int TIMEOUT_MILLISEC = 10000;
	private String serverUrl;
	private final String requestKeyString = "req";

	/**
	 * Constructor
	 */
	public ReqHttpJson() {
		super();
		if(Url.localServer){this.serverUrl = Url.localIp + Url.serverPort + Url.localServerRootUrl;}
		else{this.serverUrl = Url.remoteIp + Url.serverPort + Url.remoteServerRootUrl;}
	}

	/**
	 * Richiesta Http GET
	 * @param url
	 * @param params
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<HashMap<String, String>> getSend(String url, JSONObject params) throws	JSONException, 
	ClientProtocolException, 
	IOException{
		
		//HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
		//HttpGet httpget = new HttpGet(SERVER_URL + url);
		//HttpResponse response = httpClient.execute(httpget);
		//HttpEntity entity = response.getEntity();

		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT_MILLISEC);
		HttpResponse response;
		String URL = this.serverUrl+url;

		HttpPost post = new HttpPost(URL);

		// trasformazione in stringa
		StringEntity se = new StringEntity(params.toString());  
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

		// inserimento nel post http
		post.setEntity(se);
		response = client.execute(post);
		InputStream in = response.getEntity().getContent();

		// stringa di risposta del server
		String returnString = convertStreamToString(in);

		// lista finale in uscita per la ListView
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		// parsing Json della stringa di risposta
		char s = '[';
		if(s == returnString.charAt(0)){
			// JSONArray
			JSONArray JSONlist = new JSONArray(returnString);

			// riempimento della lista
			for (int i = 0; i < JSONlist.length(); i++) {
				JSONObject jsonObj = JSONlist.getJSONObject(i);
				HashMap<String, String> hm = new HashMap<String, String>();
				Iterator<?> it = jsonObj.keys();
				while (it.hasNext()) {
					String k = (String)it.next();
					String v = jsonObj.getString(k);
					hm.put(k, v);
				}
				data.add(hm); 
			}
		}
		return data;
	}

	/**
	 * Richiesta Http POST
	 * @param url
	 * @param params
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public JSONObject postSend(String url, JSONObject params) throws	JSONException, 
	ClientProtocolException, 
	IOException{
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT_MILLISEC);
		HttpResponse response;
		String URL = this.serverUrl+url;
		HttpPost post = new HttpPost(URL);

		// trasformazione in stringa
		StringEntity se = new StringEntity(params.toString());  
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

		// inserimento nel post http
		post.setEntity(se);
		response = client.execute(post);
		InputStream in = response.getEntity().getContent();

		// stringa di risposta del server
		String returnString = convertStreamToString(in);

		// JSONObject
		JSONObject JSONObj = new JSONObject(returnString);
		return JSONObj;
	}

	public ArrayList<HashMap<String, String>> send(String req, JSONObject params) throws	JSONException, 
	ClientProtocolException, 
	IOException{
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT_MILLISEC);
		HttpResponse response;

		JSONObject json = new JSONObject();
		String URL = this.serverUrl+Url.indexUrl;

		HttpPost post = new HttpPost(URL);

		// riempimento oggetto json
		json.put(this.requestKeyString, req);
		json.put("params", params);

		// trasformazione in stringa
		StringEntity se = new StringEntity(json.toString());  
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

		// inserimento nel post http
		post.setEntity(se);
		response = client.execute(post);
		InputStream in = response.getEntity().getContent();

		// stringa di risposta del server
		String returnString = convertStreamToString(in);

		// lista finale in uscita per la ListView
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		// parsing Json della stringa di risposta
		char s = '[';
		if(s == returnString.charAt(0)){
			// JSONArray
			JSONArray JSONlist = new JSONArray(returnString);

			// riempimento della lista
			for (int i = 0; i < JSONlist.length(); i++) {
				JSONObject jsonObj = JSONlist.getJSONObject(i);
				HashMap<String, String> hm = new HashMap<String, String>();
				Iterator<?> it = jsonObj.keys();
				while (it.hasNext()) {
					String k = (String)it.next();
					String v = jsonObj.getString(k);
					hm.put(k, v);
				}
				data.add(hm); 
			}
		}else{
			// JSONObject
			//JSONObject JSONObj = new JSONObject(returnString);
			// ... data.add(...)
		}
		return data;
	}

	private String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				InputStreamReader istreamer = new InputStreamReader(is, "UTF-8");
				Reader reader = new BufferedReader(istreamer);
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {        
			return "";
		}
	}

	private String getCurrentIp() {
		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			return thisIp.getHostAddress();
		}catch(Exception e) {
			e.printStackTrace();
		}return "";
	}

}
