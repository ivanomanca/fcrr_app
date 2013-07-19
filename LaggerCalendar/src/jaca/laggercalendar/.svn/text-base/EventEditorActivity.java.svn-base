package jaca.laggercalendar;

import jaca.android.dev.JaCaActivity;
import jaca.laggercalendar.artifacts.EventEditorGUI;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EventEditorActivity extends JaCaActivity {
	private Handler mHandler;
	private Button btnAdd;
	private Button btnEdit;
	private Integer eventId;
	private Spinner responsible;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler =  new Handler();
		setContentView(R.layout.event_form);
		btnAdd = (Button) findViewById(R.id.add_event);
		btnEdit = (Button) findViewById(R.id.modify_event);

	    responsible = (Spinner) findViewById(R.id.responsible);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.contacts, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    responsible.setAdapter(adapter);
		createArtifact("event-editor-gui", EventEditorGUI.class.getCanonicalName());
	}

	public void disableEditButton(){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				btnEdit.setEnabled(false);
			}
		});
	}

	public void disableAddButton(){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				btnAdd.setEnabled(false);
			}
		});
	}

	public Integer getEventId(){
		return eventId;
	}
}