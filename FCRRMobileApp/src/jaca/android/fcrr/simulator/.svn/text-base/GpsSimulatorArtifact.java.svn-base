package jaca.android.fcrr.simulator;

import java.util.HashMap;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;
import jaca.android.dev.LocationManagerArtifact;
import jaca.android.fcrr.util.MapPoint;

public class GpsSimulatorArtifact extends LocationManagerArtifact {

	public static final String OP_ON_LOCATION_CHANGE = "onLocationChange";
	public static final String ON_LOCATION_CHANGE = "onLocationChange";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String SENSOR_NAME = "gps-sensor";
	
	private Integer currentMapPoint;
	private HashMap<Integer, MapPoint> pathHashMap;
	
	
	protected void init(){
		//log(" Init passed.");
		defineObsProperty(LATITUDE, 0);
		defineObsProperty(LONGITUDE, 0);
		//linkOnLocationChangedEventToOp(SENSOR_NAME, OP_ON_LOCATION_CHANGE);
		
		// inizializzazione hasmap path
		currentMapPoint = 0;
		pathHashMap = new HashMap<Integer, MapPoint>();
		initPath();
		
	}

	@INTERNAL_OPERATION void onLocationChange() {
		// aggiornamento valori
		getObsProperty(LATITUDE).updateValue(pathHashMap.get(currentMapPoint).getDoubleLat());
		getObsProperty(LONGITUDE).updateValue(pathHashMap.get(currentMapPoint).getDoubleLng());
		signal(ON_LOCATION_CHANGE, SENSOR_NAME);
	}

	@OPERATION void move(){
		currentMapPoint++;
		if (currentMapPoint<31) {
			ObsProperty lat = getObsProperty(LATITUDE);
			ObsProperty lng = getObsProperty(LONGITUDE);
		    lat.updateValue(pathHashMap.get(currentMapPoint).getDoubleLat());
		    lng.updateValue(pathHashMap.get(currentMapPoint).getDoubleLng());
		    signal(ON_LOCATION_CHANGE, SENSOR_NAME);
		}
	}
	
	@INTERNAL_OPERATION void initPath() {
		// inserisco i punti del percorso Cesena, via Genova -> Lecce, via Trinchese
		/**
		 * PARTENZA
		 */
		// cesena, via genova
		pathHashMap.put(0, new MapPoint("44.151897,12.240154"));
		// cesena, via cavalcavia
		pathHashMap.put(1, new MapPoint("44.151482,12.25276"));
		/**
		 * PICK-UP RIDER1
		 */
		// cesena, via madonna dello schioppo RIDER1 - Marco Rossi
		pathHashMap.put(2, new MapPoint("44.151482,12.25276"));
		// cesena, via madonna dello schioppo RIDER1
		pathHashMap.put(3, new MapPoint("44.151482,12.25276"));
		// cesena, via madonna dello schioppo RIDER1
		pathHashMap.put(4, new MapPoint("44.151482,12.25276"));
		// cesena, via cervese appena inboccata
		pathHashMap.put(5, new MapPoint("44.151982,12.261139"));
		/**
		 * PICK-UP RIDER2
		 */
		// cesena, via cervese RIDER2 - Angelo Verdi
		pathHashMap.put(6, new MapPoint("44.154122,12.2638"));
		// cesena, via cervese RIDER2
		pathHashMap.put(7, new MapPoint("44.154122,12.2638"));
		// cesena, via cervese RIDER2
		pathHashMap.put(8, new MapPoint("44.154122,12.2638"));
		// cesena, casello A14 direzione bari.
		pathHashMap.put(9, new MapPoint("44.168407,12.287092"));
		// A14 - vicino San Mauro Pascoli
		pathHashMap.put(10, new MapPoint("44.113103,12.417812"));
		// A14 - Rimini
		pathHashMap.put(11, new MapPoint("44.037874,12.564754"));
		// A14 - Fano
		pathHashMap.put(12, new MapPoint("43.82818,12.996182"));
		// A14 - Senigallia
		pathHashMap.put(13, new MapPoint("43.704367,13.20857"));
		// A14 - Ancona
		pathHashMap.put(14, new MapPoint("43.536338,13.492584"));
		// A14 - Porto Recanati
		pathHashMap.put(15, new MapPoint("43.442092,13.633904"));
		/**
		 * PICK-UP RIDER3
		 */
		// Civitanova Marche, via D'annunzio - Luca Bianchi
		pathHashMap.put(16, new MapPoint("43.305225,13.721495"));
		// Civitanova Marche, via D'annunzio
		pathHashMap.put(17, new MapPoint("43.305225,13.721495"));
		// Civitanova Marche, via D'annunzio
		pathHashMap.put(18, new MapPoint("43.305225,13.721495"));
		// A14 - Pescara
		pathHashMap.put(19, new MapPoint("42.411314,14.147816"));
		// A14 - Foggia
		pathHashMap.put(20, new MapPoint("41.497585,15.573367"));
		// Brindisi, via Appia
		pathHashMap.put(21, new MapPoint("40.62642,17.919763"));
		/**
		 * ARRIVO
		 */
		// Lecce, via trinchese
		pathHashMap.put(22, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(23, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(24, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(25, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(26, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(27, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(28, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(29, new MapPoint("40.353461,18.174026"));
		// Lecce, via trinchese
		pathHashMap.put(30, new MapPoint("40.353461,18.174026"));		
	}
}
