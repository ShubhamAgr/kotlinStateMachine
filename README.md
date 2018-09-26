# kotlinStateMachine
This is the implementation of state machine for the kotlin which can be used in android to render the views on state change
or handle the network connectivity etc. 
I have also implemented the Abstract factory method to manage local and cloud network connection

## AppState
```
interface AppState {
    fun onAction(action:MyActions)
}
```
## AppStateMachine 
```
class AppStateMachine(val context:Context) {
    var LOG_TAG = AppStateMachine::class.java.canonicalName
    private var mState :  AppState by Delegates.observable<AppState>(InitialState()) { _, oldValue, newValue ->
        myStateChanged(oldValue,newValue)
    }

    fun myStateChanged(oldState:AppState, newState:AppState){
        Log.d(LOG_TAG,"${oldState::class.java.canonicalName}-- to --${newState::class.java.canonicalName}")
    }

    fun setState(newState: AppState){
        mNiniState = newState
    }

    fun getState():AppState{
        return mState
    }

    inner class InitialState():AppState{
        override fun onAction(action: MyActions) {
            when(action){
                is MyActions.Initial->{
                    Log.d(LOG_TAG,"Action::Intial Actions recieved")
                    mNiniState = OnNoInternetConnection()
                }
            }
        }

    }

    inner class OnNoInternetConnection():AppState{
        private val LOG_TAG = OnNoInternetConnection::class.java.canonicalName
        override fun onAction(action: MyActions) {
         when(action){
             is MyActions.ConnectToCloud ->{
                 Log.d(LOG_TAG,"Action connect to cloud")
             }
         }
        }

    }
}
```

## Actions
```
sealed class MyActions {
    class Initial():MyActions()
    class ConnectToLocal():MyActions()
    class ConnectToCloud():MyActions()
    class Finish:MyActions()
}
```

## Call StateMachine from the activity
```
 val appStateMachine = AppStateMachine(this@MainActivity)
 appStateMachine.getState().onAction(MyActions.Initial())
 appStateMachine.getState().onAction(MyActions.ConnectToCloud())
 ```
