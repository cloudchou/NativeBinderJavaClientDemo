/*
 **
 ** Copyright 2007, The Android Open Source Project
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 **     http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */

package com.cloud.test;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;

public class FClient {
	static final java.lang.String DESCRIPTOR = "com.cloud.test.BServer";

	/**
	 * Command-line entry point.
	 * 
	 * @param args
	 *            The command-line arguments
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		System.out.println("==========Client starts===========");
		IBinder binder = ServiceManager.getService("cloudservice");
		ICloudManager manager = new CloudManagerProxy(binder);
		System.out.println("========== client call server  test ===========");
		manager.test();
		System.out.println("========== client call server print ===========");
		manager.print("Hello world");
		System.out.println("========== client call server add ===========");
		int result = manager.add(2, 3);
		System.out.println("manager.add(2, 3)=" + result);
	}
}
