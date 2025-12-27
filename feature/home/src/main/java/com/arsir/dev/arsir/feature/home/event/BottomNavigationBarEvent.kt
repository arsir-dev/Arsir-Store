package com.arsir.dev.arsir.feature.home.event

sealed interface BottomNavigationBarEvent {
    data object OnHome : BottomNavigationBarEvent
    data object OnCategory : BottomNavigationBarEvent
    data object OnCart : BottomNavigationBarEvent
    data object OnSearch : BottomNavigationBarEvent
}