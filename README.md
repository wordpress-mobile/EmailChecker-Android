# Email Checker for Android and iOS

## Introduction

This library helps to catch simple email domain typos. Its intended to
be used as a hint when a user have to enter an email address.

The library is written in Kotlin and is inspired by the algorithm
described here: http://norvig.com/spell-correct.html (Warning, it's
not the exact same algo).

## How to use it in an Android project

If you want to use it in your Android project, your can add it as a
library in your build.gradle file, don't forget to add the
wordpress-mobile maven repository. For instance:

    dependencies {
        // use the latest 0.x version
        compile 'org.wordpress:emailchecker2:0.+'
    }

Sample usage:

    String emailToCheck = "salut@gmial.com";
    String suggest = (new EmailChecker()).suggestDomainCorrection(email);
    if (suggest.compareTo(email) != 0) {
        Log.v("MYAPP", "did you mean: " + suggest + " ?");
    }

## Hack it

### Directory structure

    |-- example                  # common C++ native code
    `-- emailchecker
        |-- jni                 # android specific C++ native code
        `-- src                 # android specific Java code

### Build

* For Android

    ```
    $ ./gradlew build
    ```

## Apps that use this library

### [WordPress for Android][1]

![Screenshot from WordPress Android](https://i.cloudup.com/rUxkHNsm5c.png)

## LICENSE

This library is dual licensed unded MIT and GPL v2.


## CHANGELOG

### 0.1

* Initial release


[1]: https://github.com/wordpress-mobile/WordPress-Android
