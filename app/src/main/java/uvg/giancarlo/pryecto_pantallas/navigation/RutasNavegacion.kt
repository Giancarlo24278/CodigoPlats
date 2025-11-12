package uvg.giancarlo.pryecto_pantallas.navigation


import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object LogIn : Screen

    @Serializable
    data object HomeScreen : Screen

    @Serializable
    data object CreateRecipeScreen : Screen

    @Serializable
    data object ProfileScreen : Screen

    @Serializable
    data object FavoritesScreen : Screen

    @Serializable
    data object CategoryListScreen : Screen

    @Serializable
    data object RecipeDetailScreen : Screen
}
