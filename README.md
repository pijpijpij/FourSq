# Whitbread

## Introduction
This is my implementation of a [technical test for Whitbread](https://github.com/whitbread-eos/product-developer-test).
The intention is to showcase my technical knowledge and ability. For other similar examples, please refer to:
- [Lottieshow](https://github.com/pijpijpij/LottieShow), a viewer of Lotties, animations defined and implemented by 
AirBnB,
- [Noopetal](https://github.com/pijpijpij/Noopetal), an annotation processor to generate boilerplate code.
- I also wrote a set miscellaneous [utilities](https://bitbucket.org/pijpijpij/android-utils), centered around RxJava
 and Android. Bitbucket Pipelines is the automated build system. The released binaries are available on 
 [Jitpack](https://jitpack.io/#org.bitbucket.pijpijpij/android-utils)


## Scope

### In
- MVVM with RxJava. One characteristic is that View nor ViewModel do NOT handle threads. It's the Interactors that do,
- Retrofit and Moshi,
- AutoValue for DTO and Network data,
- Some Unit tests (randomly chosen)
- Espresso tests (Just to show how they can be setup)
- Build in Travis CI

### Out
- Proguard
- Breaking the code in modules
- Automated release management,
- Automated upload to Google Play,
- Fabric,
- Analytics.

## No time for
Mock web server to use in **repeatable** Espresso tests.

## Dev notes

### Some tools
The other tools I need:
- LeakCanary,
- Butterknife
- RxJava on Android: RxBindings, RxAndroid... 
- Apache common Lang and Collections

## Design considerations

Miscellaneous points/decision made while coding this:
 - We use DI with Dagger2.
 - Features are place in their own packges. There's only one here, but still! 
 - Configuration events supported (i.e. rotation)
 - no specifics for tablet.
 - no use of style
 - Limited number of DTO (1 as of this writing, used from the back-end all the way to the front-end)


## Automated build status
Thanks [Travis-CI](https://travis-ci.org) for providing a build system. The status of the last master build: 
[![Build Status](https://travis-ci.org/pijpijpij/FourSq.svg?branch=master)](https://travis-ci.org/pijpijpij/FourSq)


# License

    Copyright 2017 PiJ International

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

