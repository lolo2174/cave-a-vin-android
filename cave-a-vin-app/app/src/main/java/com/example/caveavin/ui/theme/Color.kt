package com.example.caveavin.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val WineRed = Color(0xFF8B0000)
val WineRedDark = Color(0xFF5D0000)
val WineGold = Color(0xFFDAA520)
val WineBeige = Color(0xFFF5F5DC)
val WineGray = Color(0xFF757575)

val LightColorScheme = lightColorScheme(
    primary = WineRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD6),
    onPrimaryContainer = Color(0xFF410002),
    secondary = WineGold,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE8CC),
    onSecondaryContainer = Color(0xFF261A00),
    tertiary = Color(0xFF795548),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFD7CCC8),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFF4DDDB),
    onSurfaceVariant = Color(0xFF534341)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB4AB),
    onPrimary = Color(0xFF690005),
    primaryContainer = Color(0xFF93000A),
    onPrimaryContainer = Color(0xFFFFDAD6),
    secondary = Color(0xFFFFB951),
    onSecondary = Color(0xFF3E2E00),
    secondaryContainer = Color(0xFF5A4300),
    onSecondaryContainer = Color(0xFFFFE8CC),
    tertiary = Color(0xFFBCAAA4),
    onTertiary = Color(0xFF2F2927),
    tertiaryContainer = Color(0xFF45403E),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF534341),
    onSurfaceVariant = Color(0xFFD8C2BF)
)
