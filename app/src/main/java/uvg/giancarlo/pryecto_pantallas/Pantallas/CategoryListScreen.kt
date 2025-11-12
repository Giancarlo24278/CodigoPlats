package uvg.giancarlo.pryecto_pantallas.Pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.giancarlo.pryecto_pantallas.Model.DrinksRepository
import uvg.giancarlo.pryecto_pantallas.navigation.Screen

@Composable
fun CategoryListScreen(
    changeScreen: (Screen) -> Unit,
    category: String = "",
    drinkId: Int? = null, // ✅ usamos drinkId en lugar de categoryId
    onRecipeClick: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val items = remember(category, drinkId) {
        when {
            drinkId != null -> listOfNotNull(DrinksRepository.getDrinkById(drinkId))
            category.isNotBlank() -> {
                val foundEntry = DrinksRepository.categories.entries.find {
                    it.value.equals(category, ignoreCase = true)
                }
                if (foundEntry != null)
                    DrinksRepository.getDrinksByCategoryId(foundEntry.key)
                else
                    DrinksRepository.getAllDrinks()
            }
            else -> DrinksRepository.getAllDrinks()
        }
    }

    val title = when {
        drinkId != null -> DrinksRepository.getDrinkById(drinkId)?.name ?: "Bebida"
        category.isNotBlank() -> category
        else -> "Bebidas"
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 🔹 Barra superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "< Atrás",
                color = Color(0xFF6A1B9A),
                modifier = Modifier
                    .clickable {
                        onBack()
                        changeScreen(Screen.HomeScreen)
                    }
                    .padding(8.dp)
            )

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.width(56.dp))
        }

        // 🔹 Lista de bebidas (solo 1 en este caso)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(items) { drink ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clickable {
                            onRecipeClick(drink.name)
                            changeScreen(Screen.RecipeDetailScreen)
                        },
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = drink.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = drink.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                maxLines = 2
                            )
                        }
                        Text(
                            text = "Ver",
                            color = Color(0xFF6A1B9A),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    CategoryListScreen(changeScreen = {})
}

