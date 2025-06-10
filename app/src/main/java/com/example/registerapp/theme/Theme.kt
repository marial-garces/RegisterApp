package com.example.registerapp.theme

import android.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.roomguideandroid.ui.theme.DarkPurple
import com.example.roomguideandroid.ui.theme.DarkerPurpleGrey
import com.example.roomguideandroid.ui.theme.LightPurple
import com.example.roomguideandroid.ui.theme.LightPurpleLight
import com.example.roomguideandroid.ui.theme.Pink40
import com.example.roomguideandroid.ui.theme.Pink80
import com.example.roomguideandroid.ui.theme.Purple
import com.example.roomguideandroid.ui.theme.Purple40
import com.example.roomguideandroid.ui.theme.Purple80
import com.example.roomguideandroid.ui.theme.PurpleGrey40
import com.example.roomguideandroid.ui.theme.PurpleGrey80
import com.example.roomguideandroid.ui.theme.Typography
import com.example.roomguideandroid.ui.theme.White

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,

    background = LightPurple,
    surface = LightPurpleLight,

    onPrimary = White,
    onSecondary = White,
    onTertiary = White,

    onBackground = DarkPurple,
    onSurface = DarkPurple





)

private val LightColorScheme = lightColorScheme(

    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    background = Purple,
    surface = DarkerPurpleGrey,

    onPrimary = Purple40,
    onSecondary = PurpleGrey40,
    onTertiary = Pink40,

    onBackground = White,
    onSurface = White,


)

@Composable
fun RegisterAppTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}