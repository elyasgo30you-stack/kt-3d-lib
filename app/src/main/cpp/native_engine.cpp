#include <jni.h>
#include <GLES2/gl2.h>
#include <string>
#include <vector>
#include <cmath>

struct LoadedModel {
    std::string name;
    std::string path;
};

static std::vector<LoadedModel> loadedModels;
static int screenWidth = 1;
static int screenHeight = 1;
static float camYaw = 0.0f;
static float camPitch = 0.0f;

static std::string toString(JNIEnv* env, jstring value) {
    if (!value) return "";

    const char* chars = env->GetStringUTFChars(value, nullptr);
    std::string result(chars);
    env->ReleaseStringUTFChars(value, chars);

    return result;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lib_elyasabdo3d_Native3DEngine_nativeInit(
        JNIEnv* env,
        jobject thiz
) {
    glClearColor(0.08f, 0.10f, 0.14f, 1.0f);
    glEnable(GL_DEPTH_TEST);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lib_elyasabdo3d_Native3DEngine_nativeResize(
        JNIEnv* env,
        jobject thiz,
        jint width,
        jint height
) {
    screenWidth = width;
    screenHeight = height;
    glViewport(0, 0, width, height);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lib_elyasabdo3d_Native3DEngine_nativeBeginFrame(
        JNIEnv* env,
        jobject thiz,
        jfloat cameraYaw,
        jfloat cameraPitch
) {
    camYaw = cameraYaw;
    camPitch = cameraPitch;
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lib_elyasabdo3d_Native3DEngine_nativeDrawModel(
        JNIEnv* env,
        jobject thiz,
        jstring name,
        jstring path,
        jfloat x,
        jfloat y,
        jfloat z,
        jfloat scale,
        jstring animation
) {
    float r = 0.2f + fabs(sin(x * 0.1f)) * 0.6f;
    float g = 0.4f + fabs(sin(z * 0.1f)) * 0.4f;
    float b = 0.8f;

    glClearColor(r * 0.2f, g * 0.2f, b * 0.2f, 1.0f);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lib_elyasabdo3d_Native3DEngine_nativeEndFrame(
        JNIEnv* env,
        jobject thiz
) {
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_lib_elyasabdo3d_ModelLoaderNative_loadModel(
        JNIEnv* env,
        jobject thiz,
        jstring name,
        jstring path
) {
    LoadedModel model;
    model.name = toString(env, name);
    model.path = toString(env, path);

    loadedModels.push_back(model);

    return JNI_TRUE;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_lib_elyasabdo3d_ModelLoaderNative_loadedCount(
        JNIEnv* env,
        jobject thiz
) {
    return static_cast<jint>(loadedModels.size());
}