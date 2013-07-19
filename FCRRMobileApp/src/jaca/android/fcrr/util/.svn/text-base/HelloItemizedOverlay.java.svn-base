package jaca.android.fcrr.util;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends ItemizedOverlay  {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	/*public HelloItemizedOverlay(Drawable defaultMarker) {
		  super(boundCenterBottom(defaultMarker));
	}*/
	
	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		//super(defaultMarker);
		super(boundCenterBottom(defaultMarker));  
		mContext = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    // rilegge tutti gli overlay e li prepara per disegnarli
	    populate();
	}
	
	public void removeOverlay(int index){
	    mOverlays.remove(index);
	    populate();
	}
	
	public void removeOverlay(OverlayItem overlay){
	    mOverlays.remove(overlay);
	    populate();
	}
	
	public void forcePopulate(){
	    populate();
	}
	
	public void addOverlayList(List<OverlayItem> overlayitems) {
	    Object temp[] = overlayitems.toArray();
	    for(int i = 0;i<temp.length;i++){
	    	mOverlays.add((OverlayItem)temp[i]);
	    }
	    populate();	    
	}
	
	public void removeOverlayList(List<OverlayItem> overlayitems) {
	    Object temp[] = overlayitems.toArray();
        for(int i = 0;i<temp.length;i++){
            mOverlays.remove((OverlayItem)temp[i]);
        }
	    populate();
	}

	
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}
	
	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
	
}
