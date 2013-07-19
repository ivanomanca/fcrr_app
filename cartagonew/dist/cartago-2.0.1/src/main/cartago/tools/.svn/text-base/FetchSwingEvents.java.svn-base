package cartago.tools;

import cartago.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class FetchSwingEvents implements IBlockingCmd, ActionListener, ItemListener, ListSelectionListener, WindowListener  {

	private BlockingQueue<EventOpInfo> events;
	private EventOpInfo fetched;
	
	public FetchSwingEvents(){
		events = new java.util.concurrent.ArrayBlockingQueue<EventOpInfo>(100);
		fetched = null;
	}
	
	public void exec(){
		try {
			fetched = events.take();
		} catch (Exception ex){
		}
	}
	
	public EventOpInfo getCurrentEventFetched(){
		return fetched;
	}

	public AbstractAction getAbstractAction(){
		return new AbstractAction(){
			public void actionPerformed(ActionEvent event){
				try {
					events.put(new EventOpInfo("keyPressed",event));
				} catch (Exception ex){}
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			events.put(new EventOpInfo("actionPerformed",e));
			
		} catch (Exception ex){}
	}

	public void itemStateChanged(ItemEvent e){
		try {
			events.put(new EventOpInfo("itemStateChanged",e));
		} catch (Exception ex){}
	}

	public void valueChanged(ListSelectionEvent event){
		try {
			events.put(new EventOpInfo("valueChanged",event));
		} catch (Exception ex){}
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	public void windowClosing(WindowEvent e) {
		try {
			events.put(new EventOpInfo("windowClosing",e));
		} catch (Exception ex){}
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}	
