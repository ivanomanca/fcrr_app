package cartago.infrastructure.rmi;

import java.rmi.RemoteException;

import cartago.ArtifactObsProperty;
import cartago.CartagoException;
import cartago.ICartagoController;
import cartago.OperationInfo;
import java.util.*;

public class CartagoControllerProxy implements ICartagoController, java.io.Serializable {

	private ICartagoControllerRemote ctx;

	CartagoControllerProxy(ICartagoControllerRemote ctx) throws CartagoException {
		this.ctx = ctx;
	}
	
	public String[] getCurrentArtifacts() throws CartagoException {
		try {
			return ctx.getCurrentArtifacts();
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}

	public String[] getCurrentAgents() throws CartagoException {
		try {
			return ctx.getCurrentAgents();
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}

	public boolean removeArtifact(String artifactName) throws CartagoException {
		try {
			 return ctx.removeArtifact(artifactName);
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}

	public boolean removeUser(String userName) throws CartagoException {
		try {
			return ctx.removeUser(userName);
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}


	public List<ArtifactObsProperty> getObsProperties(String artifactName)
			throws CartagoException {
		try {
			return ctx.getObsProperties(artifactName);
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}

	public OperationInfo[] getOpInExecution(String artifactName)
			throws CartagoException {
		try {
			return ctx.getOpInExecution(artifactName);
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}

	public String[] getUsageInterface(String artifactName) throws CartagoException {
		try {
			return ctx.getUsageInterface(artifactName);
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}
}
