package cartago.infrastructure.android;



import java.io.Serializable;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import cartago.AgentId;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.IAlignmentTest;
import cartago.Op;
import cartago.WorkspaceId;

/**
 * Interface for remote contexts
 *
 * @author mguidi
 *
 */
public interface IAgentBodyRemote extends IInterface {
    
	AgentId getAgentId() throws CartagoException, RemoteException;
	void doAction(long actionId, Op op, IAlignmentTest test, long timeout) throws  RemoteException, CartagoException;
	void doAction(long actionId, ArtifactId id, Op op, IAlignmentTest test, long timeout) throws  RemoteException, CartagoException;
	void doAction(long actionId, String name, Op op, IAlignmentTest test, long timeout) throws  RemoteException, CartagoException;
	WorkspaceId getWorkspaceId() throws  RemoteException, CartagoException;
	void ping() throws RemoteException;
	
	/** Local-side IPC implementation stub class. */
	public static abstract class Stub extends android.os.Binder implements IAgentBodyRemote {
		private static final java.lang.String DESCRIPTOR = "cartago.infrastructure.android.IAgentBodyRemote";
		/** Construct the stub at attach it to the interface. */
		public Stub() {
			this.attachInterface(this, DESCRIPTOR);
		}
		/**
		 * Cast an IBinder object into an ICartagoContextRemote interface,
		 * generating a proxy if needed.
		 */
		public static IAgentBodyRemote asInterface(android.os.IBinder obj) {
			if ((obj==null)) {
				return null;
			}
			android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
			if (((iin!=null)&&(iin instanceof IAgentBodyRemote))) {
				return ((IAgentBodyRemote)iin);
			}
			return new IAgentBodyRemote.Stub.Proxy(obj);
		}

		public android.os.IBinder asBinder(){
			return this;
		}
		
		@Override 
		public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
			switch (code){
			case INTERFACE_TRANSACTION:
			{
				reply.writeString(DESCRIPTOR);
				return true;
			}
			case TRANSACTION_doAction1:
			{
				data.enforceInterface(DESCRIPTOR);
				long actionId = data.readLong();
				Op op = (Op) data.readSerializable(); 
				IAlignmentTest test = (IAlignmentTest) data.readSerializable();
				long timeout = data.readLong();
				try {
					this.doAction(actionId, op, test, timeout);
					reply.writeNoException();
				} catch (CartagoException e) {
					reply.writeException(e);
				}
				return true;
			}
			case TRANSACTION_doAction2:
			{
				data.enforceInterface(DESCRIPTOR);
				long actionId = data.readLong();
				ArtifactId id = (ArtifactId) data.readSerializable();
				Op op = (Op) data.readSerializable(); 
				IAlignmentTest test = (IAlignmentTest) data.readSerializable();
				long timeout = data.readLong();
				try {
					this.doAction(actionId, id, op, test, timeout);
					reply.writeNoException();
				} catch (CartagoException e) {
					reply.writeException(e);
				}
				return true;
			}
			case TRANSACTION_doAction3:
			{
				data.enforceInterface(DESCRIPTOR);
				long actionId = data.readLong();
				String name = data.readString();
				Op op = (Op) data.readSerializable(); 
				IAlignmentTest test = (IAlignmentTest) data.readSerializable();
				long timeout = data.readLong();
				try {
					this.doAction(actionId, name, op, test, timeout);
					reply.writeNoException();
				} catch (CartagoException e) {
					reply.writeException(e);
				}
				return true;
			}
			case TRANSACTION_getAgentId:
			{
				data.enforceInterface(DESCRIPTOR);
				AgentId id;
				try {
					id = this.getAgentId();
					reply.writeNoException();
					reply.writeSerializable(id);
				} catch (CartagoException e) {
					reply.writeException(e);
				}
				return true;
			}
			case TRANSACTION_getWorkspaceId:
			{
				data.enforceInterface(DESCRIPTOR);
				WorkspaceId id;
				try {
					id = this.getWorkspaceId();
					reply.writeNoException();
					reply.writeSerializable(id);
				} catch (CartagoException e) {
					reply.writeException(e);
				}
				return true;
			}
			case TRANSACTION_ping:
			{
				data.enforceInterface(DESCRIPTOR);
				this.ping();
				reply.writeNoException();
				return true;
			}
			}
			return super.onTransact(code, data, reply, flags);
		}
		
		static final int TRANSACTION_getAgentId = (IBinder.FIRST_CALL_TRANSACTION + 0);
		static final int TRANSACTION_doAction1 = (IBinder.FIRST_CALL_TRANSACTION + 1);
		static final int TRANSACTION_doAction2 = (IBinder.FIRST_CALL_TRANSACTION + 2);
		static final int TRANSACTION_doAction3 = (IBinder.FIRST_CALL_TRANSACTION + 3);
		static final int TRANSACTION_getWorkspaceId = (IBinder.FIRST_CALL_TRANSACTION + 4);
		static final int TRANSACTION_ping = (IBinder.FIRST_CALL_TRANSACTION + 5);
		
		
		private static class Proxy implements IAgentBodyRemote {
			private android.os.IBinder mRemote;
			Proxy(android.os.IBinder remote) {
				mRemote = remote;
			}

			public android.os.IBinder asBinder() {
				return mRemote;
			}

			public java.lang.String getInterfaceDescriptor() {
				return DESCRIPTOR;
			}
			
			@Override
			public void doAction(long actionId, Op op, IAlignmentTest test,
					long timeout) throws RemoteException, CartagoException {
				
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeLong(actionId);
					_data.writeSerializable(op);
					_data.writeSerializable((Serializable) test);
					_data.writeLong(timeout);
					mRemote.transact(Stub.TRANSACTION_doAction1, _data, _reply, 0);
					_reply.readException();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
			}

			@Override
			public void doAction(long actionId, ArtifactId id, Op op,
					IAlignmentTest test, long timeout) throws RemoteException,
					CartagoException {
				
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeLong(actionId);
					_data.writeSerializable(id);
					_data.writeSerializable(op);
					_data.writeSerializable((Serializable) test);
					_data.writeLong(timeout);
					mRemote.transact(Stub.TRANSACTION_doAction2, _data, _reply, 0);
					_reply.readException();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
			}

			@Override
			public void doAction(long actionId, String name, Op op,
					IAlignmentTest test, long timeout) throws RemoteException,
					CartagoException {
				
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeLong(actionId);
					_data.writeString(name);
					_data.writeSerializable(op);
					_data.writeSerializable((Serializable) test);
					_data.writeLong(timeout);
					mRemote.transact(Stub.TRANSACTION_doAction3, _data, _reply, 0);
					_reply.readException();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
			}

			@Override
			public AgentId getAgentId() throws CartagoException,
					RemoteException {
				
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				AgentId id = null;
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					mRemote.transact(Stub.TRANSACTION_getAgentId, _data, _reply, 0);
					_reply.readException();
					id = (AgentId) _reply.readSerializable();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
				return id;
			}

			@Override
			public WorkspaceId getWorkspaceId() throws RemoteException,
					CartagoException {
				
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				WorkspaceId wspId = null;
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					mRemote.transact(Stub.TRANSACTION_getWorkspaceId, _data, _reply, 0);
					_reply.readException();
					wspId = (WorkspaceId) _reply.readSerializable();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
				return wspId;
			}

			@Override
			public void ping() throws RemoteException {
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					mRemote.transact(Stub.TRANSACTION_ping, _data, _reply, 0);
					_reply.readException();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
			}			
		}
	}

}

