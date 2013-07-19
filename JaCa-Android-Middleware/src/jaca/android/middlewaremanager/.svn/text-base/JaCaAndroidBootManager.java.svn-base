package jaca.android.middlewaremanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class JaCaAndroidBootManager extends BroadcastReceiver{

	public void onReceive(Context context, Intent intent) {
		
		boolean isAutoStart = MiddlewarePreferenceManager.getAutoStart(context);

		if(isAutoStart){Intent service = new Intent(context, JaCaMiddlewareService.class);
			service.setAction(JaCaMiddlewareService.SERVICE_START_ACTION);
			service.putExtra(JaCaMiddlewareService.MAS2J, R.string.middleware_manager_mas2j);
			context.startService(service);
			Log.v("JaCaAndroidBootManager", "Starting JaCaMiddlewareService in auto-start mode");
		}
		else Log.v("JaCaAndroidBootManager", "Auto-start preference not setted for the middleware");
	}
}