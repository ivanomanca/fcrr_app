package google_maps.application;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import jacaweb.PageArtifact;

/**
 * Artifact that represents the application specific page. It allows two-way communication with HTML+JavaScript
 * page. It extends PageArtifact that is an abstract artifact.
 * 
 * @author Mattia Minotti
 *
 */
public class MyPageArtifact extends PageArtifact {

	/**
	 * It override the abstract method of PageArtifact. This method is called on artifact creation.
	 */
	@Override
	protected void setup() {						
		defineObsProperty("remoteIp",getRemoteIp());
	}		
	
	/**
	 * 
	 * Add a marker to the Map.
	 * 
	 * @param lat Latitude coordinate 
	 * @param lon Longitude coordinate
	 * @param title Title of the Conference (identifier)
	 * @param state State of the Conference ("wait","ready","run")
	 * @param content Additional content about the Conference
	 */
	@OPERATION void addMarker(String title, double lat, double lon, String state, int partecipants, String content){
		executeJSFunction("AddMarker",title,lat,lon,state,partecipants,content);
	}
	
	/**
	 * 
	 * Add a partecipant for a specified Conference
	 * 
	 * @param title Title of the Conference (identifier)
	 */
	@OPERATION void addPartecipant(String title){
		executeJSFunction("AddPartecipant",title);
	}		
	
	/**
	 * 
	 * Remove a partecipant for a specified Conference
	 * 
	 * @param title Title of the Conference (identifier)
	 */
	@OPERATION void removePartecipant(String title){
		executeJSFunction("RemovePartecipant",title);
	}
	
	/**
	 * 
	 * Change partecipant number for a specified Conference
	 * 
	 * @param title Title of the Conference (identifier)
	 */
	@OPERATION void changeCurrentPartecipants(String title, int partecipants){
		executeJSFunction("ChangeCurrentPartecipants", title, partecipants);
	}
	
	/**
	 * 
	 * Change the state of a marker.
	 * 
	 * @param title Title of the Conference (identifier)
	 * @param new_state New state of the Conference ("wait","ready","run")
	 */
	@OPERATION void changeMarkerState(String title, String new_state){
		executeJSFunction("ChangeMarkerState",title,new_state);
	}
	
	/**
	 * 
	 * Change the additional content about a conference linked to a marker.
	 * 
	 * @param title Title of the Conference (identifier)
	 * @param new_content New content about the Conference
	 */
	@OPERATION void changeMarkerContent(String title, String new_content){
		executeJSFunction("ChangeMarkerContent",title,new_content);
	}
	
	/**
	 * 
	 * Change the content of a status message in the bottom of the page.
	 * 
	 * @param message The message to show, in HTML format
	 */
	@OPERATION void changeStatusMessage(String message){
		executeJSFunction("ChangeStatusMessage",message);
	}
	
	@INTERNAL_OPERATION String getRemoteIp(){
		return (String)executeJSFunction("GetRemoteIp");
	}
	
}
