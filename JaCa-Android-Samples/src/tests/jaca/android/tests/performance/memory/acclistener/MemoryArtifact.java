package jaca.android.tests.performance.memory.acclistener;

import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import jaca.android.samples.main.R;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;


public class MemoryArtifact extends GUIArtifact {
	
	private MemoryActivity activity;
	private View btnCalcAccListener;
	private View btnCalcOtherApps;
	
	@OPERATION public void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		this.activity = (MemoryActivity) activity;
		btnCalcAccListener = this.activity.findViewById(R.id.btnCalcMemAccListener);
		btnCalcOtherApps = this.activity.findViewById(R.id.btnCalcMemOtherApps);
		linkOnClickEventToOp(btnCalcAccListener, "onClick");
		linkOnClickEventToOp(btnCalcOtherApps, "onClick");
		
	}
	
	@INTERNAL_OPERATION public void onClick(View view) {
		
		if(view == btnCalcAccListener){
			double currMem =0;
			double totMem =0;
			double privMem =0;
			Debug.MemoryInfo mi;
			int park = 0;
			
			for(int i=0; i<20; i++){
				mi = new Debug.MemoryInfo(); 
				Debug.getMemoryInfo(mi);

				currMem = mi.dalvikPss + mi.dalvikPrivateDirty + mi.dalvikSharedDirty 
				+ mi.nativePss + mi.nativePrivateDirty + mi.nativeSharedDirty 
				+ mi.otherPss + mi.otherPrivateDirty + mi.otherSharedDirty;

				privMem += mi.dalvikPrivateDirty + mi.nativePrivateDirty + mi.otherPrivateDirty;
			    try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    totMem += currMem;
			}
		    park = (int) ((totMem/(1024*20))*100);
		    totMem = (double)park/100;
		    
		    park = (int) ((privMem/(1024*20))*100);
		    privMem = (double)park/100;
			signal("mo_acclistener", privMem, totMem);
		}
		else if(view == btnCalcOtherApps){
			ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

			List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();

			Map<Integer, String> pidMap = new TreeMap<Integer, String>();
			for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
			{
			    pidMap.put(runningAppProcessInfo.pid, runningAppProcessInfo.processName);
			}

			Collection<Integer> keys = pidMap.keySet();

			for(int key : keys)
			{
			    int pids[] = new int[1];
			    pids[0] = key;
			    android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(pids);
			    for(android.os.Debug.MemoryInfo mi: memoryInfoArray)
			    {
				    double totMem = mi.dalvikPss + mi.dalvikPrivateDirty + mi.dalvikSharedDirty + mi.nativePss + mi.nativePrivateDirty + + mi.nativeSharedDirty + mi.otherPss + mi.otherPrivateDirty + mi.otherSharedDirty;
				    double privMem = mi.dalvikPrivateDirty + mi.nativePrivateDirty + mi.otherPrivateDirty;
				    int park = (int) ((totMem/1024)*100);
				    totMem = (double)park/100;
				    
				    park = (int) ((privMem/1024)*100);
				    privMem = (double)park/100;

				    signal("mo_other_apps", pidMap.get(key), privMem, totMem);
					Log.v("GINO", pidMap.get(key) +" "+ privMem+ " " + totMem);
			    }
			}
		}
	}
}