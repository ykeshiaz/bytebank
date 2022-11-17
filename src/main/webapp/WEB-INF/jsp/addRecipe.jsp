<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="card mb-3">
<c:choose>
    <c:when test="${param.id == null}">
    <div class="card-header">
        <h1>Add Recipe</h1>
    </div>
    <div class="card-body">
        <c:url var="formAction" value="/upload" />
    </c:when>
    <c:otherwise>
    <div class="card-header">
        <h1>Modify Recipe</h1>
    </div>
    <div class="card-body">
        <c:url var="formAction" value="/recipes/modify">
            <c:param name="id" value="${recipe.recipeId}"/>
        </c:url>
    </c:otherwise>
</c:choose>



    <form method="POST" action="${formAction}" enctype="multipart/form-data">
            <input type="hidden" name="destination" value="${param.destination}"/>
            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
            <div class="form-group">
                <label for="name">Recipe Name </label>
                <input type="text" id="name" name="name" placeHolder="Recipe Name" required = "required" class="form-control" value="${recipe.name}"/>
            </div>

            <div class="form-group">
                <label for = "type">Type of Meals</label>
                <select name="type" id = "type" required = "required">
                    <option value="Breakfast"
                        <c:if test="${recipe.type eq 'Breakfast'}">selected= "selected"</c:if>
                    >Breakfast</option>
                    <option value="Lunch"
                        <c:if test="${recipe.type eq 'Lunch'}">selected= "selected"</c:if>
                    >Lunch</option>
                    <option value="Dinner"
                        <c:if test="${recipe.type eq 'Dinner'}">selected= "selected"</c:if>
                    >Dinner</option>
                    <option value="Snack"
                        <c:if test="${recipe.type eq 'Snack'}">selected= "selected"</c:if>
                    >Snack</option>
                    <option value="Dessert"
                        <c:if test="${recipe.type eq 'Dessert'}">selected= "selected"</c:if>
                    >Dessert</option>
                </select>

            </div>
            <div class="form-group">
                <label for="file">Image File: </label>
                <input type="file" name="file" id="file" />
            </div>
            <div class="card-footer text-muted" id = "add-footer">
                <c:choose>
                    <c:when test="${param.id == null}">
                        <button type="submit" class="btn btn-meal">Add Ingredient</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-meal">Edit Ingredients</button>
                    </c:otherwise>
                </c:choose>
            </div>


        </form>
    </div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />

