
package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
        setContent {
            FitnessApp()
        }
    }
}

@Composable
fun FitnessApp() {
    var selectedTab by remember { mutableStateOf("Home") }
    val green = Color(0xFFB9F36A)
    val dark = Color(0xFF171A21)

    Scaffold(
        containerColor = dark,
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF242832)) {
                listOf("Home", "Workouts", "Progress", "Profile").forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            Text(
                                when (tab) {
                                    "Home" -> "⌂"
                                    "Workouts" -> "🏋"
                                    "Progress" -> "↗"
                                    else -> "●"
                                },
                                fontSize = 20.sp
                            )
                        },
                        label = { Text(tab) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = dark,
                            selectedTextColor = green,
                            indicatorColor = green,
                            unselectedTextColor = Color.LightGray
                        )
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            item {
                Text(
                    "FITZONE",
                    color = green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    when (selectedTab) {
                        "Home" -> "Let's Get Moving!"
                        "Workouts" -> "Your Workouts"
                        "Progress" -> "Your Progress"
                        else -> "Your Profile"
                    },
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(20.dp))
            }

            when (selectedTab) {
                "Home" -> {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = green
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Column(Modifier.padding(22.dp)) {
                                Text("TODAY'S GOAL", color = dark)
                                Text(
                                    "Stay Strong 💪",
                                    color = dark,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Small steps make big changes.", color = dark)
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Text("Your Activity", color = Color.White, fontSize = 20.sp)
                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            StatCard("Steps", "6,240", Modifier.weight(1f))
                            StatCard("Minutes", "35", Modifier.weight(1f))
                        }
                        Spacer(Modifier.height(12.dp))
                        StatCard("Calories", "280 kcal", Modifier.fillMaxWidth())
                    }
                }

                "Workouts" -> {
                    item {
                        listOf(
                            "Full Body Workout" to "20 min • Beginner",
                            "Cardio Training" to "15 min • Easy",
                            "Strength Training" to "25 min • Intermediate",
                            "Stretching" to "10 min • Beginner"
                        ).forEach { workout ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF292E38)
                                )
                            ) {
                                Column(Modifier.padding(18.dp)) {
                                    Text(
                                        workout.first,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(workout.second, color = green)
                                }
                            }
                        }
                    }
                }

                "Progress" -> {
                    item {
                        StatCard("Workouts Completed", "12", Modifier.fillMaxWidth())
                        Spacer(Modifier.height(12.dp))
                        StatCard("Active Days", "5", Modifier.fillMaxWidth())
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Keep going! Your progress is building.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }

                "Profile" -> {
                    item {
                        Text("Welcome, Fitness User!", color = Color.White, fontSize = 22.sp)
                        Spacer(Modifier.height(12.dp))
                        Text("Fitness level: Beginner", color = green)
                        Text("Goal: Stay active and healthy", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF292E38)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Text(title, color = Color.LightGray, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                value,
                color = Color(0xFFB9F36A),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
