package com.cloud.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;

public class TestAc extends Activity {

    private static final String TAG = TestAc.class.getSimpleName();
    private ICloudManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid() + "]  onCreate");
        findViewById(R.id.btn_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "=========== Client call CloudService print function");
                    manager.print("Hello world");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "======Client call CloudService add function");
                    int a = manager.add(3, 2);
                    Log.d(TAG, "======Client add function reuslt : " + a);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "======Client call CloudService test function");
                    manager.test();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IBinder binder = ServiceManager.getService("cloudservice");
        manager = new CloudManagerProxy(binder);
        findViewById(R.id.btn_print).setEnabled(true);
        findViewById(R.id.btn_add).setEnabled(true);
        findViewById(R.id.btn_test).setEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
