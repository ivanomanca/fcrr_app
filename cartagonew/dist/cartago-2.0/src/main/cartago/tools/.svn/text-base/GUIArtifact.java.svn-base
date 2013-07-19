/**
 * CArtAgO - DEIS, University of Bologna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package cartago.tools;

import cartago.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Base class for defining GUI artifacts
 * 
 * @author aricci
 *
 */
public abstract class GUIArtifact extends Artifact {

	private HashMap<Object,HashMap<String,String>> evToOpLinks;
	private boolean stopped;
	private FetchSwingEvents proc;
	
    protected GUIArtifact() {
    	stopped = false;
		evToOpLinks = new HashMap<Object,HashMap<String,String>>();
    	proc = new FetchSwingEvents();
    }

    @OPERATION void init(){
    	setup();
    	execInternalOp("fetchGUIEvents");
    }
    
    /**
     * This method can be override to initialize the GUI
     */
    public void setup(){}
    
    @INTERNAL_OPERATION void fetchGUIEvents(){
    	while (!stopped){
    		await(proc);
    		EventOpInfo eventOp = proc.getCurrentEventFetched();
    		HashMap<String,String> map = evToOpLinks.get(eventOp.getEvent().getSource());
    		if (map!=null){
    			String opName = map.get(eventOp.getListenerName());
    			if (opName!=null){
    				execInternalOp(opName,eventOp.getEvent());
    			}
    		}
    	}
    }
    
    /**
     * Link a action event to a specific operation of the artifact
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkActionEventToOp(AbstractButton source, String opName){
		insertEventToOp(source,"actionPerformed",opName);
		source.addActionListener(proc);
	}

    /**
     * Link a action event to a specific operation of the artifact
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkActionEventToOp(JComboBox source, String opName){
		insertEventToOp(source,"actionPerformed",opName);
		source.addActionListener(proc);
	}

    /**
     * Link a item event to a specific operation of the artifact
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkItemEventToOp(ItemSelectable source, String opName){
		insertEventToOp(source,"itemStateChanged",opName);
		source.addItemListener(proc);
	}

    /**
     * Link a list selection event to a specific operation of the artifact
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkListSelectionEventToOp(JList source, String opName){
		insertEventToOp(source,"valueChanged",opName);
		source.addListSelectionListener(proc);
	}

	
	/**
     * Link a window closing event to a specific operation of the artifact
     * 
     * @param source event source
     * @param opName name of the operation to trigger
     */
	protected void linkWindowClosingEventToOp(JFrame source, String opName){
		insertEventToOp(source,"windowClosing", opName);
		source.addWindowListener(proc);
	}
	
	/**
     * Link a key stroke event to a specific operation of the artifact
     *
	 * @param source event source
	 * @param key key
	 * @param opName operation name
	 */
	protected void linkKeyStrokeToOp(JComponent source, String key, String opName){		
		insertEventToOp(source,"keyPressed", opName);
		source.getInputMap().put(KeyStroke.getKeyStroke(key), "myAction");
		source.getActionMap().put("myAction",proc.getAbstractAction());
	}
	
	private void insertEventToOp(Object obj, String type, String opName){
		HashMap<String,String> map = evToOpLinks.get(obj);
		if (map==null){
			map = new HashMap<String,String>();
			evToOpLinks.put(obj, map);
		}
		map.put(type, opName);
	}
}
