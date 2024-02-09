<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="title" value="Byte Bank: Recipes"></c:param>
</c:import>

<html>
<head>

    <meta charset="utf-8">
    <title>Sidebar</title>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="path/to/bootstrap/css/bootstrap.min.css">


</head>
<body>
<ul class="sidebar">
    <c:url var = "recipeUrl" value="/recipes"/>
    <a href="${recipeUrl}"><li class="sidebar-brand">${currentUser.userName}'s Recipes</li></a>
    <c:forEach var="recipe" items = "${recipes}">
        <c:url var="recipeDetailsUrl" value="/recipes/details">
            <c:param name="id" value="${recipe.recipeId}"/>
        </c:url>
        <a href="${recipeDetailsUrl}"><li>${recipe.name}
            <c:url var="deleteRecipeUrl" value="/recipes/delete">
                <c:param name="id" value="${recipe.recipeId}"/>
            </c:url>
            <form action="${deleteRecipeUrl}" method="POST">
                <input type="hidden" name="destination" value="${param.destination}"/>
                <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                <button type="submit" class="close delete-recipe" aria-label="Close">
                    &times;
                </button>
            </form>
        </li></a>

    </c:forEach>
    <c:url var = "addRecipeUrl" value="/addRecipe"/>
    <a class="nav-link" href="${addRecipeUrl}"><li>Add Recipe</li></a>
</ul>
</body>
</html>



<div class="main-content">
    <c:choose>
        <c:when test="${recipes.size()==0}">

            <div class="jumbotron">
                <h1 class="display-3">No current Recipes</h1>
                <p class="lead">
                    <c:url var = "addRecipeUrl" value="/addRecipe"/>
                    <a class="btn btn-primary btn-lg" href="${addRecipeUrl}"" role="button">Add a recipe!</a>
                </p>
            </div>
        </c:when>

        <c:when test="${currentRecipe == null}">

            <header>
                <div class="content">
                </div>
                <div class="overlay"></div>
            </header>
            <section class="site">

                <h1 class="text-recipe">
                    Select Recipe To Get Started!
                </h1>
                <p class="lead">
                        <c:url var = "addRecipeUrl" value="/addRecipe"/>
                            <a class="btn btn-primary btn-lg" href="${addRecipeUrl}"" role="button">Add a recipe!</a>
                </p>
            </section>

    </c:when>


        <c:otherwise>

        <div class="navigation">${currentRecipe.type}</div>

        <c:choose>
        <c:when test="${currentRecipe.imagePath!=null}">
            <c:url value="${currentRecipe.imagePath}" var="imageUrl" />
            <div class="pie-image"><img src="${imageUrl}"> </div>
        </c:when>
        <c:otherwise>
            <div class="pie-image"></div>
        </c:otherwise>
        </c:choose>

        <div class="pie-recipe">
            <div class="pie-recipe__title">
                <div class="pie-name">
                        ${currentRecipe.name}</div>

            </div>
            <div class="pie-recipe__ingredients">

                <div class="pie-recipe__ingredients__item">
                    <h4>Ingredients</h4>
                    <ul>
                        <c:forEach var = "ingredient" items="${currentRecipe.ingredients}">
                            <li>${ingredient.quantity} ${ingredient.name}</li>

                        </c:forEach>

                    </ul>

                </div>

            </div>
            <div class="pie-recipe__subtitle">Instructions</div>
            <div class="pie-recipe__number"></div>
            <div class="pie-recipe__steps" style="white-space: pre-line; word-break: break-all; word-break: break-word;">${currentRecipe.steps} </div>
            <c:url var = "groceryListUrl" value="/groceryList">
                <c:param name="id" value="${currentRecipe.recipeId}"/>
            </c:url>

            <div class="pie-recipe__tips">
                <c:url var="editRecipeUrl" value="/recipes/modify">
                    <c:param name="id" value="${currentRecipe.recipeId}"/>
                </c:url>
            <a type="button" class="btn btn-primary" href = "${editRecipeUrl}">Edit Recipe</a>
            <a type="button" class="btn btn-primary" href = "${groceryListUrl}">Get Grocery List</a>
            </div>
            <div class="btn_wrap" id="moveShare">
                <span id = "shareButton">Share</span>
                <div class="shareContainer">
                    <c:url var = "facebookUrl" value="https://www.facebook.com/sharer/sharer.php?u=http://localhost:8080/capstone_war_exploded/recipes"/>
                    <c:url var = "twitterUrl" value="https://twitter.com/home?status=http://localhost:8080/capstone_war_exploded/recipes "/>
                    <c:url var = "instagramUrl" value=""/>
                    <c:url var = "emailUrl" value="mailto:info@example.com?&subject=&body=http://localhost:8080/capstone_war_exploded/recipes "/>

                    <a href ="${facebookUrl}" target="_blank"> <i class="fab fa-facebook-f" ></i> </a>
                    <a href ="${twitterUrl}" target="_blank"><i class="fab fa-twitter"></i> </a>
                    <a href ="${instagramUrlUrl}" target="_blank"><i class="fab fa-instagram"></i> </a>
                    <a href ="${emailUrl}" target="_blank"><i class="fas fa-paper-plane"></i> <a/>
                </div>
            </div>

        </c:otherwise>
    </c:choose>


        <c:url var="iconUrl" value="/img/bytebank_01.png" />
        <link rel="shortcut icon" href="${iconUrl}" type="image/png" >

</div>




<c:import url="/WEB-INF/jsp/footer.jsp">
</c:import>
