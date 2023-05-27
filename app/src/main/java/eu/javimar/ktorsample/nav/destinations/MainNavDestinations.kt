package eu.javimar.ktorsample.nav.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eu.javimar.ktorsample.nav.screens.HomeGraphScreens
import eu.javimar.ktorsample.presentation.feature.PostListingMain

fun NavGraphBuilder.postListingsDestination() {
    composable(
        route = HomeGraphScreens.Listings.route
    ) {
        PostListingMain()
    }
}