package com.luismiguel4953.nexamusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NexaApp() }
    }
}

@Composable
fun NexaApp() {
    var selected by remember { mutableStateOf(0) }
    MaterialTheme(colorScheme = darkColorScheme(primary = Color(0xFF00E5FF), background = Color(0xFF07131A), surface = Color(0xFF0C2029))) {
        Scaffold(containerColor = Color(0xFF07131A), bottomBar = {
            NavigationBar(containerColor = Color(0xFF091A22)) {
                listOf("Inicio", "Biblioteca", "Favoritos", "Ajustes").forEachIndexed { i, label ->
                    NavigationBarItem(selected = selected == i, onClick = { selected = i }, icon = { Text(listOf("⌂","♫","♥","⚙")[i], fontSize = 20.sp) }, label = { Text(label) })
                }
            }
        }) { padding ->
            AnimatedVisibility(visible = true, enter = fadeIn(), modifier = Modifier.padding(padding)) {
                Column(Modifier.fillMaxSize().padding(22.dp)) {
                    Text("NEXA", color = Color(0xFF00E5FF), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Tu música, a tu manera.", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(Modifier.height(24.dp))
                    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF0C2029))) {
                        Column(Modifier.padding(22.dp)) {
                            Text("Ahora sonando", color = Color(0xFF9AB4BC))
                            Text("Tu biblioteca está lista", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(14.dp))
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Importar música") }
                        }
                    }
                    Spacer(Modifier.height(22.dp))
                    Text("Acciones rápidas", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ActionCard("♫", "Biblioteca", Modifier.weight(1f))
                        ActionCard("♥", "Favoritos", Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ActionCard(icon: String, title: String, modifier: Modifier = Modifier) {
    Card(modifier, shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF0C2029))) {
        Column(Modifier.padding(18.dp)) { Text(icon, fontSize = 26.sp, color = Color(0xFF00E5FF)); Text(title, color = Color.White, fontWeight = FontWeight.Bold) }
    }
}
