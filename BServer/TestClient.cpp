#include "ICloudManager.h"
namespace android
{
    BpCloudManager::BpCloudManager(const sp<IBinder>& impl) :
            BpInterface<ICloudManager>(impl) {
    }
    void
    BpCloudManager::test() {
        printf("Client call server test method\n");
        Parcel data, reply;
        data.writeInterfaceToken(ICloudManager::getInterfaceDescriptor());
        remote()->transact(TEST, data, &reply);
        int code = reply.readExceptionCode();
        printf("Server exepction code: %d\n", code);
    }

    void
    BpCloudManager::print(const char* str) {
        printf("Client call server print method\n");
        Parcel data, reply;
        data.writeInterfaceToken(ICloudManager::getInterfaceDescriptor());
        data.writeString16(String16(str));
        remote()->transact(PRINT, data, &reply);
        int code = reply.readExceptionCode();
        printf("Server exepction code: %d\n", code);
    }

    int
    BpCloudManager::add(int a, int b) {
        printf("Client call server add method\n");
        Parcel data, reply;
        data.writeInterfaceToken(ICloudManager::getInterfaceDescriptor());
        data.writeInt32(a);
        data.writeInt32(b);
        remote()->transact(ADD, data, &reply);
        int code = reply.readExceptionCode();
        int result;
        reply.readInt32(&result);
        printf("Server exepction code: %d\n", code);
        return result;
    }

}

int
main() {
    sp<IServiceManager> sm = defaultServiceManager();
    sp<IBinder> binder = sm->getService(String16("cloudservice"));
    sp<ICloudManager> cs = interface_cast<ICloudManager>(binder);
    cs->test();
    cs->print("Hello world");
    int result=cs->add(2,3);
    printf("client receive add result from server : %d\n",result);
    return 0;
}
