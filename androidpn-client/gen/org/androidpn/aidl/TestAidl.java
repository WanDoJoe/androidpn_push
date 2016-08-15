/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\WorkSpace\\androidpn-master\\androidpn-master\\androidpn-client\\src\\org\\androidpn\\aidl\\TestAidl.aidl
 */
package org.androidpn.aidl;
public interface TestAidl extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.androidpn.aidl.TestAidl
{
private static final java.lang.String DESCRIPTOR = "org.androidpn.aidl.TestAidl";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.androidpn.aidl.TestAidl interface,
 * generating a proxy if needed.
 */
public static org.androidpn.aidl.TestAidl asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.androidpn.aidl.TestAidl))) {
return ((org.androidpn.aidl.TestAidl)iin);
}
return new org.androidpn.aidl.TestAidl.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
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
case TRANSACTION_testAidl:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.testAidl();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.androidpn.aidl.TestAidl
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int testAidl() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_testAidl, _data, _reply, 0);
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
static final int TRANSACTION_testAidl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int testAidl() throws android.os.RemoteException;
}
