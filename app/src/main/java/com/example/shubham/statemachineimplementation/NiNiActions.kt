package com.example.shubham.statemachineimplementation

sealed class NiNiActions {
    class Initial():NiNiActions()
    class ConnectToLocal():NiNiActions()
    class ConnectToCloud():NiNiActions()
    class Finish:NiNiActions()
}