package uvg.giancarlo.pryecto_pantallas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uvg.giancarlo.pryecto_pantallas.Pantallas.CategoryListScreen
import uvg.giancarlo.pryecto_pantallas.Pantallas.CreateRecipeScreen
import uvg.giancarlo.pryecto_pantallas.Pantallas.FavoritesScreen
import uvg.giancarlo.pryecto_pantallas.Pantallas.HomeScreen
import uvg.giancarlo.pryecto_pantallas.Pantallas.LogIn
import uvg.giancarlo.pryecto_pantallas.Pantallas.ProfileScreen
import uvg.giancarlo.pryecto_pantallas.Pantallas.RecipeDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LogIn
    ) {
        composable<Screen.LogIn> {

            LogIn(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable<Screen.HomeScreen> {
            HomeScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }
        composable<Screen.ProfileScreen> {
            ProfileScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable<Screen.FavoritesScreen> {
            FavoritesScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable<Screen.CategoryListScreen> {
            CategoryListScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }
        composable<Screen.RecipeDetailScreen> {
            RecipeDetailScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable<Screen.CreateRecipeScreen> {
            CreateRecipeScreen(
                changeScreen = { screen ->
                    navController.navigate(screen)
                }
            )
        }
    }
}