package jaca.android.dev;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;

/**
 * <p>
 * Developers must extend this class to define an artifact usable
 * from agents for observe and control Android view interface.
 * The events related to the wrapped Activity are fetched automatically 
 * fetched by the GUIArtifact using the {@link GUIArtifact#fetchGUIEvents()} 
 * internal operation through a {@link ActivityEventsFetcher}.
 * </p>
 * 
 * <p> #### Observable Properties #####
 *  <ul>
 *  	<li> {@code state(State). State = {"running","paused","stopped"}}</li>
 *  </ul>
 * </p>
 * 
 * @author mguidi
 *
 */
public abstract class GUIArtifact extends JaCaArtifact{
	
	/**
	 * Name of the observable property indicating the current state of the
	 * {@link IJaCaActivity} wrapped by the GUIArtifact
	 */
	public static final String STATE = "state";
	
	/**
	 * Value of the {@link GUIArtifact#STATE} observable property indicating that the activity is running
	 */
	public static final String RUNNING = "running";

	/**
	 * Value of the {@link GUIArtifact#STATE} observable property indicating that the activity is stopped
	 */
	public static final String STOPPED = "stopped";

	/**
	 * Value of the {@link GUIArtifact#STATE} observable property indicating that the activity is paused
	 */
	public static final String PAUSED = "paused";

	/*
	 * Key: The object generating the event (could be a view, the activity linked to the GUIArtifact)
	 * Value: An HashMap<String,String> mapping for each kind of event
	 * (each object can have different kind of event onClikc, etc.) to the appropriate listener. 
	 */
	protected HashMap<Object, HashMap<String,String>> mEvToOpLinks;
	protected boolean mStopped;
	protected IJaCaActivity mActivity;

	
	/* Op for Activity events */
	protected static final String OP_ON_CREATE = "onCreate";
	protected static final String OP_ON_RESTART = "onReStart";
	protected static final String OP_ON_START = "onStart";
	protected static final String OP_ON_RESUME = "onResume";
	protected static final String OP_ON_PAUSE = "onPause";
	protected static final String OP_ON_STOP = "onStop";
	protected static final String OP_ON_DESTROY = "onDestroy";
	protected static final String OP_ON_ACTIVITY_RESULT = "onActivityResult";
	protected static final String OP_ON_LIST_ITEM_CLICK = "onListItemClick";
	protected static final String OP_ON_OPTIONS_ITEMS_SELECTED = "onOptionsItemSelected";
	protected static final String OP_ON_TOUCH_EVENT = "onTouchEvent";

	/* Op for View events */
	protected static final String OP_ON_CLICK = "onClick";
	protected static final String OP_ON_LONG_CLICK = "onLongClick";
	protected static final String OP_ON_KEY = "onKey";
	protected static final String OP_ON_TOUCH = "onTouch";
	protected static final String OP_ON_FOCUS_CHANGE = "onFocusChange";
	protected static final String OP_ON_EDITOR_ACTION = "onEditorAction";
	protected static final String OP_ON_DOUBLE_TAP = "onDoubleTap";
	protected static final String OP_ON_DOUBLE_TAP_EVENT = "onDoubleTapEvent";
	protected static final String OP_ON_DOWN = "onDown";
	protected static final String OP_ON_FLING = "onFling";
	protected static final String OP_ON_LONG_PRESS = "onLongPress";
	protected static final String OP_ON_SCROLL = "onScroll";
	protected static final String OP_ON_SHOW_PRESS = "onShowPress";
	protected static final String OP_ON_SINGLE_TAP_CONFIRMED = "onSingleTapConfirmed";
	protected static final String OP_ON_SINGLE_TAP_UP = "onSingleTapUp";
	
	/**
	 * This method must be override to initialize the GUIArtifact. 
     * First instruction of the override method must be {@code super.init()}.
     *
	 * @param activity The class IJaCaActivity wrapped by the Artifact
	 * @param savedInstanceState
	 */
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		mEvToOpLinks = new HashMap<Object, HashMap<String,String>>();
    	mActivity = activity;
		mStopped = false;
    	defineObsProperty(STATE, (Object) null);
    	execInternalOp("fetchGUIEvents");
    }

	
	/**
	 * Internal operation cyclically in execution inside the GUIArtifact that process
	 * incoming events
	 */
	@INTERNAL_OPERATION protected void fetchGUIEvents() {
		
		ActivityEventsFetcher eventFetcher = mActivity.getActivityEventsFetcher();
    	
		while (!mStopped) {
    	
			await(eventFetcher);
			EventOpInfo eventOp = eventFetcher.getCurrentEventFetched();

			if (eventOp!=null) {
    		
				String listener = eventOp.getMethodName();
    			Object source= eventOp.getSource();
	    		HashMap<String,String> map = mEvToOpLinks.get(eventOp.getSource());
	    		
	    		/*
	    		 * Update of the state observable properties, if its the case 
	    		 */
    			if(source.equals(mActivity)){
    				ObsProperty prop = getObsProperty(STATE);
    				if (listener.equals(ActivityEventsFetcher.ON_RESUME)) {
    	    			prop.updateValue(RUNNING);
    	    		} else if (listener.equals(ActivityEventsFetcher.ON_PAUSE)) {
    	    			prop.updateValue(PAUSED);
    	    		} else if (listener.equals(ActivityEventsFetcher.ON_STOP)) {
    	    			prop.updateValue(STOPPED);
    	    		}
    			}

    			//Invocation of the operation linked to the event
    			if (map!=null) {
	    			String opName = map.get(listener);
	    			if (opName!=null) execInternalOp(opName, eventOp.getParam());
	    		}
    		} else {
    			mStopped = true;
    		}
    	}
    }
	
    /**
     * Link {@code onReStart} event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onRestart()
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnReStartEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_RESTART, opName);
    }
    
    /**
     * Link {@code onStart} event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onStart()}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnStartEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_START, opName);
    }
    
    /**
     * Link onResume event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onResume()}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnResumeEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_RESUME, opName);
    }
    
    /**
     * Link onPause event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onPause()}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnPauseEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_PAUSE, opName);
    }
    
    /**
     * Link onStop event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onStop()}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnStopEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_STOP, opName);
    }
    
    /**
     * Link onDestroy event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onDestroy()}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnDestroyEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_DESTROY, opName);
    }
    
    /**
     * Link onActivityResult event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onActivityResult(int requestCode, int resultCode, Intent data)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnActivityResultEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_ACTIVITY_RESULT, opName);
    }
    
    
    /**
     * Link onOptionsItemSelected event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onOptionsItemSelected(MenuItem item)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnOptionsItemSelectedToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_OPTIONS_ITEMS_SELECTED, opName);
    }
    
    /**
     * Link onTouchEvent event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onTouchEvent(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnTouchEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_TOUCH_EVENT, opName);
    }
    
    /**
     * Link onDoubleTap event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onDoubleTap(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnDoubleTapToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_DOUBLE_TAP, opName);
    }
    
    /**
     * Link onDoubleTapEvent event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onDoubleTapEvent(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnDoubleTapEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_DOUBLE_TAP_EVENT, opName);
    }
    
    /**
     * Link onDown event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onDown(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnDownToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_DOWN, opName);
    }
    
    /**
     * Link onFling event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnFlingToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_FLING, opName);
    }
    
    /**
     * Link onLongPress event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onLongPress(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnLongPressToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_LONG_PRESS, opName);
    }
    
    /**
     * Link onScroll event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnScrollToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_SCROLL, opName);
    }
    
    /**
     * Link onShowPress event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onShowPress(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnShowPressToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_SHOW_PRESS, opName);
    }
    
    /**
     * Link onSingleTapConfirmed event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onSingleTapConfirmed(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnSingleTapConfirmedToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_SINGLE_TAP_CONFIRMED, opName);
    }
    
    /**
     * Link onSingleTapUp event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onSingleTapUp(MotionEvent event)}
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnSingleTapUpToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_SINGLE_TAP_UP, opName);
    }
    
    /**
     * Link a click event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onClick(View view)}
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkOnClickEventToOp(View source, String opName) {
		linkEventToOp(source, ActivityEventsFetcher.ON_CLICK, opName);
		source.setOnClickListener(mActivity.getActivityEventsFetcher());
	}
	
	/**
     * Link a long click event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onLongClick(View view)}
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkOnLongClickEventToOp(View source, String opName) {
		linkEventToOp(source, ActivityEventsFetcher.ON_LONG_CLICK, opName);
		source.setOnClickListener(mActivity.getActivityEventsFetcher());
	}
	
	/**
     * Link a key event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onKey(View view, int keyCode, KeyEvent event)}
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkOnKeyEventToOp(View source, String opName) {
		linkEventToOp(source, ActivityEventsFetcher.ON_KEY, opName);
		source.setOnKeyListener(mActivity.getActivityEventsFetcher());
	}
	
	/**
     * Link a touch event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onTouch(View view, MotionEvent event)}
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkOnTouchEventToOp(View source, String opName) {
		linkEventToOp(source, ActivityEventsFetcher.ON_TOUCH, opName);
		source.setOnTouchListener(mActivity.getActivityEventsFetcher());
	}
	
	/**
     * Link a focus change event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION void onFocusChange(View view, boolean arg1)}
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkOnFocusChangeEventToOp(View source, String opName) {
		linkEventToOp(source, ActivityEventsFetcher.ON_FOCUS_CHANGE, opName);
		source.setOnFocusChangeListener(mActivity.getActivityEventsFetcher());
	}
	
	/**
     * Link a editor action event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: {@code @INTERNAL_OPERATION onEditorAction(TextView view, int actionId, KeyEvent event)}
     *
	 * @param source event source
	 * @param opName operation name
	 */
	protected void linkOnEditorActionToOp(TextView source, String opName) {		
		linkEventToOp(source, ActivityEventsFetcher.ON_EDITOR_ACTION, opName);
		source.setOnEditorActionListener(mActivity.getActivityEventsFetcher());
	}

	/**
	 * Utility methods for doing the event-op linking
	 */
	protected void linkEventToOp(Object source, String eventListener, String opName) {
		HashMap<String,String> map = mEvToOpLinks.get(source);
		if (map==null) {
			map = new HashMap<String,String>();
			mEvToOpLinks.put(source, map);
		}
		map.put(eventListener, opName);
	}
	
	/**
	 * Artifact operation for starting a new activity identified by the intent
	 * provided in input
	 * @param intent
	 */
	@OPERATION public void startActivity(Intent intent) {
		mActivity.getActivity().startActivity(intent);
	}

	/**
	 * Artifact operation for implicitly starting a new activity
	 * 
	 * @param action The action identifying the target activity
	 */
	@OPERATION public void startImplicitActivity(String action) {
		Intent intent = new Intent(action);
		mActivity.getActivity().startActivity(intent);
	}

	/**
	 * Artifact operation for explicitly starting a new activity
	 * 
	 * @param action The target Activity qualified name
	 */
	@OPERATION public void startExplicitActivity(String className) {
		Class<?> classTemplate;
		try {
			classTemplate = Class.forName(className);
			Intent intent = new Intent(mActivity.getActivity().getApplicationContext(), classTemplate);
			mActivity.getActivity().startActivity(intent);
		} catch (ClassNotFoundException e) {
			failed(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Artifact operation for starting a new activity for result with the provided intent
	 *  
	 * @param intent The intent for identifying the target activity
	 * @param requestCode The request code used for requesting a particular operation to the activity 
	 */
	@OPERATION public void startActivityForResult(Intent intent, int requestCode) {
		mActivity.getActivity().startActivityForResult(intent, requestCode);
	}
	
	/**
	 * Artifact operation for implicitly starting a new activity for result with the provided action
	 * 
	 * @param action The action identifying the target activity
	 * @param requestCode The request code used for requesting a particular operation to the activity
	 */
	@OPERATION public void startImplicitActivityForResult(String action, int requestCode) {
		Intent intent = new Intent(action);
		mActivity.getActivity().startActivityForResult(intent, requestCode);
	}

	/**
	 * Artifact operation for explicitly starting a new activity for result
	 * 
	 * @param action The target Activity qualified name
	 * @param requestCode The request code used for requesting a particular operation to the activity
	 */
	@OPERATION public void startExplicitActivityForResult(String className, int requestCode) {
		Class<?> classTemplate;
		try {
			classTemplate = Class.forName(className);
			Intent intent = new Intent(mActivity.getActivity().getApplicationContext(), classTemplate);
			mActivity.getActivity().startActivityForResult(intent, requestCode);
		} catch (ClassNotFoundException e) {
 			failed(e.getLocalizedMessage());
		}
	}

	/**
	 * Artifact operation for starting a new Service with the provided intent
	 * @param service
	 */
	@OPERATION public void startService(Intent service) {
		mActivity.getActivity().startService(service);
	}

	/**
	 * Artifact operation for implicitly starting a new Android Service
	 * 
	 * @param action The action for used for identifying the Service. This
	 * action should be also specified in the Service IntentFilters in order
	 * to successfully start the Service. 
	 */
	@OPERATION public void startImplicitService(String action) {
		Intent intent = new Intent(action);
		mActivity.getActivity().startService(intent);
	}

	/**
	 * Artifact operation for explicitly starting a new Android Service.
	 * 
	 * @param className The qualified name of the service to start.
	 */
	@OPERATION public void startExplicitService(String className) {
		Class<?> classTemplate;
		try {
			classTemplate = Class.forName(className);
			Intent intent = new Intent(mActivity.getActivity().getApplicationContext(), classTemplate);
			mActivity.getActivity().startService(intent);
		} catch (ClassNotFoundException e) {
			failed(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Artifact operation for stopping an Android Service identified by
	 * the intent provided in input
	 * @param service
	 */
	@OPERATION public void stopService(Intent service) {
		mActivity.getActivity().stopService(service);
	}
	
	/**
	 * Artifact operation for implicitly stopping an Android Service identified by
	 * the action param provided in input
	 * @param action
	 */
	@OPERATION public void stopImplicitService(String action) {
		Intent intent = new Intent(action);
		mActivity.getActivity().stopService(intent);
	}
	
	/**
	 * Artifact operation for explicitly stopping an Android Service
	 * @param className The qualified name of the service to stop
	 */
	@OPERATION public void stopExplicitService(String className) {
		Class<?> classTemplate;
		try {
			classTemplate = Class.forName(className);
			Intent intent = new Intent(mActivity.getActivity().getApplicationContext(), classTemplate);
			mActivity.getActivity().stopService(intent);
		} catch (ClassNotFoundException e) {
			failed(e.getLocalizedMessage());
		}
	}	
}