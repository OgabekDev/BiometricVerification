package dev.ogabek.pdponline.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NumberPadButton(
    modifier: Modifier = Modifier,
    number: Int,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .aspectRatio(1f)
            .border(1.dp, Color.Gray, CircleShape)
            .clip(CircleShape)
            .clickable {
                onClick(number)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$number",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun NumberPadButtonPreview() {
    NumberPadButton(number = 1) {

    }
}