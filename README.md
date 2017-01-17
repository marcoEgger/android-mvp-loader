# android-mvp-loader

[ ![Download](https://api.bintray.com/packages/marcoegger/android/mvp-loader/images/download.svg) ](https://bintray.com/marcoegger/android/mvp-loader/_latestVersion)

This library helps you to implement the MVP pattern for your Android app. It uses no external dependency, only the
Android SDK. The presenters are decoupled from the activities/fragments lifecycles. This is done by using Loaders,
which are bound to an activity/fragment for the whole lifetime, so if the activity gets destroyed and recreated by
e.g. a configuration change the presenter will survive and reattach to the recreated activity/fragment.
See this great
[blog post](https://medium.com/@czyrux/presenter-surviving-orientation-changes-with-loaders-6da6d86ffbbf#.yxxjtchii)
by [Antonio Gutierrez](https://medium.com/@czyrux) about using this method.

## Usage
See the [example](https://github.com/marcoEgger/android-mvp-loader/tree/master/app) to have a look on how to implement
MVP with android-mvp-loader

## License

    MIT License

    Copyright (c) 2017 Marco Egger

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
