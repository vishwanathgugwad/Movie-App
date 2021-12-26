# Movie-App / Kotlin / Retrofit / MVVM
Popular movie details application

// A basic movie app with two activities were the first activity displays the popular movie banners from the Movie poster and on click the secondactivity displays the movie details of the selected movie poster.


-Kotiln
-Retrofit for Network call
-Pagination to populate data when scrolled
-Rxjava 
-MVVM architecture

dependencies:
  //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'

    //Gson
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation "com.google.code.gson:gson:2.8.9"

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'

    //Paging
    implementation("androidx.paging:paging-runtime:3.1.0")

    //Rx
    implementation 'io.reactivex.rxjava2:rxjava:2.2.7'
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'

    //logging
    implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'

