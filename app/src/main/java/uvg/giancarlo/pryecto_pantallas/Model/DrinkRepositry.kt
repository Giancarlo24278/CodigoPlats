package uvg.giancarlo.pryecto_pantallas.Model

object DrinksRepository {

    // IDs de categorías - convenio simple
    // 1 -> Cocktails
    // 2 -> Mocktails
    // 3 -> Shakes
    // 4 -> Frozen
    val categories = mapOf(
        1 to "Cocktails",
        2 to "Mocktails",
        3 to "Shakes",
        4 to "Frozen"
    )

    private val drinks = listOf(
        Drink(
            id = 100,
            name = "Tequila Sunrise",
            categoryId = 1,
            description = "Cóctel clásico a base de tequila y jugo de naranja con granadina que crea un efecto degradado parecido a un amanecer.",
            ingredients = listOf("1 ½ oz Tequila", "Jugo de naranja", "¼ oz Granadina", "Hielo", "Guinda o rodaja de limón para decorar"),
            preparation = "En un vaso alto con hielo, agregar el tequila y llenar con jugo de naranja. Añadir la granadina al final para que se asiente y decorar."
        ),
        Drink(
            id = 101,
            name = "Tequila Sunrise Frozen",
            categoryId = 4,
            description = "Versión frozen del clásico Tequila Sunrise: todos los sabores pero servidos en textura frappé/helada.",
            ingredients = listOf("1 ½ oz Tequila", "120 ml Jugo de naranja", "¼ oz Granadina", "1 taza de hielo picado", "Guinda/rodaja de limón para decorar"),
            preparation = "En una licuadora poner el hielo picado, el tequila y el jugo de naranja. Licuar hasta obtener textura frozen. Servir en vaso frío y añadir la granadina lentamente para crear el efecto de color; decorar."
        ),
        Drink(
            id = 102,
            name = "Tequila Sunrise Mocktail",
            categoryId = 2,
            description = "Versión sin alcohol que mantiene el perfil de cítricos y el efecto visual del Tequila Sunrise.",
            ingredients = listOf("120 ml Jugo de naranja", "¼ oz Granadina", "30 ml Agua con gas o soda (opcional)", "Hielo", "Guinda/rodaja de limón"),
            preparation = "En un vaso alto con hielo, verter el jugo de naranja y la soda si se desea. Añadir la granadina al final para que se asiente y decorar con guinda o limón."
        )
    )

    fun getDrinksByCategoryId(categoryId: Int): List<Drink> =
        drinks.filter { it.categoryId == categoryId }

    fun getCategoryName(categoryId: Int): String = categories[categoryId] ?: "Categoría"

    // Obtener bebida por id
    fun getDrinkById(id: Int): Drink? = drinks.find { it.id == id }

    // Obtener bebida por nombre (case-insensitive)
    fun getDrinkByName(name: String): Drink? =
        drinks.find { it.name.equals(name, ignoreCase = true) }

    // Obtener todas las bebidas
    @Suppress("unused")
    fun getAllDrinks(): List<Drink> = drinks.toList()

}