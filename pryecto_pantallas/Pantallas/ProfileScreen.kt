package uvg.giancarlo.pryecto_pantallas.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import uvg.giancarlo.pryecto_pantallas.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(changeScreen: (Screen) -> Unit,
                  onBackClick: () -> Unit = {},
                  onEditProfile: () -> Unit = {},
                  onFavoritesClick: () -> Unit = {},
                  onCreateRecipeClick: () -> Unit = {}
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick()
                        changeScreen(Screen.HomeScreen)})
                { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás") } },
                actions = { IconButton(onClick = onEditProfile) { Icon(Icons.Default.Edit, contentDescription = "Editar Perfil") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .padding(24.dp),
                tint = Color.Gray
            )
            Spacer(Modifier.height(16.dp))
            Text("Sara Connor", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("@sconnor", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                changeScreen(Screen.CreateRecipeScreen)
            }) {
                Icon(Icons.Default.Create, contentDescription = "Crear", modifier = Modifier.size(ButtonDefaults.IconSize))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Crear nueva receta")
            }

            Spacer(Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ProfileStat("Recetas", "12")
                ProfileStat("Favoritos", "42", Modifier.clickable { onFavoritesClick() })
                ProfileStat("Seguidores", "128")
            }
            Spacer(Modifier.height(24.dp))

            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }, text = { Text("Mis Recetas") })
                Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }, text = { Text("Guardados") })
            }

            Spacer(Modifier.height(16.dp))
            if (selectedTabIndex == 0) {
                Text("Mostrando mis recetas...", modifier = Modifier.fillMaxWidth().padding(16.dp))
            } else {
                Text("Mostrando recetas guardadas...", modifier = Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}

@Composable
private fun ProfileStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

