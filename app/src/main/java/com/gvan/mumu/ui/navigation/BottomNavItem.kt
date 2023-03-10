package com.gvan.mumu.ui.navigation

import com.gvan.mumu.R

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {

    object Home: BottomNavItem("Home", R.drawable.ic_playground, "home")
    object Channels: BottomNavItem("Channels", R.drawable.ic_poem, "channels")
    object Profile: BottomNavItem("Profile", R.drawable.ic_fox, "profile")

}