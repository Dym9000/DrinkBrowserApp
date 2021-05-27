package dependencies.deps

import dependencies.Versions

object Dependencies {

    //SUPPORT
    val ktx_core = "androidx.core:core-ktx:${Versions.ktx}"
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val material_design = "com.google.android.material:material:${Versions.material_design}"

    //ROOM
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    //HILT
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_navigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hilt_navigation}"

    //VIEW MODEL
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    //NAVIGATION
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //COROUTINES
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //RETROFIT, GSON
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson_retrofit = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //LEAK CANARY
    val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"

    //GLIDE
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}