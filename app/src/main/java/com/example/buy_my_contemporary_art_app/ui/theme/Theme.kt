package com.example.buy_my_contemporary_art_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_primary,
    onPrimary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onPrimary,
    primaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_primaryContainer,
    onPrimaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onPrimaryContainer,
    secondary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_secondary,
    onSecondary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onSecondary,
    secondaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_secondaryContainer,
    onSecondaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onSecondaryContainer,
    tertiary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_tertiary,
    onTertiary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onTertiary,
    tertiaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_tertiaryContainer,
    onTertiaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onTertiaryContainer,
    error = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_error,
    errorContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_errorContainer,
    onError = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onError,
    onErrorContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onErrorContainer,
    background = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_background,
    onBackground = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onBackground,
    surface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_surface,
    onSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onSurface,
    surfaceVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_surfaceVariant,
    onSurfaceVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_onSurfaceVariant,
    outline = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_outline,
    inverseOnSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_inverseOnSurface,
    inverseSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_inverseSurface,
    inversePrimary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_inversePrimary,
    surfaceTint = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_surfaceTint,
    outlineVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_outlineVariant,
    scrim = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_primary,
    onPrimary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onPrimary,
    primaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_primaryContainer,
    onPrimaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onPrimaryContainer,
    secondary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_secondary,
    onSecondary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onSecondary,
    secondaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_secondaryContainer,
    onSecondaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onSecondaryContainer,
    tertiary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_tertiary,
    onTertiary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onTertiary,
    tertiaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onTertiaryContainer,
    error = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_error,
    errorContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_errorContainer,
    onError = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onError,
    onErrorContainer = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onErrorContainer,
    background = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_background,
    onBackground = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onBackground,
    surface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_surface,
    onSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onSurface,
    surfaceVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_surfaceVariant,
    onSurfaceVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_onSurfaceVariant,
    outline = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_outline,
    inverseOnSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_inverseOnSurface,
    inverseSurface = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_inverseSurface,
    inversePrimary = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_inversePrimary,
    surfaceTint = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_surfaceTint,
    outlineVariant = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_outlineVariant,
    scrim = com.example.buy_my_contemporary_art_app.ui.theme.md_theme_dark_scrim,
)

@Composable
fun BuyMyContemporaryArtAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
/*
@Composable
fun BuyMyContemporaryArtAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
*/