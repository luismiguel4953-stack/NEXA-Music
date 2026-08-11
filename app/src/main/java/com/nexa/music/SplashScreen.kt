package com.nexa.music

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun NexaSplash(onFinished: () -> Unit) {
    val scale = remember { Animatable(0.72f) }
    var showName by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scale.animateTo(1f, tween(750, easing = FastOutSlowInEasing))
        showName = true
        delay(900)
        onFinished()
    }

    Box(
        Modifier.fillMaxSize().background(Color(0xFF061016)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                Modifier.size(128.dp).scale(scale.value).background(Color(0xFF0A2630), androidx.compose.foundation.shape.RoundedCornerShape(36.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("N", color = Color(0xFF00E5FF), fontSize = 64.sp, fontWeight = FontWeight.Black)
            }
            AnimatedVisibility(visible = showName, enter = fadeIn(tween(450)) + scaleIn()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("NEXA Music", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text("Tu música. Tu experiencia.", color = Color(0xFF8FAAB2), fontSize = 14.sp)
                }
            }
        }
    }
}
