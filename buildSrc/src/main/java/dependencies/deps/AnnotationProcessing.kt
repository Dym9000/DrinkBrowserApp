package dependencies.deps

import dependencies.Versions

object AnnotationProcessing {
    val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}