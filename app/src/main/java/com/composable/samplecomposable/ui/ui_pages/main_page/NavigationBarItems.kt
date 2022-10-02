package com.composable.samplecomposable.ui.ui_pages.main_page

import com.composable.samplecomposable.R

sealed class NavigationBarItems(val route: String, val title: String, val icon: Int) {
    object Home : NavigationBarItems("home", "Home", R.drawable.ic_home_icon)
    object Categories : NavigationBarItems("categories", "Categories", R.drawable.ic_search_icon)
    object Cart : NavigationBarItems("cart", "Cart", R.drawable.ic_rocket_icon)
    object Billing : NavigationBarItems("billing", "Billing", R.drawable.ic_clipboard_copy_paste_icon)
    object Account :
        NavigationBarItems("account", "Account", R.drawable.ic_profile_icon)
}
