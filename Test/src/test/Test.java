package test;
/**
 * 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/**
 * @author Ivano Manca (ivano.manca@studio.unibo.it)
 *
 */
public class Test {

	/**
	 * Constructor
	 */
	public Test() {
		// GregorianCalendar test
		testCalendar();
		
		// Trip class test
		//testTrip();
		
		// HttpRequestTest
		//testHttpRequest();
	}

	
	private void testHttpRequest() {
		String user = "admin";
		String password = "password";
		
		HttpClient client = new DefaultHttpClient();
		final int TIMEOUT_MILLISEC = 10000;
		HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT_MILLISEC);
		HttpResponse response;
		
		JSONObject json = new JSONObject();
		final String URL = "localhost/prova.php";
		try{
	        HttpPost post = new HttpPost(URL);
	        json.put("user", user);
	        json.put("password", password);
	        
	        StringEntity se = new StringEntity(json.toString());  
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        post.setEntity(se);
	        response = client.execute(post);
	        InputStream in = response.getEntity().getContent();
	        
	        // Questo è il testo che il server mi ha inviato
	        String returnString = convertStreamToString(in);
	        // Faccio il parsing del JSON
	        JSONObject returnJson = new JSONObject(returnString);
	        // Leggo dal JSON il valore della chiave chiamata “result”
	        String result = returnJson.getString("result");
	        if(result.equals("success")){
	            //return true;
	        	System.out.println("successfull.");
	        } else {
	            //return false;
	        	System.out.println("failed.");
	        }
	    } catch(Exception e){
	        e.printStackTrace();
	        System.out.println("failed.");
	        //return false; 
	    }
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
	

	private void testTrip() {
		//Trip t = new Trip(1, "Ivano", "Bologna", "Torino", new GregorianCalendar(2012, 10, 10, 9, 0));
		Trip t = new Trip(1, "Ivano", "Bologna", "Torino", "10/10/2012 - 9:00");
		System.out.println(t.toString());
	}

	private void testCalendar() {
		// Stampa della data secondo il formato
		GregorianCalendar c = new GregorianCalendar(2012, 4, 30, 9, 30);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String dataString = sdf.format(c.getTime());
		System.out.println(dataString);
		
		// Settaggio della data secondo il formato
		System.out.println("------------------------------------");
		Trip t = new Trip(1, "testDriver", "Cesena", "Milano", c);
		System.out.println(t.toString());
		System.out.println("------------------------------------");
		System.out.println(t.getDepartureDateString());
		System.out.println("------------------------------------");
		t.setDepartureDate("07/02/1982 - 00:00");
		System.out.println(t.getDepartureDateString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}
}
