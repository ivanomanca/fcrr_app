package jaca.android.fcrr.util;

import com.google.android.maps.GeoPoint;

public class MapPoint {

	String coordString;
	double doubleLat;
	double doubleLng;
	int microLat;
	int microLng;
	GeoPoint geoPoint;
	
	/**
	 * @param coordString
	 */
	public MapPoint(String coordString) {
		super();
		this.coordString = coordString;
		String[] coordArray = coordString.split(",");
		String lngString = coordArray[0];
		String latString = coordArray[1];
		//lat = null; lng = null;
		try {
	         doubleLng = Double.valueOf(lngString.trim()).doubleValue();
	         doubleLat = Double.valueOf(latString.trim()).doubleValue();
	         //System.out.println("doubleLat = "+doubleLat);
	         //System.out.println("doubleLat = "+doubleLng);
	      } catch (NumberFormatException nfe) {
	         System.out.println("NumberFormatException: " + nfe.getMessage());
	      }
		microLng = (int)(doubleLng * 1E6);
		microLat = (int)(doubleLat * 1E6);
		geoPoint = new GeoPoint(microLng, microLat);
		
	}
	
	/**
	 * @param coordString
	 */
	public MapPoint(Double doubleLat, Double doubleLng) {
		super();
		coordString = doubleLng+","+doubleLat;
		microLng = (int)(doubleLng * 1E6);
		microLat = (int)(doubleLat * 1E6);
		geoPoint = new GeoPoint(microLng, microLat);
		
	}

	/**
	 * @return the coordString
	 */
	public String getCoordString() {
		return coordString;
	}

	/**
	 * @param coordString the coordString to set
	 */
	public void setCoordString(String coordString) {
		this.coordString = coordString;
	}

	/**
	 * @return the doubleLat
	 */
	public double getDoubleLat() {
		return doubleLat;
	}

	/**
	 * @param doubleLat the doubleLat to set
	 */
	public void setDoubleLat(double doubleLat) {
		this.doubleLat = doubleLat;
	}

	/**
	 * @return the doubleLng
	 */
	public double getDoubleLng() {
		return doubleLng;
	}

	/**
	 * @param doubleLng the doubleLng to set
	 */
	public void setDoubleLng(double doubleLng) {
		this.doubleLng = doubleLng;
	}

	/**
	 * @return the microLat
	 */
	public int getMicroLat() {
		return microLat;
	}

	/**
	 * @param microLat the microLat to set
	 */
	public void setMicroLat(int microLat) {
		this.microLat = microLat;
	}

	/**
	 * @return the microLng
	 */
	public int getMicroLng() {
		return microLng;
	}

	/**
	 * @param microLng the microLng to set
	 */
	public void setMicroLng(int microLng) {
		this.microLng = microLng;
	}

	/**
	 * @return the geoPoint
	 */
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	/**
	 * @param geoPoint the geoPoint to set
	 */
	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}	
	
}
