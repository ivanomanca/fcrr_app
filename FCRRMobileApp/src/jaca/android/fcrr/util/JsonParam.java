package jaca.android.fcrr.util;

public final class JsonParam {

	public static final String inputParams = "inputParams";
	public static final String selectParams = "selectParams";
	public static final String updateParams = "updateParams";
	public static final String id = "_id";
	
	
	/**
	 * Carpooler
	 */
	public final class Carpooler {
		//id identificativo
		public static final String id = "_id";
		//name nome del carpooler
		public static final String name = "name";
		//surname cognome del carpooler
		public static final String surname = "surname";
		//genre sesso del carpooler //(0) uomo //(1) donna
		public final class Genre {
			public static final String genre = "genre";
			public static final int MALE = 0;
			public static final int FEMALE = 1;
		}
		//geopoint coordinate posizione geografica default
		public static final String geopoint = "geopoint";
		//cc ammontare di CC in suo possesso
		public static final String cc = "cc";
		//smoke abitudine al fumo //(0) non fumatore //(1) fumatore //(2) indifferente
		public final class Smoke {
			public static final String smoke = "smoke";
			public static final int NON_SMOKER = 0;
			public static final int SMOKER = 1;
			public static final int NEUTRAL = 2;
		}
		//phone numero di cellulare
		public static final String phone = "phone";
		//e-mail indirizzo e-mail
		public static final String email = "email";
		//pw password di accesso
		public static final String pw = "pw";
		//fb-account url account facebook
		public static final String fb_account = "fb_account";
		//feedback valore di feedback (scala 1 : 10 in 5 stelle)
		public static final String feedback = "feedback";
		//status stato dell’account //(0) attivo //(1) sospeso (dal sistema) //(-1) cancellato (dall’utente)
		public final class Status {
			public static final String status = "status";
			public static final int DELETED = -1;
			public static final int ACTIVE = 0;
			public static final int SUSPENDED = 1;
		}
	}
	
	/**
	 * Trip
	 */
	public final class Trip {
		//id identificativo
		public static final String id = "_id";
		//id-driver identificativo del driver
		public static final String id_driver = "id_driver";
		//from coordinate del luogo di partenza
		public static final String from = "from";
		//to coordinate del luogo di arrivo
		public static final String to = "to";
		//vehicle denominazione del mezzo
		public static final String vehicle = "vehicle";
		//total-seats posti totali messi a disposizione dal driver
		public static final String total_seats = "total_seats";
		//taken-seats posti prenotati
		public static final String taken_seats = "taken_seats";
		//date-dep data ed ora della partenza
		public static final String date_dep = "date_dep";
		//date-arr data ed ora di arrivo stimate
		public static final String date_arr = "date_arr";
		//status stato del viaggio //(-1) cancellato (dal driver) //(0) programmato //(1) in corso //(2) terminato
		public final class Status {
			public static final String status = "status";
			public static final int DELETED = -1;
			public static final int PLANNED = 0;
			public static final int ONGOING = 1;
			public static final int COMPLETED = 2;
		}
	}
	
	/**
	 * Reservation
	 */
	public final class Reservation {
		//id identificativo
		public static final String id = "_id";
		//id-trip identificativo del viaggio
		public static final String id_trip = "id_trip";
		//id-rider identificativo del rider
		public static final String id_rider = "id_rider";
		//rider-status stato del rider //(0) check-in non effettuato //(1) checked-in (dopo il pick-up) //(2) checked-out (a fine viaggio)
		public final class Rider_status {
			public static final String rider_status = "rider_status";
			public static final int NOT_CHECKED_IN = 0;
			public static final int CHECKED_IN = 1;
			public static final int CHECKED_OUT = 2;
		}
		//check-in-code codice di check-in del rider
		public static final String check_in_code = "check_in_code";
		//pick-up-geopoint coordinate punto di pick-up
		public static final String pick_up_geopoint = "pick_up_geopoint";
		//km-covered km totali stimati dal sistema
		public static final String km_covered = "km_covered";
		//cc-reserved n CC a garanzia di pagamento
		public static final String cc_reserved = "cc_reserved";
		//luggage-size dimensione del bagaglio del rider
		public static final String luggage_size = "luggage_size"; //(0) non presente //(1) piccolo //(2) medio //(3) grande
		//status stato della prenotazione //(-1) cancellata dal rider //(0) in attesa di risposta //(1) accettata //(2) rifiutata per motivi non specificati //(3) rifiutata per lontananza del punto di pick-up //(4) rimborsata al driver del 30%
		public final class Status {
			public static final String status = "status";
			public static final int DELETED = -1;
			public static final int WAITING_FOR_RESPONSE = 0;
			public static final int ACCEPTED = 1;
			public static final int REFUSED = 2;
			public static final int PICKUP_POINT_REFUSED = 3;
			public static final int AMENDS_MADE = 4;
		}
	}
	
	/**
	 * Notification
	 */
	public final class Notification {
		//id identificativo
		public static final String id = "_id";
		//id-res identificativo della reservation
		public static final String id_res = "id_res";
		//msg messaggio testuale di notifica
		public final class Msg {
			public static final String msg = "msg";
			//(0) “hai una nuova richiesta di prenotazione per il
			//viaggio <from> <to> <date-dep> <nomedriver>”
			public static final int NEW_RESERVATION_REQUEST = 0;
			//(1) “la richiesta di prenotazione di <nome-rider>
			//per il viaggio <from> <to> <date-dep> è
			//stata cancellata”
			public static final int RESERVATION_DELETED = 1;
			//(2) “la tua richiesta di prenotazione per il viaggio
			//<from> <to> <date-dep> <nome-driver> è
			//stata accettata”
			public static final int RESERVATION_ACCEPTED = 2;
			//(3) “la tua richiesta di prenotazione per il viaggio
			//<from> <to> <date-dep> <nome-driver>
			//è stata rifiutata dal driver per l’eccessiva
			//distanza del punto di pick-up dal percorso”
			public static final int RESERVATION_REQUEST_REFUSED_PICKUP_POINT = 3;
			//(4) “la tua richiesta di prenotazione per il viaggio
			//<from> <to> <date-dep> <nome-driver>
			//è stata rifiutata dal driver per motivi non
			//specificati”
			public static final int RESERVATION_REQUEST_REFUSED = 4;
		}
		//date data e ora di invio della notifica
		public static final String date = "date";
		//status stato della notifica //(-1) cancellata //(0) non letta //(1) letta
		public final class Status {
			public static final String status = "status";
			public static final int DELETED = -1;
			public static final int UNREAD = 0;
			public static final int READ = 1;
		}
	}
}
