// Application Specific Functions of Google Map Example for JaCa-Web
var map;
var markers;
var bounds;

function init(){
	
	bounds = new google.maps.LatLngBounds ()
	markers = new Array()
	
	var myOptions = {
		zoom:2,            
		mapTypeId: google.maps.MapTypeId.HYBRID
	};
	map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
	
	var initialLocation = new google.maps.LatLng(0,0);	    
	map.setCenter(initialLocation);  						
	
	google.maps.event.addListener(map, 'zoom_changed',function() {
		if (map.getZoom() > 17) {
			map.setZoom(17);
		}
	});			
}

function GetRemoteIp(){
	return "137.204.73.31";
}

function AddMarker(title, lat, lang, state, partecipants, content){
	
	var myLatlng = new google.maps.LatLng(lat,lang);			
	var infowindow = new google.maps.InfoWindow({
		content: content
	});	
	
	var marker = new google.maps.Marker({
	    position: myLatlng,
	    map: map,
	    icon: getIconPath(state,partecipants),
	    title: title,
	    infowindow: infowindow,
	    partecipants: partecipants,
	    state: state
	});
	
	google.maps.event.addListener(marker, 'click', function() {
  		infowindow.open(map,marker);
	});
	
	markers.push(marker);
	bounds.extend(marker.position);
	map.fitBounds(bounds);	
}

function AddPartecipant(title){
	for(i=0;i<markers.length;i++){
		if(markers[i].title==title){
			markers[i].partecipants++;
			markers[i].setIcon(getIconPath(markers[i].state,markers[i].partecipants));
		}
	}
	map.fitBounds(bounds);
}

function RemovePartecipant(title){
	for(i=0;i<markers.length;i++){
		if(markers[i].title == title){
			markers[i].partecipants--;
			markers[i].setIcon(getIconPath(markers[i].state,markers[i].partecipants));
		}
	}
	map.fitBounds(bounds);
}

function ChangeCurrentPartecipants(title, partecipants){		
	for(i=0;i<markers.length;i++){
		if(markers[i].title == title){
			markers[i].partecipants = partecipants;			
			markers[i].setIcon(getIconPath(markers[i].state,markers[i].partecipants));
		}
	}
	map.fitBounds(bounds);
}

function ChangeMarkerState(title, state){
	for(i=0;i<markers.length;i++){
		if(markers[i].title == title){
			markers[i].state = state;		
			markers[i].setIcon(getIconPath(markers[i].state,markers[i].partecipants));
		}
	}
	map.fitBounds(bounds);
}

function ChangeMarkerContent(title, new_content){
	for(i=0;i<markers.length;i++){
		if(markers[i].title == title)
			markers[i].infowindow.setContent(new_content);
	}
	map.fitBounds(bounds);
}

function ChangeStatusMessage(message){
	status_message_div = document.getElementById("status_message");
	status_message_div.innerHTML = message;
}

function getIconPath(state, partecipants){	
	var icon = state;	
	if(state == "status_waiting"){
		if(partecipants < 0)
			icon = icon + 0;
		else if(partecipants > 30)
			icon = icon + "+";
		else						
			icon = icon + partecipants;			
	}
	icon = "markers/" + icon + ".png";		
	return icon;	
}
