#include <jni.h>
#include <cmath>

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_lib_elyasabdo3d_World3DNativeMath_distance3D(
        JNIEnv* env,
        jobject thiz,
        jfloat ax,
        jfloat ay,
        jfloat az,
        jfloat bx,
        jfloat by,
        jfloat bz
) {
    const float dx = bx - ax;
    const float dy = by - ay;
    const float dz = bz - az;

    return std::sqrt((dx * dx) + (dy * dy) + (dz * dz));
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_lib_elyasabdo3d_World3DNativeMath_isNear3D(
        JNIEnv* env,
        jobject thiz,
        jfloat ax,
        jfloat ay,
        jfloat az,
        jfloat bx,
        jfloat by,
        jfloat bz,
        jfloat radius
) {
    if (radius < 0.0f) {
        return JNI_FALSE;
    }

    const float dx = bx - ax;
    const float dy = by - ay;
    const float dz = bz - az;

    const float distance = std::sqrt((dx * dx) + (dy * dy) + (dz * dz));

    return distance <= radius ? JNI_TRUE : JNI_FALSE;
}
