package cartago.infrastructure.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cartago.AgentId;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.IAlignmentTest;
import cartago.ICartagoContext;
import cartago.Op;
import cartago.WorkspaceId;

/**
 * 
 * @author mguidi
 *
 */
public class AgentBodyProxy implements ICartagoContext, Parcelable {

	private IAgentBodyRemote mCtx;
	
	public AgentBodyProxy(IAgentBodyRemote ctx) {
		mCtx = ctx;
	}
	
	@Override
	public void doAction(long actionId, Op op, IAlignmentTest test, long timeout)
			throws CartagoException {
		try {
			mCtx.doAction(actionId, op, test, timeout);
		} catch (RemoteException e) {
			throw new CartagoException(e.getMessage());
		}
	}

	@Override
	public void doAction(long actionId, ArtifactId id, Op op,
			IAlignmentTest test, long timeout) throws CartagoException {
		try {
			mCtx.doAction(actionId, id, op, test, timeout);
		} catch (RemoteException e) {
			throw new CartagoException(e.getMessage());
		}
	}

	@Override
	public void doAction(long actionId, String name, Op op,
			IAlignmentTest test, long timeout) throws CartagoException {
		try {
			mCtx.doAction(actionId, name, op, test, timeout);
		} catch (RemoteException e) {
			throw new CartagoException(e.getMessage());
		}
	}

	@Override
	public AgentId getAgentId() throws CartagoException {
		try {
			return mCtx.getAgentId();
		} catch (RemoteException e) {
			throw new CartagoException(e.getMessage());
		}
	}

	@Override
	public WorkspaceId getWorkspaceId() throws CartagoException {
		try {
			return mCtx.getWorkspaceId();
		} catch (RemoteException e) {
			throw new CartagoException(e.getMessage());
		}
	}
	
	public void ping() throws CartagoException {
		try {
			mCtx.ping();
		} catch (RemoteException ex) {
			throw new CartagoException(ex.getMessage());
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStrongBinder(mCtx.asBinder());
	}
	
	public void readFromParcel(Parcel in) {
		mCtx = IAgentBodyRemote.Stub.asInterface(in.readStrongBinder());
	}
	
	public static final Parcelable.Creator<AgentBodyProxy> CREATOR = new Parcelable.Creator<AgentBodyProxy>() {
        public AgentBodyProxy createFromParcel(Parcel in) {
            return new AgentBodyProxy(in);
        }

        public AgentBodyProxy[] newArray(int size) {
            return new AgentBodyProxy[size];
        }
    };

    private AgentBodyProxy(Parcel in) {
        readFromParcel(in);
    }

}
