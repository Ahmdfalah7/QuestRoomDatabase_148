package com.example.roomlocaldb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.roomlocaldb.ui.viewmahasiswa.DestinasiInsert
import com.example.roomlocaldb.ui.viewmahasiswa.InsertMhsView

@Composable
fun PengelolaHalaman(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = NavController,
        startDestination = DestinasiInsert.route){
        composable(
            route = DestinasiInsert.route
        ){
            InsertMhsView(
                onBack = {}, onNavigate = {})
        }
    }
}