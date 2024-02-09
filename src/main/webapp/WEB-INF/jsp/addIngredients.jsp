<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="card mb-3">

    <c:choose>
    <c:when test="${recipe.steps == null}">
        <h3 class="card-header">Add Ingredients</h3>
    </c:when>
    <c:otherwise>
        <h3 class="card-header">Modify Ingredients</h3>
    </c:otherwise>
    </c:choose>

    <div class="card-body">
        <h5 class="card-title">${recipe.name}</h5>
        <h6 class="card-subtitle text-muted">${recipe.type}</h6>
    </div>
    <c:if test="${recipe.imagePath!=null}">
    <c:url value="${recipe.imagePath}" var="imageUrl" />
    <img src="${imageUrl}" alt="Card image" id = "card-image">
    </c:if>
    <ul class="list-group list-group-flush">
        <c:forEach var = "ingredient" items="${recipe.ingredients}" >
            <li class="list-group-item">${ingredient.quantity} ${ingredient.name}
                <c:url var="deleteIngredientUrl" value="/ingredients/delete">
                    <c:param name="recipe_id" value="${recipe.recipeId}"/>
                    <c:param name="ing_id" value="${ingredient.ingredientId}"/>
                </c:url>
                <form action="${deleteIngredientUrl}" method="POST">
                    <input type="hidden" name="destination" value="${param.destination}"/>
                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                    <button type="submit" class="close" aria-label="Close">
                        &times;
                    </button>
                </form>
            </li>
        </c:forEach>
    </ul>

    <div class="card-body">
        <c:url var="formAction" value="/addIngredients" />
        <form method="POST" action="${formAction}">
            <input type="hidden" name="destination" value="${param.destination}"/>
            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

            <div class="form-group">
                <label for="quantity">Quantity / Measurement Type: </label>
                <input type="text" id="quantity" name="quantity" placeHolder="Quantity" class="form-control" />
            </div>
            <div class="form-group">
                <label for="name">Ingredient Name: </label>
                <input type="text" id="name" name="name" placeHolder="Ingredients" class="form-control" required = "required"/>
            </div>
            <div class="card-footer text-muted" id = "add-footer">
            <button type="submit" class="btn btn-primary">Add Ingredient</button>
                <c:url value="/addSteps" var = "stepsUrl"/>
                <a href="${stepsUrl}" class="btn btn-meal">Add Steps</a>
            </div>
        </form>

    </div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
