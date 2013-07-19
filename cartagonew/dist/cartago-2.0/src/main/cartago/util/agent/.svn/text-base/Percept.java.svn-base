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
package cartago.util.agent;

import cartago.CartagoEvent;
import cartago.events.ArtifactObsEvent;

/**
 * Basic class representing a percept
 * 
 * @author aricci
 *
 */
public class Percept implements java.io.Serializable {
	
	private ArtifactObsEvent event;
	
	public Percept(ArtifactObsEvent ev){
			event = ev;
	}	
	
	public CartagoEvent getEvent(){ 
		return event;
	}
	
    public int intContent(int index){
        return event.getSignal().intContent(index);
    }

    /**
     * Get the i-th argument coverted to an double
     * 
     * @param index
     * @return
     */
    public double doubleContent(int index){
        return event.getSignal().doubleContent(index);
    }
    
    /**
     * Get the i-th argument coverted to a boolean
     * 
     * @param index
     * @return
     */
    public boolean booleanContent(int index){
        return event.getSignal().booleanContent(index);
    }

    /**
     * Get the i-th argument coverted to a String
     * 
     * @param index
     * @return
     */
    public String stringContent(int index){
        return event.getSignal().stringContent(index);
    }

    /**
     * Get the arity of the tuple.
     * 
     * @return
     */
    public int getNArgs(){
    	return event.getSignal().getNArgs();
    }
    
    /**
     * Get the string representation of the tuple.
     * 
     * TupleName(TupleArg,...)
     */
    public String toString(){
    	return event.getSignal().toString();
    }
}
