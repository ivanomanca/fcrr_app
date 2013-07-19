/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\ivano\\Dropbox\\wsp\\JaCa-Android-Samples\\src\\tests\\jaca\\android\\tests\\service\\ITestCounter.aidl
 */
package jaca.android.tests.service;
// Declare the interface.

public interface ITestCounter extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements jaca.android.tests.service.ITestCounter
{
private static final java.lang.String DESCRIPTOR = "jaca.android.tests.service.ITestCounter";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an jaca.android.tests.service.ITestCounter interface,
 * generating a proxy if needed.
 */
public static jaca.android.tests.service.ITestCounter asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof jaca.android.tests.service.ITestCounter))) {
return ((jaca.android.tests.service.ITestCounter)iin);
}
return new jaca.android.tests.service.ITestCounter.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_inc:
{
data.enforceInterface(DESCRIPTOR);
this.inc();
reply.writeNoException();
return true;
}
case TRANSACTION_getValue:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getValue();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements jaca.android.tests.service.ITestCounter
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void inc() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_inc, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public int getValue() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getValue, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_inc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void inc() throws android.os.RemoteException;
public int getValue() throws android.os.RemoteException;
}
