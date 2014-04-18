#include "ICloudManager.h"
namespace android
{
    class BnCloudManager : public BnInterface<ICloudManager>
    {
    public:
        virtual status_t
        onTransact(uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags = 0);
        virtual void   test();
        virtual void   print(const char* str);
        virtual int   add(int a, int b);
    };

    status_t
    BnCloudManager::onTransact(uint_t code, const Parcel& data, Parcel* reply, uint32_t flags) {
        switch (code)
            {
        case TEST:
            {
                CHECK_INTERFACE(ICloudManager, data, reply);
                test();
                reply->writeInt32(0);
                return NO_ERROR;
            }
            break;
        case PRINT:
            {
                CHECK_INTERFACE(ICloudManager, data, reply);
                String16 str = data.readString16();
                String8 str8 = String8(str);
                print(str8.string());
                reply->writeInt32(0);
                return NO_ERROR;
            }
            break;
        case ADD:
            {
                CHECK_INTERFACE(ITest, data, reply);
                int a;
                int b;
                data.readInt32(&a);
                data.readInt32(&b);
                int c = add(a,b);
                reply->writeInt32(0);
                reply->writeInt32(c);
                return NO_ERROR;
            }
            break;
        default:
            break;
            }
        return NO_ERROR;
    }

    void
    BnCloudManager::test() {
        printf("Now server receive requset from client: [call test]\n");
    }

    void
    BnCloudManager::print(const char* str) {
        printf("Now server receive requset from client: [call print %s]\n", str);
    }

    int
    BnCloudManager::add(int a, int b) {
        printf("Now server receive requset from client: [call add %d %d]\n", a, b);
        return a + b;
    }

}
int
main() {
    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();
    sm->addService(String16("cloudservice"), new BnCloudManager());
    printf("Native binder server starts to work\n");
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();
    return 0;
}
