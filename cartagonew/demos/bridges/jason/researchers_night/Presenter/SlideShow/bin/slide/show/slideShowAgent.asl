!init.

+!init
	<- 	?current_wsp(LocalWsp,_,_); +local_wsp(LocalWsp);
		lookupArtifact("workspace", Wsp);
		focus(Wsp).
		
/************************************* observable property plans **************************************/
+artifact("SlideShowActivity", _, SlideShowActivity)
	: true
	<-	focus(SlideShowActivity).
	
-artifact("SlideShowActivity", _, SlideShowActivity)
	: device_wsp(DeviceWsp)
	<- 	-device_wsp(DeviceWsp);
		unlock;
		quitWorkspace.

	
/************************************* events plans ****************************************************/
+search_device[artifact_id(SlideShowActivity)]
	: not device_wsp(DeviceWsp)
	<- 	cartago.new_obj("android.content.Intent",["com.google.zxing.client.android.SCAN"],Intent);
     	cartago.invoke_obj(Intent,putExtra("SCAN_MODE", "QR_CODE_MODE"));
     	startActivityForResult(Intent, 0).

	
+device_fouded(Address, Protocol)
	:true
	<- !joinDeviceWsp(Address, Protocol).
	
+next_slide(URL)
	: device_wsp(DeviceWsp)
	<- drawImageUrl(URL)[wsp_id(DeviceWsp)].


/************************************* goals plans ****************************************************/

+!joinDeviceWsp(Address, Protocol)
	: true
	<- 	joinRemoteWorkspace("default", Address, Protocol, EventsWsp); +device_wsp(DeviceWsp);
		lookupArtifact("Device", Device);
		lock;
		?local_wsp(LocalWsp);
		showMessage("Device connected")[wsp_id(LocalWsp)];
		getCurrentSlideUrl(URL)[wsp_id(LocalWsp)];
		drawImageUrl(URL)[wsp_id(DeviceWsp)].


-!joinDeviceWsp(Address, Protocol)
	<- 	?local_wsp(LocalWsp);
		showMessage("Device connect failed")[wsp_id(LocalWsp)].
		