#ifndef ICLOUDMANAGER_H_H
#define ICLOUDMANAGER_H_H
#include <stdio.h>
#include <binder/IInterface.h>
#include <binder/Parcel.h>
#include <binder/IBinder.h>
#include <binder/Binder.h>
#include <binder/ProcessState.h>
#include <binder/IPCThreadState.h>
#include <binder/IServiceManager.h>
using namespace android;
namespace android
{
    class ICloudManager : public IInterface
    {
    public:
        DECLARE_META_INTERFACE(CloudManager); // declare macro
        virtual void test()=0;
        virtual void print(const char* str)=0;
        virtual int add(int a, int b)=0;
    };

    enum
    {
        TEST = IBinder::FIRST_CALL_TRANSACTION+1,
        PRINT = IBinder::FIRST_CALL_TRANSACTION+2,
        ADD = IBinder::FIRST_CALL_TRANSACTION+3,
    };

    class BpCloudManager: public BpInterface<ICloudManager> {
    public:
        BpCloudManager(const sp<IBinder>& impl);
    	virtual void test();
    	virtual void print(const char* str);
    	virtual int add(int a, int b);
    };
}
#endif

