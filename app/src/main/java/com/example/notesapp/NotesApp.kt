package com.example.notesapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.screens.MainScreen
import com.example.notesapp.navigation.Destinations
import com.example.notesapp.screens.AddNoteScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp() {
    Scaffold{
        val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = Destinations.MainScreen.name,
            modifier = Modifier.padding(it)
            ) {
            composable(route = Destinations.MainScreen.name) {
                MainScreen(modifier = Modifier, onAddNote = {
                    navController.navigate("jk/$it")
                })
            }
            composable(route = "jk/{str}",
                arguments = listOf(navArgument(name = "str") {
                    type = NavType.StringType
                })) {
                AddNoteScreen(it.arguments?.getString("str")?:"") {
                    navController.popBackStack()
                }
            }
        }
    }
}
