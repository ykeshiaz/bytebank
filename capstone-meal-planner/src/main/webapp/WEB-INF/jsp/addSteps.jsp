<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="card mb-3">

    <c:choose>
        <c:when test="${recipe.steps == null}">
            <h3 class="card-header">Add Steps</h3>
        </c:when>
        <c:otherwise>
            <h3 class="card-header">Edit Steps</h3>
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
        <c:url var="formAction" value="/addSteps" />
        <form method="POST" action="${formAction}">
            <input type="hidden" name="destination" value="${param.destination}"/>
            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
            <div class="form-group">
            <label for="steps">Steps: </label>

            <c:choose>
                <c:when test="${recipe.steps == null}">
                <textarea class = "form-control" id="steps" name="steps" placeholder="1. Step 1&#10;2. Step 2&#10;3.Step 3..." class="form-control" required = "required"></textarea>
                </div>
                <div class="card-footer text-muted" id = "add-footer">
                    <button type="submit" class="btn btn-meal">Add Steps</button>
                </div>
                </c:when>
                <c:otherwise>
                    <textarea id="steps" name="steps" placeHolder="Steps" class="form-control" required = "required">${recipe.steps}</textarea>
                </div>
                <div class="card-footer text-muted" id = "add-footer">
                    <button type="submit" class="btn btn-meal">Save</button>
                </div>
                </c:otherwise>
            </c:choose>


        </form>
    </div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
