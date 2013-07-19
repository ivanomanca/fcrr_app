package jaca.android.fcrr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.OverlayItem;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;
import cartago.OpFeedbackParam;
import cartago.OperationException;
import android.os.Bundle;
import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import jaca.android.fcrr.util.HelloItemizedOverlay;
import jaca.android.fcrr.util.JsonParam;
import jaca.android.fcrr.util.MapPoint;
import jaca.android.fcrr.util.HttpRequestLinkedOperations;
import jaca.android.fcrr.util.Url;

public class DTripMapGUIArtifact extends GUIArtifact{

	private DTripMapActivity activityRef;
	private JSONObject joTripID;
	private OpFeedbackParam<ArrayList<HashMap<String, String>>> infoDriver;
	private OpFeedbackParam<ArrayList<HashMap<String, String>>> riderList;
	private OpFeedbackParam<OverlayItem[]> overlayItemArray;

	protected void init(IJaCaActivity activity, Bundle savedInstanceState){
		super.init(activity, savedInstanceState);

		// ricavo l'attività associata
		activityRef = (DTripMapActivity)activity;

		// definisco una proprietà osservabile (visualizzazione GUI)
		defineObsProperty("gui_state", "not_displayed");

		// Recuperdo l'id del TRIP

		// recupero dal db le posizioni correnti dei carpooler
		//OverlayItem[] overlayItemArray = new OverlayItem[] {
		//		new OverlayItem(new MapPoint("44.140257,12.232654").getGeoPoint(), "Driver", "viaMatteotti"),
		//		new OverlayItem(new MapPoint("44.152113,12.240186").getGeoPoint(), "Rider1", "viaGenova"),
		//		new OverlayItem(new MapPoint("44.139564,12.23907").getGeoPoint(), "Rider2", "viaCairoli"),
		//		new OverlayItem(new MapPoint("44.142413,12.239864").getGeoPoint(), "Rider3", "viaMulini")
		//};
		// genero per la prima volta gli overlay
		//activityRef.generateOverlays(overlayItemArray);
		// ricavo gli elementi di view


		// pressione pulsanti - linkingOnClickToOp
		//linkOnClickEventToOp(driverButton, "onClick");
		//linkOnClickEventToOp(riderButton, "onClick");
	}

	@OPERATION void updateView(	ArrayList<HashMap<String,String>> carpoolerInfo, 
								ArrayList<HashMap<String,String>> ridersInfoList){
		// passo all'aggiornamento della mappa
		activityRef.updateMap(carpoolerInfo, ridersInfoList);
	}

	/*
	@INTERNAL_OPERATION void getDriverInfo(JSONObject joTripID, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> infoDriver){
		try {
			infoDriver = new OpFeedbackParam<ArrayList<HashMap<String,String>>>();
			execLinkedOp(	"out-1", HttpRequestLinkedOperations.getHttpReqLinked,
					Url.getTripDriverInfo, joTripID, infoDriver);
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoDriver.set(infoDriver.get());
	}*/

	/*
	@INTERNAL_OPERATION void getRidersInfo(JSONObject joTripID, 
			OpFeedbackParam<ArrayList<HashMap<String, String>>> riderList){
		try {
			riderList = new OpFeedbackParam<ArrayList<HashMap<String,String>>>();
			execLinkedOp(	"out-1", HttpRequestLinkedOperations.getHttpReqLinked,
					Url.getTripRiderList, joTripID, riderList);
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		riderList.set(riderList.get());
	}*/

	/*
	@INTERNAL_OPERATION void retrieveMapElements(JSONObject joTripID, 
			OpFeedbackParam<OverlayItem[]> overlayItemArray){
		// 1. recupero posizione, nome, cognome, driver da db
		execInternalOp(	"getDriverInfo", joTripID, infoDriver);
		// recupero dal db le info sul driver e sui rider
		// non ancora checked-in, da invocare dopo ogni check-in
		// e dopo ogni accettazione di una reservation
		execInternalOp(	"getRidersInfo", joTripID, riderList);	

		ArrayList<HashMap<String, String>> alDriver = infoDriver.get();
		ArrayList<HashMap<String, String>> alRiderList = riderList.get();

		// hashmap del driver
		HashMap<String, String> hmDriver = alDriver.get(0);
		OverlayItem oiDriver = new OverlayItem(
				new MapPoint(hmDriver.get(JsonParam.Carpooler.geopoint)).getGeoPoint(),
				"DriverNomeCognome",
				"Commento...");

		// creo una overlaylist dimensionata driver + numero riders
		OverlayItem[] overlayArray = new OverlayItem[alRiderList.size()+1];
		// inserisco il driver
		overlayArray[0] = oiDriver;
		// inserisco i riders
		Iterator<HashMap<String, String>> iterator = alRiderList.iterator();
		int i=1;
		while (iterator.hasNext()) {
			HashMap<String,String> hmRider = iterator.next();
			overlayArray[i] = new OverlayItem(
					new MapPoint(hmRider.get(JsonParam.Reservation.pick_up_geopoint)).getGeoPoint(),
					"RiderNomeCognome",
					"Commento...");
			i++;
		}
		overlayItemArray.set(overlayArray);
	}*/

	@INTERNAL_OPERATION void onStart(){
		ObsProperty obsProp = getObsProperty("gui_state");
		obsProp.updateValue("displayed");
	}

	@INTERNAL_OPERATION void onStop(){
		ObsProperty obsProp = getObsProperty("gui_state");
		obsProp.updateValue("not_displayed");
	}
}
