package com.az.composeplayground.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class BoxState {
    Small,
    Expanded
}

@Composable
@Preview
fun TransitionDemo() {
    var boxState by remember { mutableStateOf(BoxState.Small) }

    val transition = updateTransition(targetState = boxState, label = "")

    val color by transition.animateColor(label = "") { state ->
        when (state) {
            BoxState.Small -> Color.Blue
            BoxState.Expanded -> Color.Green
        }
    }

    val size by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Small -> 100.dp
            BoxState.Expanded -> 300.dp
        }
    }

    Box(
        modifier = Modifier
            .size(size)
            .background(color)
            .clickable {
                boxState = when (boxState) {
                    BoxState.Small -> BoxState.Expanded
                    BoxState.Expanded -> BoxState.Small
                }
            },
    )
}