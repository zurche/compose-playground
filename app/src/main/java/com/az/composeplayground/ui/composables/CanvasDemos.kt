package com.az.composeplayground.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CanvasTest() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Magenta,
            size = canvasQuadrantSize
        )
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun DiagonalTest() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue
        )
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CircleTest() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(scaleX = 2f, scaleY = 2f) {
            drawCircle(Color.Blue, radius = 20.dp.toPx())
        }
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun MovingCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 100f, top = -300f) {
            drawCircle(Color.Blue, radius = 200.dp.toPx())
        }
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun RotateDrawing() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45F) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun InsetDrawing() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        inset(horizontal = 50f, vertical = 30f) {
            drawRect(color = Color.Green, size = canvasQuadrantSize)
        }
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun MultipleTransform() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform({
            translate(left = size.width / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

@Composable
//@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun DrawPathTest() {
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width / 2f, size.height / 2f)
                path.lineTo(size.width, 0f)
                path.close()
                onDrawBehind {
                    //drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                    drawArc(
                        color = Color.Blue,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = true,
                        topLeft = Offset(x = 0f, y = 0f),
                        size = size
                    )
                }
            }
            .fillMaxSize()
    )
}

@Composable
//@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
fun CardCutOutTest() {
    val cardSize = 300
    Card(
        modifier = Modifier
            .size(cardSize.dp)
            .clip(RoundedCornerShape(2.dp))
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Spacer(
            modifier = Modifier
                .drawWithCache {
                    val path = Path()
                    path.moveTo(0f, 0f)
                    path.lineTo(size.width / 2f, size.height / 2f)
                    path.lineTo(size.width, 0f)
                    path.close()
                    onDrawBehind {
                        val radius = cardSize / 2f

                        drawCircle(
                            color = Color.White,
                            radius = radius,
                            center = Offset(x = size.width - radius, y = radius)
                        )

                        drawRect(
                            color = Color.White,
                            topLeft = Offset(x = size.width - cardSize, y = 0f),
                            size = size / 5f
                        )

                        drawRect(
                            color = Color.White,
                            topLeft = Offset(
                                x = size.width - (cardSize / 2f),
                                y = (cardSize / 2.08f)
                            ),
                            size = size / 5f
                        )

                        drawRect(
                            color = Color.White,
                            topLeft = Offset(x = size.width - (cardSize / 2f), y = 0f),
                            size = size / 5f
                        )

                        drawRect(
                            color = Color.White,
                            topLeft = Offset(x = size.width - (cardSize * 1.52f), y = 0f),
                            size = size / 5f
                        )

                        drawArc(
                            color = Color.Black,
                            startAngle = 270f,
                            sweepAngle = 90f,
                            useCenter = true,
                            topLeft = Offset(x = size.width - (cardSize * 2.065f), y = 0f),
                            size = Size(
                                width = cardSize.toFloat() + 20f,
                                height = cardSize.toFloat() + 20f
                            )
                        )

                        drawRect(
                            color = Color.White,
                            topLeft = Offset(
                                x = size.width - (cardSize / 2f),
                                y = cardSize.toFloat()
                            ),
                            size = size / 5f
                        )

                        drawArc(
                            color = Color.Black,
                            startAngle = 270f,
                            sweepAngle = 90f,
                            useCenter = true,
                            topLeft = Offset(
                                x = size.width - (cardSize * 1.066f),
                                y = cardSize.toFloat()
                            ),
                            size = Size(
                                width = cardSize.toFloat() + 20f,
                                height = cardSize.toFloat() + 20f
                            )
                        )

                    }
                }
                .fillMaxSize()
        )
    }
}

@Composable
//@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PerformanceChart(list: List<Float> = listOf(102f, 322f, 0f, 150f, 130f)) {

    val augmenterModifierForList = getAugmenterModifierForValue(list.max())

    val zippedList = list.zipWithNext()

    Row(modifier = Modifier.fillMaxHeight()) {
        zippedList.forEach {
            Dot(it, Color.Red, 700.dp, augmenterModifierForList)
        }
    }

}

@Composable
@Preview(heightDp = 300, widthDp = 300, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PerformanceChartV2(list: List<Float> = listOf(153.7f, 154.1f, 153.2f, 155.7f, 156f)) {
    Row(modifier = Modifier.fillMaxWidth()) {
        var lastY = 0f
        val max = list.max()
        val min = list.min()
        for (value in list) {
            val yPercentage = (value - min) / (max - min)
            Canvas(modifier = Modifier.fillMaxHeight().weight(1f), onDraw = {
                if (lastY == 0f) { lastY = size.height }
                val currentPoint = Offset(x = size.width, y = size.height.times(1 - yPercentage))
                drawLine(color = Color.Red, start = Offset(x = 0f, y = lastY), end = currentPoint)
                lastY = currentPoint.y
            })
        }
    }
}

private fun getAugmenterModifierForValue(value: Float): Int =
    when (value) {
        in 0f..9f -> 9
        in 9f..99f -> 9_0
        in 99f..999f -> 9_00
        in 999f..9_999f -> 9_000
        in 9_999f..99_999f -> 90_000
        else -> 900_000
    }

@Composable
fun RowScope.Dot(
    value: Pair<Float, Float> = Pair(10f, 30f),
    color: Color = Color.Red,
    maxHeight: Dp = 500.dp,
    graphAugmenterValue: Int = 90
) {
    val firstItemHeight =
        remember(value.first) { value.first * maxHeight.value / graphAugmenterValue }
    val secondItemHeight =
        remember(value.second) { value.second * maxHeight.value / graphAugmenterValue }

    Canvas(modifier = Modifier.weight(1f), onDraw = {
        val firstValueOffset = Offset(x = 0f, y = maxHeight.value - firstItemHeight)
        val secondValueOffset = Offset(x = size.width, y = maxHeight.value - secondItemHeight)

        drawLine(
            color = color,
            start = firstValueOffset,
            end = secondValueOffset,
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
    })
}