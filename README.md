# Android currency converter

<img src="demo.gif" width="30%">

* Built out of curiosity and necessity to an upcoming holiday trip
* Simple currency converter for android
* Light and dark themes available
* Update to the latest currency rates using the update rates button
* Only has to update the rates once, after that first update it will use that value even if the app is closed

## Build info

```sh
compileSdkVersion 29
buildToolsVersion "29.0.0"
minSdkVersion 28
targetSdkVersion 29
versionCode 1
versionName "1.0"
```

## App info

To get the latest currency exchange values the app uses the api provided by https://fixer.io/

If you want to test it, generate an access key and in app/src/main/java/com/converter/MainActivity.java replace `< ! Insert fixer api access key here ! >` with your actual key
