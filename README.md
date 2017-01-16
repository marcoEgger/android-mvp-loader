# android-mvp-loader

This library helps you to implement the MVP pattern for your Android app. It uses no external dependency, only the
Android SDK. The presenters are decoupled from the activities/fragments lifecycles. This is done by using Loaders,
which are bound to an activity/fragment for the whole lifetime, so if the activity gets destroyed and recreated by
e.g. a configuration change the presenter will survive and reattach to the recreated activity/fragment.
See this great
[blog post](https://medium.com/@czyrux/presenter-surviving-orientation-changes-with-loaders-6da6d86ffbbf#.yxxjtchii)
by [Antonio Gutierrez](https://medium.com/@czyrux) about using this method.

# Usage
See the [example](https://github.com/marcoEgger/android-mvp-loader/tree/master/app) to have a look on how to implement
MVP with android-mvp-loader