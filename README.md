# Email Checker for Android

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

```gradle
dependencies {
    // use the latest version
    compile 'org.wordpress:emailchecker2:+'
}
```

Sample usage in Java:

```java
String emailToCheck = "salut@gmial.com";
String suggestion = EmailChecker.suggestDomainCorrection(emailToCheck);
if (suggestion.compareTo(email) != 0) {
    Log.v("MYAPP", "did you mean: " + suggestion + " ?");
}
```

Sample usage in Kotlin:

```kotlin
val emailToCheck = "salut@gmial.com";
val suggestion = suggestDomainCorrection(emailToCheck)
if (suggestion != emailToCheck) {
    Log.v("MYAPP", "did you mean: " + suggestion + " ?");      
}
```

## Hack it

### Directory structure

```
|-- example                 # Example App
`-- emailchecker            # EmailChecker Library
```

### Build

* Build:

```
$ ./gradlew build
```

* Publish to bintray:

```
$ ./gradlew build bintrayUpload -PbintrayUser=XXX -PbintrayKey=XXX -PdryRun=false
```

## LICENSE

This library is dual licensed unded MIT and GPL v2.


## CHANGELOG

### 1.1.1

* Internal changes and updated build files.

### 1.1.0

* Rename the main class name s/EmailCheckerKt/EmailChecker/

### 1.0.0

* Initial release


[1]: https://github.com/wordpress-mobile/WordPress-Android
