package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.theme.BrandGold
import com.example.ui.theme.CardSurface
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.TextLight
import com.example.ui.viewmodel.AppTab

@Composable
fun AppBottomNavigation(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit
) {
    NavigationBar(
        containerColor = CardSurface,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentTab == AppTab.HOME,
            onClick = { onTabSelected(AppTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            modifier = Modifier.testTag("nav_home"),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldDark,
                indicatorColor = BrandGold.copy(alpha = 0.25f),
                unselectedIconColor = TextLight,
                unselectedTextColor = TextLight
            )
        )
        NavigationBarItem(
            selected = currentTab == AppTab.DASHBOARD,
            onClick = { onTabSelected(AppTab.DASHBOARD) },
            icon = { Icon(Icons.Default.SelfImprovement, contentDescription = "5-Day Challenge") },
            label = { Text("Challenge") },
            modifier = Modifier.testTag("nav_challenge"),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldDark,
                indicatorColor = BrandGold.copy(alpha = 0.25f),
                unselectedIconColor = TextLight,
                unselectedTextColor = TextLight
            )
        )
        NavigationBarItem(
            selected = currentTab == AppTab.INNER_SHIFT,
            onClick = { onTabSelected(AppTab.INNER_SHIFT) },
            icon = { Icon(Icons.Default.Diamond, contentDescription = "Inner Shift") },
            label = { Text("Inner Shift") },
            modifier = Modifier.testTag("nav_innershift"),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldDark,
                indicatorColor = BrandGold.copy(alpha = 0.25f),
                unselectedIconColor = TextLight,
                unselectedTextColor = TextLight
            )
        )
        NavigationBarItem(
            selected = currentTab == AppTab.CAREER_AXIS,
            onClick = { onTabSelected(AppTab.CAREER_AXIS) },
            icon = { Icon(Icons.Default.TrendingUp, contentDescription = "Career Axis") },
            label = { Text("Career Axis") },
            modifier = Modifier.testTag("nav_career"),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldDark,
                indicatorColor = BrandGold.copy(alpha = 0.25f),
                unselectedIconColor = TextLight,
                unselectedTextColor = TextLight
            )
        )
        NavigationBarItem(
            selected = currentTab == AppTab.GUIDE,
            onClick = { onTabSelected(AppTab.GUIDE) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Toolkit & S.T.O.P.") },
            label = { Text("Toolkit") },
            modifier = Modifier.testTag("nav_guide"),
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldDark,
                indicatorColor = BrandGold.copy(alpha = 0.25f),
                unselectedIconColor = TextLight,
                unselectedTextColor = TextLight
            )
        )
    }
}
