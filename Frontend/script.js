function searchRecipe() {
    const ingredients = document.getElementById("ingredients").value.trim();
    const dishName = document.getElementById("dishName").value.trim();
    const result = document.getElementById("result");

    result.innerHTML = "";

    // ❌ Nothing entered
    if (ingredients === "" && dishName === "") {
        alert("Please enter ingredients or dish name");
        return;
    }

    // ✅ Dish name search has priority
    if (dishName !== "") {
        fetch(`http://localhost:8080/api/recipe?name=${dishName}`)
            .then(res => res.json())
            .then(data => {
                if (data.error) {
                    result.innerHTML = "<p>Dish not found</p>";
                } else {
                    displaySingleRecipe(data);
                }
            })
            .catch(() => {
                result.innerHTML = "<p>Error fetching dish</p>";
            });
        return;
    }

    // ✅ Ingredient-based search
    fetch(`http://localhost:8080/api/recipes?ingredients=${ingredients}`)
        .then(res => res.json())
        .then(data => {
            if (data.length === 0) {
                result.innerHTML = "<p>No matching recipes found</p>";
            } else {
                displayRecipes(data);
            }
        })
        .catch(() => {
            result.innerHTML = "<p>Error fetching recipes</p>";
        });
}

function displayRecipes(recipes) {
    const result = document.getElementById("result");

    recipes.forEach(r => {
        result.innerHTML += `
            <div class="recipe-card">
                <h3>${r.name}</h3>
                <p>⏱ ${r.cookTime} mins</p>
                <button onclick="viewDetails('${r.name}')">
                    View Details
                </button>
            </div>
        `;
    });
}

function viewDetails(name) {
    fetch(`http://localhost:8080/api/recipe?name=${name}`)
        .then(res => res.json())
        .then(data => displaySingleRecipe(data));
}

function displaySingleRecipe(recipe) {
    const result = document.getElementById("result");

    let ingredientsList = recipe.ingredients
        .map(i => `<li>${i}</li>`)
        .join("");

    result.innerHTML = `
        <div class="recipe-details">
            <h2>${recipe.name}</h2>
            <p>⏱ ${recipe.cookTime} mins</p>

            <h4>Ingredients</h4>
            <ul>${ingredientsList}</ul>

            <h4>Preparation</h4>
            <p>${recipe.steps}</p>
        </div>
    `;
}
