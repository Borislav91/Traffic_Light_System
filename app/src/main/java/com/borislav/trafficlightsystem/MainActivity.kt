package com.borislav.trafficlightsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.borislav.trafficlightsystem.presentation.carinput.CarInputScreen
import com.borislav.trafficlightsystem.presentation.trafficlight.TrafficLightScreen
import com.borislav.trafficlightsystem.ui.theme.TrafficLightSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrafficLightSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination = "carInput") {
                        composable("carInput") {
                            CarInputScreen(navController = navController)
                        }
                        composable(
                            route = "trafficLight/{carModel}",
                            arguments = listOf(
                                navArgument("carModel") { type = NavType.StringType }
                            )
                        ) {
                            TrafficLightScreen()
                        }
                    }
                }
            }
        }
    }
}