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
package cartago;

import java.util.*;

/**
 * Interface for CArtAgO controllers for a workspace
 * 
 * @author aricci
 *
 */
public interface ICartagoController {
	
	/**
	 * Get  current user list.
	 * 
	 * @return user names
	 * @throws CartagoException
	 */
	String[] getCurrentAgents()throws CartagoException;
	
	/**
	 * Get current artifact list
	 * @return artifact names
	 * @throws CartagoException
	 */
	String[] getCurrentArtifacts() throws CartagoException;

	/**
	 * Get current observable property set of an artifact
	 * 
	 * @param artifactName name of the artifact
	 * @return
	 * @throws CartagoException
	 */
	List<ArtifactObsProperty> getObsProperties(String artifactName) throws CartagoException;
	
	/**
	 * Get current operation control set
	 * @param artifactName name of the artifact
	 * @return
	 * @throws CartagoException
	 */
	String[] getUsageInterface(String artifactName) throws CartagoException;

	/**
	 * Get current operation in execution
	 * 
	 * @param artifactName
	 * @return
	 * @throws CartagoException
	 */
	OperationInfo[] getOpInExecution(String artifactName) throws CartagoException;

	/**
	 * Remove an artifact from the workspace
	 * 
	 * @param artifactName artifact name
	 * @throws CartagoException
	 */
	boolean removeArtifact(String artifactName) throws CartagoException;

	/**
	 * Remove a user from the workspace
	 * @param userName user name
	 * @throws CartagoException
	 */
	boolean removeUser(String userName) throws CartagoException;	
	
}
