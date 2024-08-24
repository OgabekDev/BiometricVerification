package dev.ogabek.pdponline.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ogabek.pdponline.ui.theme.MainColor

@Composable
fun PasswordDots(
    modifier: Modifier = Modifier,
    total: Int,
    typed: Int,
    defaultColor: Color,
    typedColor: Color
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for(i in 0 until total) {
                Box(
                    modifier = Modifier
                        .size(35.dp, 35.dp)
                        .padding(10.dp)
                        .border(1.dp, if (typed > i) typedColor else defaultColor, CircleShape)
                        .clip(CircleShape)
                        .background(if (typed > i) typedColor else defaultColor)
                ){}
            }
        }
    }
}

@Preview
@Composable
private fun PasswordDotsPreview() {
    PasswordDots(total = 4, typed = 3, defaultColor = Color.Gray, typedColor = MainColor)
}