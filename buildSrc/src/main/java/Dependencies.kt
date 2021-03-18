object Versions {
    const val minSdk = 21
    const val targetSdk = 29

    // buildscript
    const val kotlin = "1.4.30"
    const val androidPlugin = "4.1.1"
    const val ktlint = "0.32.0"


    // support library
    const val appcompat = "1.2.0"
    const val material = "1.1.0-rc02"
    const val cardview = "1.0.0"
    const val recyclerview = "1.1.0"
    const val constraintLayout = "2.0.0-beta4"
    const val ktx = "1.1.0"
    const val lifecycle = "2.2.0"
    const val annotation = "1.1.0"
    const val paging = "3.0.0-alpha13"

    // navigation
    const val navigation = "2.3.2"

    // http
    const val apollo = "2.5.4"
    const val glide = "4.9.0"
    const val loggingInterceptor = "3.14.1"

    // injection
    const val koin = "2.1.5"

    // misc
    const val threetenabp = "1.2.0"

    // local db
    const val room = "2.2.5"

    // test
    const val junit = "4.12"
    const val mockk = "1.9.2"
    const val arch = "2.1.0-beta01"
    const val testCore = "1.2.0"
    const val testRunner = "1.2.0-beta01"
    const val testRules = "1.2.0-beta01"
    const val robolectric = "4.3"
    const val androidXJunitExt = "1.1.1"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // injection
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koin}"
    const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabp}"

    // http
    const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:${Versions.apollo}"
    const val apolloCoroutines = "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}@aar"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // local db
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // paging
    const val pagingCommonKtx = "androidx.paging:paging-common-ktx:${Versions.paging}"
}

object SupportLibs {
    // support
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val navigationFragKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationRuntime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val androidXannotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val pagingKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"

    const val coreTesting = "androidx.arch.core:core-testing:${Versions.arch}"
    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val androidXJunitExt = "androidx.test.ext:junit:${Versions.androidXJunitExt}"
}