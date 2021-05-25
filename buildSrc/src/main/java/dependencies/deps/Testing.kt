package dependencies.deps

import dependencies.Versions

object Testing {

    //UNIT TESTING
    const val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
    const val junit_jupiter_params =  "org.junit.jupiter:junit-jupiter-params:${Versions.junit_jupiter}"
    const val junit_jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_jupiter}"

    //MOCKITO
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    //INSTRUMENTATION AND UI TESTING
    const val android_junit_runner = "androidx.test.runner.AndroidJUnitRunner"
    const val junit4 = "junit:junit:${Versions.junit4}"

    //ANDROIDX
    const val test_androidx_ext = "androidx.test.ext:junit:${Versions.test_androidx_ext}"
    const val test_androidx_espresso_core = "androidx.test.ext:junit:${Versions.test_androidx_espresso_core}"

}