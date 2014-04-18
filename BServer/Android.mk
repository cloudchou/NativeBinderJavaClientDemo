LOCAL_PATH := $(call my-dir)
 

include $(CLEAR_VARS)
LOCAL_SHARED_LIBRARIES := \
    libcutils \
    libutils \
    libbinder       
LOCAL_MODULE    := native_bserver
LOCAL_SRC_FILES := \
    ICloudManager.cpp \
    TestServer.cpp
   
LOCAL_MODULE_TAGS := optional
include $(BUILD_EXECUTABLE)
 
include $(CLEAR_VARS)
LOCAL_SHARED_LIBRARIES := \
    libcutils \
    libutils \
    libbinder
LOCAL_MODULE    := native_bclient
LOCAL_SRC_FILES := \
    ICloudManager.cpp \
    TestClient.cpp
LOCAL_MODULE_TAGS := optional
include $(BUILD_EXECUTABLE)
 