package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BrandEmerald
import com.example.ui.theme.BrandGold
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldDark
import com.example.ui.viewmodel.AppTab

@Composable
fun AppBottomNavigation(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x33D4AF37),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        containerColor = EmeraldDeep,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            NavigationItemData(AppTab.HOME, "Home", Icons.Default.Home, "nav_home"),
            NavigationItemData(AppTab.DASHBOARD, "Challenge", Icons.Default.AutoAwesome, "nav_challenge"),
            NavigationItemData(AppTab.INNER_SHIFT, "Inner Shift", Icons.Default.Headphones, "nav_inner_shift"),
            NavigationItemData(AppTab.CAREER_AXIS, "Career Axis", Icons.Default.Work, "nav_career_axis"),
            NavigationItemData(AppTab.GUIDE, "Guide", Icons.Default.Psychology, "nav_guide")
        )

        items.forEach { item ->
            val isSelected = currentTab == item.tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(item.tab) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandGold,
                    selectedTextColor = BrandGold,
                    indicatorColor = EmeraldDark,
                    unselectedIconColor = Color(0xFF94A3B8),
                    unselectedTextColor = Color(0xFF94A3B8)
                ),
                modifier = Modifier.testTag(item.testTag)
            )
        }
    }
}

private data class NavigationItemData(
    val tab: AppTab,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val testTag: String
)
