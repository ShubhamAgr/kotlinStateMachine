package com.example.shubham.statemachineimplementation

sealed class MyActions {
    class Initial():MyActions()
    class ConnectToLocal():MyActions()
    class ConnectToCloud():MyActions()
    class Finish:MyActions()
}