package eu.javimar.ktorsample.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import eu.javimar.ktorsample.nav.destinations.postListingsDestination
import eu.javimar.ktorsample.nav.screens.HomeGraphScreens

@Composable
fun ListingsNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeGraphScreens.Listings.route
    ) {
        postListingsDestination()
    }
}