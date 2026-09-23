package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val InnerPeaceColorScheme = lightColorScheme(
    primary = BrandEmerald,
    onPrimary = CardWhite,
    primaryContainer = MintSoft,
    onPrimaryContainer = BrandEmerald,
    secondary = BrandGold,
    onSecondary = EmeraldDeep,
    secondaryContainer = BrandGoldLight.copy(alpha = 0.2f),
    onSecondaryContainer = BrandGoldDark,
    tertiary = BrandGoldDark,
    background = PageBackground,
    onBackground = TextEmerald950,
    surface = CardWhite,
    onSurface = TextEmerald950,
    surfaceVariant = CardCream,
    onSurfaceVariant = TextSlate,
    outline = BorderLight
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = InnerPeaceColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = EmeraldDeep.toArgb()
            window.navigationBarColor = EmeraldDeep.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
