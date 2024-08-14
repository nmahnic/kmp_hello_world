package com.nicomahnic.helloworldkmp.bottomBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.nicomahnic.helloworldkmp.bottomBar.tabs.FavTab
import com.nicomahnic.helloworldkmp.bottomBar.tabs.HomeTab
import com.nicomahnic.helloworldkmp.bottomBar.tabs.ProfileTab

class BottomBarScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(it, listOf(HomeTab, FavTab, ProfileTab))
            }
        ) {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text(it.current.options.title) },
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val tabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = { Text(HomeTab.options.title) },
                            onClick = { tabNavigator.current = HomeTab },
                            icon = { Icon(painter = HomeTab.options.icon!!, contentDescription = null) }
                        )
                        NavigationBarItem(
                            selected = tabNavigator.current.key == FavTab.key,
                            label = { Text(FavTab.options.title) },
                            onClick = { tabNavigator.current = FavTab },
                            icon = { Icon(painter = FavTab.options.icon!!, contentDescription = null) }
                        )
                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProfileTab.key,
                            label = { Text(ProfileTab.options.title) },
                            onClick = { tabNavigator.current = ProfileTab },
                            icon = { Icon(painter = ProfileTab.options.icon!!, contentDescription = null) }
                        )
                    }
                },
                content = {
                    Box(
                        modifier = Modifier.padding(
                            bottom = it.calculateBottomPadding(),
                            top = it.calculateTopPadding()
                        )
                    ) {
                        CurrentTab()
                    }
                }
            )
        }
    }

}