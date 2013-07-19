package jaca.android.fcrr;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OUTPORT;
import cartago.OpFeedbackParam;
import cartago.OperationException;

import jaca.android.dev.JaCaArtifact;
import jaca.android.fcrr.util.Url;
import jaca.android.fcrr.util.JsonParam;
import jaca.android.fcrr.util.HttpRequestLinkedOperations;

@ARTIFACT_INFO(
		outports = {
			@OUTPORT(name = "out-1")
		}
) public class FCRRServiceArtifact extends JaCaArtifact {

	//private ArrayList<HashMap<String, String>> list;
	private OpFeedbackParam<JSONObject> obj;
	
	protected void init(){
		//log(" Init passed.");
	}
	
	@OPERATION void updateCarpoolerGPS(int userID, double lat, double lng){
		
		JSONObject params = new JSONObject();
		JSONObject update = new JSONObject();
		JSONObject select = new JSONObject();
		try {
			update.put(JsonParam.Carpooler.geopoint, lng+","+lat);
			select.put(JsonParam.id, userID);
			params.put(JsonParam.updateParams, update);
			params.put(JsonParam.selectParams, select);	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			execLinkedOp(	"out-1", 
							HttpRequestLinkedOperations.postHttpReqLinked, 
							Url.updateCarpoolerGPS, 
							params, 
							obj);
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OPERATION void retrieveTripList(int userID, 
						OpFeedbackParam<ArrayList<HashMap<String, String>>> outlist){
		// costruisco l'oggetto json che andrà in input
		JSONObject params = new JSONObject();
		JSONObject select = new JSONObject();
		try {
			select.put(JsonParam.Trip.id_driver, userID);
			params.put(JsonParam.selectParams, select);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// istanzio la lista di uscita
		OpFeedbackParam<ArrayList<HashMap<String, String>>> list = new OpFeedbackParam<ArrayList<HashMap<String, String>>>();
		try {
	        execLinkedOp(	"out-1", 
	        				HttpRequestLinkedOperations.getHttpReqLinked,
	        				Url.getDriverTripList,
	        				params, 
	        				list);
	        //log("printing response: "+list.get());
	      } catch (Exception ex){
	        ex.printStackTrace();
	      }
		outlist.set(list.get());
	}
	
	@OPERATION void retrieveReservationList(JSONObject joTripID, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> outRiders){
		// costruisco l'oggetto json che andrà in input
		JSONObject params = new JSONObject();
		try {
			params.put(JsonParam.selectParams, joTripID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// istanzio la lista dei riders
		OpFeedbackParam<ArrayList<HashMap<String,String>>> riders = new OpFeedbackParam<ArrayList<HashMap<String,String>>>();		
		try {
		execLinkedOp(	"out-1",
						HttpRequestLinkedOperations.getHttpReqLinked,
						Url.getTripRiderList,
						params,
						riders);
		//log("printing response: "+riders.get());
		} catch (Exception ex){
			ex.printStackTrace();
		}
		outRiders.set(riders.get());
	}
	
	@OPERATION void retrieveTrip(	JSONObject joTripID, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> outInfoTrip){
		
		// costruisco l'oggetto json che andrà in input
		JSONObject params = new JSONObject();
		try {
			params.put(JsonParam.selectParams, joTripID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// istanzio la "lista" delle info sul viaggio
		OpFeedbackParam<ArrayList<HashMap<String,String>>> infoTrip = new OpFeedbackParam<ArrayList<HashMap<String,String>>>();		
		try {
		execLinkedOp(	"out-1",
						HttpRequestLinkedOperations.getHttpReqLinked,
						Url.getTripDriverInfo,
						params,
						infoTrip);
		//log("printing response: "+infoTrip.get());
		} catch (Exception ex){
		ex.printStackTrace();
		}
		outInfoTrip.set(infoTrip.get());
	}
	
	@OPERATION void retrieveCarpooler(	int userID, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> outInfoCarpooler){
		
		// costruisco l'oggetto json che andrà in input
		JSONObject params = new JSONObject();
		JSONObject select = new JSONObject();
		try {
			select.put(JsonParam.Carpooler.id, userID);
			params.put(JsonParam.selectParams, select);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// istanzio la "lista" delle info carpooler
		OpFeedbackParam<ArrayList<HashMap<String,String>>> infoCarpooler = new OpFeedbackParam<ArrayList<HashMap<String,String>>>();		
		try {
		execLinkedOp(	"out-1",
						HttpRequestLinkedOperations.getHttpReqLinked,
						Url.getCarpoolerInfo,
						params,
						infoCarpooler);
		//log("printing response: "+infoCarpooler.get());
		} catch (Exception ex){
		ex.printStackTrace();
		}
		outInfoCarpooler.set(infoCarpooler.get());
	}
	
}
