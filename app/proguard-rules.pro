# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


    def retrofitVersion = '2.3.0'
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    implementation("com.squareup.retrofit2:converter-simplexml:$retrofitVersion") {
        exclude module: 'stax'
        exclude module: 'stax-api'
        exclude module: 'xpp3'
    }
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

    implementation "io.reactivex.rxjava2:rxjava:2.1.3"
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.1'

    def daggerVersion = '2.11'
    implementation "com.google.dagger:dagger-android:$daggerVersion"
    implementation "com.google.dagger:dagger-android-support:$daggerVersion"
    kapt "com.google.dagger:dagger-compiler:$daggerVersion"
    kapt "com.google.dagger:dagger-android-processor:$daggerVersion"

    def moshiVersion = '1.5.0'
    implementation "com.squareup.moshi:moshi:$moshiVersion"
    implementation "com.squareup.moshi:moshi-kotlin:$moshiVersion"

    kapt 'com.android.databinding:compiler:2.3.3'

    implementation 'com.jakewharton.threetenabp:threetenabp:1.0.5'

    implementation 'com.github.PierfrancescoSoffritti:AndroidYouTubePlayer:0.7.7'

    def leakCanaryVersion = '1.5.3'
    debugImplementation "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    releaseImplementation "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"

    def glideVersion = '4.1.1'
    implementation "com.github.bumptech.glide:glide:$glideVersion"
    implementation("com.github.bumptech.glide:recyclerview-integration:$glideVersion") {
        // Excludes the support library because it's already included by Glide.
        transitive = false
    }
    kapt "com.github.bumptech.glide:compiler:$glideVersion"

    def archComponentVersion = '1.0.0-beta1'
    implementation "android.arch.persistence.room:runtime:$archComponentVersion"
    kapt "android.arch.persistence.room:compiler:$archComponentVersion"
    androidTestImplementation "android.arch.persistence.room:testing:$archComponentVersion"
    implementation "android.arch.persistence.room:rxjava2:$archComponentVersion"

    debugImplementation 'com.facebook.stetho:stetho:1.5.0'

    implementation('com.crashlytics.sdk.android:crashlytics:2.6.8@aar') {
        transitive = true
    }
