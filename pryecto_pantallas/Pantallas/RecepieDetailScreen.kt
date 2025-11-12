package uvg.giancarlo.pryecto_pantallas.Pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import uvg.giancarlo.pryecto_pantallas.Model.DrinksRepository
import uvg.giancarlo.pryecto_pantallas.navigation.Screen


@Composable
fun RecipeDetailScreen(
    changeScreen: (Screen) -> Unit,
    drinkId: Int? = null,
    recipeName: String? = null,
    onBack: () -> Unit = {}
) {
    // Obtener la bebida por ID o por nombre
    val drink = when {
        drinkId != null -> DrinksRepository.getDrinkById(drinkId)
        recipeName != null -> DrinksRepository.getDrinkByName(recipeName)
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                onBack()
                changeScreen(Screen.HomeScreen)
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(text = "< Atrás")
        }

        if (drink == null) {
            Text(
                text = "Receta no encontrada",
                style = MaterialTheme.typography.titleMedium
            )
            return@Column
        }

        Text(text = drink.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = drink.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Ingredientes:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(drink.ingredients) { ing ->
                Text(
                    text = "• $ing",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Preparación:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = drink.preparation, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailPreview() {
    RecipeDetailScreen(
        changeScreen = {},
        drinkId = 100
    )
}