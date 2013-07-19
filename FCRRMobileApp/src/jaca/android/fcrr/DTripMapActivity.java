package jaca.android.fcrr;

import jaca.android.fcrr.util.HelloItemizedOverlay;
import jaca.android.fcrr.util.JsonParam;
import jaca.android.fcrr.JaCaMapActivity;
import jaca.android.fcrr.util.MapPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class DTripMapActivity extends JaCaMapActivity{

	List<Overlay> mapOverlays;
	private HelloItemizedOverlay carItemizedOverlay;
	private MapView mapView;
	private Handler mHandler;
	private int currentItemNumber;
	private Drawable[] customDrawable;
	private Context ctx;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.hellomap);
		currentItemNumber = 0;
		ctx = this;
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mHandler = new Handler();
		mapOverlays = mapView.getOverlays();

		// recupero i marker predefiniti
		customDrawable = new Drawable[] {
				this.getResources().getDrawable(R.drawable.car),
				this.getResources().getDrawable(R.drawable.blu),
				this.getResources().getDrawable(R.drawable.orange),
				this.getResources().getDrawable(R.drawable.green),
				this.getResources().getDrawable(R.drawable.fuchsia)};

		createArtifact("dtripmapgui", DTripMapGUIArtifact.class.getCanonicalName());
	}


	public void updateMap(	ArrayList<HashMap<String,String>> carpoolerInfo, 
			ArrayList<HashMap<String,String>> ridersInfoList) {
		// creo l'array di overlay
		final OverlayItem[] overlayList = createOverlayList(carpoolerInfo, ridersInfoList);

		// se il numero di item è != rispetto
		// all'ultimo aggiornamento, allora rigenero.
		// altrimenti aggiorno (soltanto il driver)
		if (currentItemNumber == overlayList.length) {
			// -- ripulisco la mappa - non lo uso!
			//mapView.getOverlays().clear();
			//aggiornamento della mappa (driver)
			mHandler.post(new Runnable() {
				public void run() {
					// rimuovo l'elemento
					carItemizedOverlay = (HelloItemizedOverlay)mapOverlays.remove(0);
					carItemizedOverlay.removeOverlay(0);
					carItemizedOverlay.forcePopulate();

					// aggiungo la nuova posizione
					carItemizedOverlay.addOverlay(overlayList[0]);
					carItemizedOverlay.forcePopulate();
					mapOverlays.add(carItemizedOverlay);

					// ridisegna la mappa
					mapView.invalidate();
				}
			});
		}else{
			// 1. aggiorno il numero corrente di item in base
			// alla dimensione dell'array e rigenero
			currentItemNumber = overlayList.length;
			generateOverlays(overlayList);
		}
	}

	/**
	 * Restituisce un overlayArray in cui il primo elemento è il driver
	 * ed i successivi elementi sono i riders
	 * @param carpoolerInfo
	 * @param ridersInfoList
	 * @return
	 */
	public OverlayItem[] createOverlayList(	ArrayList<HashMap<String,String>> carpoolerInfo, 
											ArrayList<HashMap<String,String>> ridersInfoList){

		// hashmap del driver
		HashMap<String, String> hmDriver = carpoolerInfo.get(0);
		OverlayItem oiDriver = new OverlayItem(
				new MapPoint(hmDriver.get(JsonParam.Carpooler.geopoint)).getGeoPoint(),
				"DriverNomeCognome",
				"Commento...");
		// creo una overlaylist dimensionata driver + numero riders
		OverlayItem[] overlayArray = new OverlayItem[ridersInfoList.size()+1];
		// inserisco il driver
		overlayArray[0] = oiDriver;

		// inserisco i riders
		Iterator<HashMap<String, String>> iterator = ridersInfoList.iterator();
		int i=1;
		while (iterator.hasNext()) {
			HashMap<String,String> hmRider = iterator.next();
			overlayArray[i] = new OverlayItem(
					new MapPoint(hmRider.get(JsonParam.Reservation.pick_up_geopoint)).getGeoPoint(),
					"RiderNomeCognome",
					"Commento...");
			i++;
		}
		// restituisco l'array di overlay
		return overlayArray;
	}

	/**
	 * In base la numero di Geopoint in ingresso, genera la lista di overlay
	 * custom considerando il primo elemento di input come Driver
	 * @param overlayList
	 */
	public void generateOverlays(final OverlayItem[] overlayList) {
		mHandler.post(new Runnable() {
			public void run() {
				// per ogni carpooler creo l'itemized con il marker adeguato
				for (int i = 0; i < overlayList.length; i++) {
					//OverlayItem overlayItem = overlayList[i];
					HelloItemizedOverlay hio = new HelloItemizedOverlay(customDrawable[i], ctx);
					hio.addOverlay(overlayList[i]);
					mapOverlays.add(hio);
				}
			}
		});
	}
}
