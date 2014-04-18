package com.cloud.test;

import android.os.IInterface;
import android.os.RemoteException;

public interface ICloudManager extends IInterface {
	static final java.lang.String DESCRIPTOR = "com.cloud.test.ICloudManager";

	void test() throws RemoteException;

	void print(String str) throws RemoteException;

	int add(int a, int b) throws RemoteException;

	static final int TRANSACTION_test = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
	static final int TRANSACTION_print = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
	static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
