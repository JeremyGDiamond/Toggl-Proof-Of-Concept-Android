# Toggl-Proof-Of-Concept-Android
An app that stops and starts a timer in toggl using my java backend. For details regarding the implementation of this backend see [Java-Toggl-Control](https://github.com/JeremyGDiamond/Java-Toggl-Control)
This app is only a proof of concept and is not meant to be used as a front end for toggl.
## Note
If you want to try this app out go to togglAccount.java and modify the uName variable to your api token found in the settings of the toggl website. If you don’t do this the errors are unintelligible.

## Layout

There are simply 2 buttons that either start or stop a timer.

## Classes

On a button press the corresponding class is instantiated to start a timer.

### NetworkBackgroundRunTest.java
Launches an AsyncTask and instantiates a togglAccount object called myAccount. Runs myAccount.readAllProjects() and myAccount.startATimer(0,0) as a doInBackground.
### NetworkBackgroundStop.java
Launches an AsyncTask and instantiates a togglAccount object called myAccount. Runs myAccount.stopCurrent() as a doInBackground.
## Future plans

The minimum viable product has 2 main features. 
1. Quick launching timers with predefined descriptions from icon widgets on the home screen and stopping them the same way.
2. A persistent notification, viewable from the lock screen, that monitors the current timer and allows you to stop/launch timers.
