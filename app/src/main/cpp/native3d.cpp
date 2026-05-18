#include <jni.h>
#include <string>
#include <algorithm>
#include <cstdint>

static std::string jstringToString(JNIEnv* env, jstring value) {
    if (value == nullptr) {
        return "";
    }

    const char* chars = env->GetStringUTFChars(value, nullptr);
    std::string result(chars);
    env->ReleaseStringUTFChars(value, chars);

    std::transform(result.begin(), result.end(), result.begin(), ::tolower);
    return result;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_lib_elyasabdo3d_Native3D_getVersion(JNIEnv* env, jobject thiz) {
    return env->NewStringUTF("ElyasAbdo3D Native 1.0.0");
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_lib_elyasabdo3d_Native3D_volume(
        JNIEnv* env,
        jobject thiz,
        jfloat width,
        jfloat height,
        jfloat depth
) {
    return width * height * depth;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_lib_elyasabdo3d_Native3D_color(JNIEnv* env, jobject thiz, jstring name) {
    std::string color = jstringToString(env, name);

    if (color == "black") return static_cast<jint>(0xFF000000);
    if (color == "white") return static_cast<jint>(0xFFFFFFFF);
    if (color == "red") return static_cast<jint>(0xFFFF0000);
    if (color == "green") return static_cast<jint>(0xFF00FF00);
    if (color == "blue") return static_cast<jint>(0xFF0000FF);
    if (color == "yellow") return static_cast<jint>(0xFFFFFF00);
    if (color == "cyan") return static_cast<jint>(0xFF00FFFF);
    if (color == "magenta") return static_cast<jint>(0xFFFF00FF);
    if (color == "gray") return static_cast<jint>(0xFF808080);
    if (color == "orange") return static_cast<jint>(0xFFFFA500);
    if (color == "purple") return static_cast<jint>(0xFF800080);
    if (color == "brown") return static_cast<jint>(0xFF8B4513);
    if (color == "pink") return static_cast<jint>(0xFFFFC0CB);
    if (color == "lime") return static_cast<jint>(0xFF32CD32);
    if (color == "sky") return static_cast<jint>(0xFF87CEEB);

    return static_cast<jint>(0xFFFFFFFF);
}