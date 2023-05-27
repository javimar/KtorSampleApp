package eu.javimar.ktorsample.nav.screens

sealed class HomeGraphScreens(
    val route: String,
) {
    object Listings: HomeGraphScreens(route = LISTING_DEST)
}
const val LISTING_DEST = "listings"


