package jaca.laggercalendar.artifacts;

import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import jaca.laggercalendar.EventEditorActivity;
import jaca.laggercalendar.R;

import java.util.Calendar;
import java.util.Date;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import cartago.OPERATION;

public class EventEditorGUI extends GUIArtifact {

	private EventEditorActivity mEventEditorActivity;
	private EditText mTxtDescr;
	private EditText mTxtAddress;
	private EditText mTxtResponsible;
	private DatePicker mEventDate;
	private TimePicker mEventStartTime;
	private TimePicker mEventEndTime;
	private Button btnAdd;
	private Button btnEdit;
	private Integer eventId;
	
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mEventEditorActivity = (EventEditorActivity)activity;
		eventId = mEventEditorActivity.getEventId();
		mTxtDescr = (EditText) mEventEditorActivity.findViewById(R.id.event_descr);
		mEventDate = (DatePicker) mEventEditorActivity.findViewById(R.id.event_date);
		mEventStartTime = (TimePicker) mEventEditorActivity.findViewById(R.id.event_start_time);
		mEventEndTime = (TimePicker) mEventEditorActivity.findViewById(R.id.event_end_time);
		mTxtAddress = (EditText) mEventEditorActivity.findViewById(R.id.address);
		mTxtResponsible = (EditText) mEventEditorActivity.findViewById(R.id.responsible);
		mTxtResponsible = (EditText) mEventEditorActivity.findViewById(R.id.responsible);
		btnAdd = (Button) mEventEditorActivity.findViewById(R.id.add_event);
		btnEdit = (Button) mEventEditorActivity.findViewById(R.id.modify_event);

		if(eventId==null){
			mEventEditorActivity.disableEditButton();
		} else {
			mEventEditorActivity.disableAddButton();
		}
		
		linkOnClickEventToOp(btnAdd, "onClick");
		linkOnClickEventToOp(btnEdit, "onClick");
	}
	
	public void onClick(View view) {
		
		String evDescr = mTxtDescr.getText().toString();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, mEventDate.getDayOfMonth());
		cal.set(Calendar.MONTH, mEventDate.getMonth());
		cal.set(Calendar.YEAR, mEventDate.getYear());
		cal.set(Calendar.HOUR, mEventStartTime.getCurrentHour());
		cal.set(Calendar.MINUTE, mEventStartTime.getCurrentMinute());
		Date startDate = cal.getTime();
		
		cal.set(Calendar.HOUR, mEventEndTime.getCurrentHour());
		cal.set(Calendar.MINUTE, mEventEndTime.getCurrentMinute());
		Date endDate = cal.getTime();
		
		String address = mTxtAddress.getText().toString();
		String responsible = mTxtResponsible.getText().toString();
		
		if(view==btnAdd){
			signal("new_event_btn_click", evDescr, startDate, endDate, address, responsible);
			
		} else if (view==btnEdit){
			signal("modify_event_btn_click", evDescr, startDate, endDate, address, responsible);
		}
	}
}