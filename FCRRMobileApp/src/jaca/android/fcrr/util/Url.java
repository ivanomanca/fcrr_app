package jaca.android.fcrr.util;

public final class Url {

	/**
	 * Server
	 */
	// true = host locale, false = host remoto
	public static final boolean localServer = true;
	
	public static final String localIp = "http://192.168.10.5";
	public static final String remoteIp = "http://ubuivo.altervista.org";
	public static final String serverPort = ":80/";
	public static final String localServerRootUrl = "FCRRServer/";
	public static final String remoteServerRootUrl = "";
	public static final String indexUrl = "index.php";
	
	/**
	 * Carpooler
	 */
	// dato l'id del carpooler, ne aggiorna la posizione gps
	public static final String updateCarpoolerGPS = "updateCarpoolerGPS.php";
	// dato l'id del trip, ricava tutte le info del driver
	public static final String getTripDriverInfo = "getTripDriverInfo.php";
	// dato l'id del trip, ricava tutte le informazioni dei Rider (dalle reservation)
	public static final String getTripRiderList = "getTripRiderList.php";
	// dato l'id del driver, ricava tutte le informazioni dei trip creati dal driver
	public static final String getDriverTripList = "getDriverTripList.php";
	// dato l'id del trip, ricava tutte le informazioni dei rider in base alle reservation
	public static final String getTripInfo = "getTripInfo.php";
	// dato l'id del trip, ricava tutte le informazioni dei rider in base alle reservation
	public static final String getCarpoolerInfo = "getCarpoolerInfo.php";
	
		
	
}
