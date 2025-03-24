package com.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)
data class ResponsiveTypography(
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle
)

@Immutable
data class AppTheme(
    val colorScheme: ColorScheme,
    val typography: Typography,
    val shapes: Shapes,
    val responsiveTypography: ResponsiveTypography
)

val LocalAppTheme = staticCompositionLocalOf<AppTheme> {
    error("No AppTheme provided")
}

val appTheme: AppTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAppTheme.current

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AboutMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkScheme else lightScheme

    val windowSizeClass = calculateWindowSizeClass()

    val typography = Typography()

    val responsiveTypography = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ResponsiveTypography(
            headlineLarge = typography.headlineLarge.copy(fontSize = 28.sp),
            headlineMedium = typography.headlineMedium.copy(fontSize = 24.sp),
            headlineSmall = typography.headlineSmall.copy(fontSize = 20.sp),
            titleLarge = typography.titleLarge.copy(fontSize = 18.sp),
            titleMedium = typography.titleMedium.copy(fontSize = 16.sp),
            titleSmall = typography.titleSmall.copy(fontSize = 14.sp),
            bodyLarge = typography.bodyLarge.copy(fontSize = 16.sp),
            bodyMedium = typography.bodyMedium.copy(fontSize = 14.sp),
            bodySmall = typography.bodySmall.copy(fontSize = 12.sp),
            labelLarge = typography.labelLarge.copy(fontSize = 14.sp),
            labelMedium = typography.labelMedium.copy(fontSize = 12.sp),
            labelSmall = typography.labelSmall.copy(fontSize = 10.sp),
        )
        WindowWidthSizeClass.Medium -> ResponsiveTypography(
            headlineLarge = typography.headlineLarge.copy(fontSize = 32.sp),
            headlineMedium = typography.headlineMedium.copy(fontSize = 28.sp),
            headlineSmall = typography.headlineSmall.copy(fontSize = 24.sp),
            titleLarge = typography.titleLarge.copy(fontSize = 22.sp),
            titleMedium = typography.titleMedium.copy(fontSize = 20.sp),
            titleSmall = typography.titleSmall.copy(fontSize = 18.sp),
            bodyLarge = typography.bodyLarge.copy(fontSize = 18.sp),
            bodyMedium = typography.bodyMedium.copy(fontSize = 16.sp),
            bodySmall = typography.bodySmall.copy(fontSize = 14.sp),
            labelLarge = typography.labelLarge.copy(fontSize = 16.sp),
            labelMedium = typography.labelMedium.copy(fontSize = 14.sp),
            labelSmall = typography.labelSmall.copy(fontSize = 12.sp),
        )
        else -> ResponsiveTypography(
            headlineLarge = typography.headlineLarge.copy(fontSize = 36.sp),
            headlineMedium = typography.headlineMedium.copy(fontSize = 32.sp),
            headlineSmall = typography.headlineSmall.copy(fontSize = 28.sp),
            titleLarge = typography.titleLarge.copy(fontSize = 26.sp),
            titleMedium = typography.titleMedium.copy(fontSize = 24.sp),
            titleSmall = typography.titleSmall.copy(fontSize = 22.sp),
            bodyLarge = typography.bodyLarge.copy(fontSize = 20.sp),
            bodyMedium = typography.bodyMedium.copy(fontSize = 18.sp),
            bodySmall = typography.bodySmall.copy(fontSize = 16.sp),
            labelLarge = typography.labelLarge.copy(fontSize = 18.sp),
            labelMedium = typography.labelMedium.copy(fontSize = 16.sp),
            labelSmall = typography.labelSmall.copy(fontSize = 14.sp),
        )
    }



    CompositionLocalProvider(
        LocalAppTheme provides AppTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = Shapes(),
            responsiveTypography = responsiveTypography
        )
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = Shapes(),
            content = content
        )
    }
}
