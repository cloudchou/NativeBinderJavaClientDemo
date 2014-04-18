package com.cloud.test;

import android.os.IBinder;
import android.os.RemoteException;

public class CloudManagerProxy implements ICloudManager {
	private android.os.IBinder mRemote;

	public CloudManagerProxy(android.os.IBinder remote) {
		mRemote = remote;
	}

	public java.lang.String getInterfaceDescriptor() {
		return DESCRIPTOR;
	}

	@Override
	public void print(String str) throws RemoteException {
		android.os.Parcel _data = android.os.Parcel.obtain();
		android.os.Parcel _reply = android.os.Parcel.obtain();
		try {
			_data.writeInterfaceToken(DESCRIPTOR);
			_data.writeString(str);
			mRemote.transact(TRANSACTION_print, _data, _reply, 0);
			_reply.readException();
		} finally {
			_reply.recycle();
			_data.recycle();
		}
	}

	@Override
	public int add(int a, int b) throws RemoteException {
		android.os.Parcel _data = android.os.Parcel.obtain();
		android.os.Parcel _reply = android.os.Parcel.obtain();
		int result = 0;
		try {
			_data.writeInterfaceToken(DESCRIPTOR);
			_data.writeInt(a);
			_data.writeInt(b);
			mRemote.transact(TRANSACTION_add, _data, _reply, 0);
			_reply.readException();
			result = _reply.readInt();
		} finally {
			_reply.recycle();
			_data.recycle();
		}
		return result;
	}

	@Override
	public IBinder asBinder() {
		return mRemote;
	}

	@Override
	public void test() throws RemoteException {
		android.os.Parcel _data = android.os.Parcel.obtain();
		android.os.Parcel _reply = android.os.Parcel.obtain(); 
		try {
			_data.writeInterfaceToken(DESCRIPTOR); 
			mRemote.transact(TRANSACTION_test, _data, _reply, 0);
			_reply.readException(); 
		} finally {
			_reply.recycle();
			_data.recycle();
		}

	}

}
