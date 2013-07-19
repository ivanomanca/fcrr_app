// -------------------------------------------------------
// --------------- JaCa-Web Javacript Layer --------------
// -------------------------------------------------------

window.onload = JaCa_Init;

//Initialization function
function JaCa_Init(){
	JaCa_ShowLoading();
}

//Function to show black div until JaCa-Web framework is completely load and application can start.
function JaCa_ShowLoading(){
	loading_div = document.createElement("div");
	
	if(typeof(loading_div.getAttribute("style")) == 'string')
		loading_div.setAttribute("style","filter:alpha(opacity=90);-moz-opacity:0.9;opacity:0.9;position:absolute;display:block;background-color:black;width:100%;height:100%;top:0px;left:0px;text-align:center;");
	else
		loading_div.style.cssText = "filter:alpha(opacity=90);-moz-opacity:0.9;opacity:0.9;position:absolute;display:block;background-color:black;width:100%;height:100%;top:0px;left:0px;text-align:center;";	
	loading_div.setAttribute("id","jaca-loading");
	loading_msg = document.createElement("div");	
	if(typeof(loading_msg.getAttribute("style")) == 'string')
		loading_msg.setAttribute("style","position:absolute;color:white;display:block;top:0px;width:100%;height:100%;text-align:center;font-size:12px;font-family:Verdana;");
	else
		loading_msg.style.cssText = "position:absolute;color:white;display:block;top:0px;width:100%;height:100%;text-align:center;font-size:12px;font-family:Verdana;";
	loading_msg.setAttribute("id","jaca-loading-msg");
	loading_msg.innerHTML = "<br><br><br><br><img border=0 src=\"http://jaca-web.sourceforge.net/wp-content/images/loading.gif\"/><br/><br/>...please wait while JaCa-Web is loading...<br/><br/><br/><a alt=\"JaCa-Web official page\"target=\"_blank\" href=\"http://jaca-web.sourceforge.net\"><img border=0 src=\"http://jaca-web.sourceforge.net/wp-content/images/jaca-web_nosub.png\"/></a><br/>";
	document.body.appendChild(loading_div);
	document.body.appendChild(loading_msg);		
}

//Function to hide loading div
function JaCa_HideLoading(){
	loading_div = document.getElementById("jaca-loading");
	loading_div.style.display = "none";
	loading_msg = document.getElementById("jaca-loading-msg");
	loading_msg.style.display = "none";
}

//Function to hide loading div with fade effect
function JaCa_HideLoadingWithFade(){
	JaCa_FadeOut("jaca-loading","jaca-loading-msg",0.9,800, 20);	
}


//Function called by JaCa-Web to add an event listener to a specific DOM element
function MyAddEventListener(elementId,type){
	element = document.getElementById(elementId);
	if(element!=null){
		if(element.addEventListener){
			element.addEventListener(type,MyListener,true);
		}
		//IE compatibility
		else if(element.attachEvent){
			element.attachEvent("on"+type,MyListener);
		}
	}
	else
		alert("Wrong event selection, please control elementId.");
}

//Function called by JaCa-Web to remove an event listener to a specific DOM element
function MyRemoveEventListener(elementId,type){
	element = document.getElementById(elementId);
	if(element!=null){
		if(element.addEventListener){
			element.removeEventListener(type,MyListener,true);
		}
		//IE compatibility
		else if(element.attachEvent){
			element.detachEvent("on"+type,MyListener);
		}
	}
	else
		alert("Wrong event selection, please control elementId.");
}

//The listener for all events, it propagates events to the Applet using LiveConnect
function MyListener(e){
	
	var targ;
	
	//IE compatibility
	if (!e) var e = window.event;

	//IE compatibility
	if (e.target) targ = e.target;
	else if (e.srcElement) targ = e.srcElement;

	if (targ.nodeType == 3) // defeat Safari bug
		targ = targ.parentNode;		
	
	//Using LiveConnect for event propagation to applet	
	document.getElementById("jacaweb").notifyEvent([targ.getAttribute("id"),e.type]);
}

//Function for hiding two divs with fade effect
function JaCa_FadeOut(loading_div_id, loading_msg_id, startOp, time, step)
{
	var loading_div = document.getElementById(loading_div_id);
	var loading_msg = document.getElementById(loading_msg_id);
	if((loading_div == null) || (loading_msg == null))
		return;
	loading_div.startOp = startOp;
    loading_div.fadeTime = time;	
	loading_div.firstTick = new Date().getTime();
   	JaCa_AnimateFade(loading_div_id, loading_msg_id, step);
}

//Function for support hiding divs with fade effect
function  JaCa_AnimateFade(loading_div_id, loading_msg_id, step)
{  
	var loading_div = document.getElementById(loading_div_id);
	var loading_msg = document.getElementById(loading_msg_id);

	var curTick = new Date().getTime();
	var elapsedTicks = curTick - loading_div.firstTick;
 
	if(loading_div.fadeTime <= elapsedTicks)
	{
		loading_div.style.opacity = '0';
		loading_div.style.filter = 'alpha(opacity = 0)';
		
		loading_msg.style.opacity = '0';
		loading_msg.style.filter = 'alpha(opacity = 0)';		
		
		loading_div.style.display = "none";
		loading_msg.style.display = "none";
		
		return;
	}
 
	var newOpVal = loading_div.startOp-(loading_div.startOp*(elapsedTicks/loading_div.fadeTime));
	
	loading_div.style.opacity = newOpVal;
	loading_div.style.filter = 'alpha(opacity = ' + (newOpVal*100) + ')';
	
	loading_msg.style.opacity = newOpVal;
	loading_msg.style.filter = 'alpha(opacity = ' + (newOpVal*100) + ')';
 
	setTimeout("JaCa_AnimateFade('" + loading_div_id + "','" + loading_msg_id + "')", step);
}