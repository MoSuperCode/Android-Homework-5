package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.AddContactScreen
import com.example.myapplication.ui.screens.ContactDetailScreen
import com.example.myapplication.ui.screens.ContactListScreen
import com.example.myapplication.ui.viewmodel.ContactViewModel

@Composable
fun ContactNavigation(
    navController: NavHostController,
    contactViewModel: ContactViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = "contact_list"
    ) {
        composable("contact_list") {
            ContactListScreen(
                navController = navController,
                contactViewModel = contactViewModel
            )
        }

        composable("add_contact") {
            AddContactScreen(
                navController = navController,
                contactViewModel = contactViewModel
            )
        }

        composable(
            "contact_detail/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.LongType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getLong("contactId") ?: 0L
            ContactDetailScreen(
                navController = navController,
                contactViewModel = contactViewModel,
                contactId = contactId
            )
        }
    }
}