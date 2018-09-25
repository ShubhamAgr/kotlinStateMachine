package com.example.shubham.statemachineimplementation

sealed class States {
    class  Init():States()
    class  StateOne():States()
    class  StateTwo():States()
    class  StateThree():States()
    class  Statefour():States()
    class Final():States()
}