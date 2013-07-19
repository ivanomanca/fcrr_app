package jaca.android.fcrr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import cartago.LINK;
import cartago.OpFeedbackParam;
import jaca.android.dev.JaCaArtifact;
import jaca.android.fcrr.util.ReqHttpJson;

public class HttpManagerArtifact extends JaCaArtifact {

	private ArrayList<HashMap<String, String>> list;
	private ArrayList<HashMap<String, String>> responseList;
	private JSONObject responseObj;

	protected void init(){
		//log(" Init passed.");
	}

	/**
	 * Richiesta HTTP GET al Service
	 * @param url
	 * @param params
	 * @param responseList
	 */
	@LINK void getHttpRequest(	String url, 
								JSONObject params,
								OpFeedbackParam<ArrayList<HashMap<String, String>>> outList){
		ReqHttpJson request = new ReqHttpJson();
		responseList = new ArrayList<HashMap<String, String>>();
		try {
			responseList = request.getSend(url, params);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outList.set(responseList);
	}	

	/**
	 * Richiesta HTTP POST al Service
	 * @param url
	 * @param params
	 * @param obj
	 */
	@LINK void postHttpRequest(	String url, 
								JSONObject params,
								OpFeedbackParam<JSONObject> obj){
		ReqHttpJson request = new ReqHttpJson();
		try {
			responseObj = request.postSend(url, params);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.set(responseObj);
	}

	@LINK void httpRequest(	String opRequest,
			JSONObject params, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> responseList){

		ReqHttpJson request = new ReqHttpJson();

		// invio request e recupero response
		//ArrayList<HashMap<String, String>> lista = null;
		try {
			list = request.send(opRequest, params);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		responseList.set(list);
	}
}
