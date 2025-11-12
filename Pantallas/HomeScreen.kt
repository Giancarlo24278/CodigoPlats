package uvg.giancarlo.pryecto_pantallas.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.giancarlo.pryecto_pantallas.R
import uvg.giancarlo.pryecto_pantallas.navigation.Screen


@Composable
fun HomeScreen(
    changeScreen: (Screen) -> Unit,
    onRecipeClick: (String) -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSeeAllRecipesClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F8))
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Logotipo",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF6A1B9A)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Drinkspiration",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil de usuario",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable {
                            onProfileClick()
                            changeScreen(Screen.ProfileScreen)
                        }
                        .padding(8.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF6A1B9A)
                )
            )
            Spacer(Modifier.height(24.dp))
        }

        item {
            SectionHeader(title = "Categorías", onSeeAllClick = { /* Navegar a todas las categorías */ })
            Spacer(Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    CategoryCard("Cocktails", Color(0xFF8E44AD), onClick = {
                        onCategoryClick("100") // ID para Cocktails
                        changeScreen(Screen.CategoryListScreen)
                    })
                }
                item {
                    CategoryCard("Mocktails", Color(0xFF1ABC9C), onClick = {
                        onCategoryClick("101") // ID para Mocktails
                        changeScreen(Screen.CategoryListScreen)
                    })
                }
                item {
                    CategoryCard("Shakes", Color(0xFFF1C40F), onClick = {
                        onCategoryClick("102") // ID para Shakes
                        changeScreen(Screen.CategoryListScreen)
                    })
                }
            }
            Spacer(Modifier.height(24.dp))
        }

        item {
            SectionHeader(
                title = "Recetas recientes",
                onSeeAllClick = {
                    onSeeAllRecipesClick()
                    changeScreen(Screen.FavoritesScreen)
                }
            )
            Spacer(Modifier.height(8.dp))
            RecentRecipeCard(
                name = "Mojito de Fresa",
                onClick = { recipe ->
                    onRecipeClick(recipe)
                },
                changeScreen = changeScreen
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
internal fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(
            text = "See All",
            color = Color(0xFF6A1B9A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onSeeAllClick)
        )
    }
}

@Composable
internal fun CategoryCard(name: String, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 120.dp, height = 160.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
internal fun RecentRecipeCard(
    name: String = "Mocktail",
    onClick: (String) -> Unit,
    changeScreen: (Screen) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onClick(name)
                changeScreen(Screen.FavoritesScreen)
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C3E))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(name, style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Favorite, contentDescription = "Likes", tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("534", color = Color.White, fontSize = 14.sp)
            }
            Spacer(Modifier.height(12.dp))
            Row {
                repeat(5) {
                    Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFF1C40F))
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun HomeScreenPreview() {
    HomeScreen(changeScreen = {})
}