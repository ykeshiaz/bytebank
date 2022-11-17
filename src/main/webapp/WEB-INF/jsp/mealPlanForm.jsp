<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Button trigger modal -->
<button type="button" class="btn btn-outline-meal" data-toggle="modal" data-target="#modalform-${param.meal}-${param.dayVal}">
    Add ${param.meal}
</button>
<!-- Modal -->
<div class="modal fade show" id="modalform-${param.meal}-${param.dayVal}" tabindex="-1" role="dialog" aria-labelledby="modalform-${param.meal}-${dayVal}"
     aria-hidden="true">
    <!-- Add .modal-dialog-centered to .modal-dialog to vertically center the modal -->
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header" id = "modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    &times;
                </button>
                <h5 class="modal-title">${param.dayName}, ${param.date}</h5>
                <h6>${param.meal}</h6>

            </div>
            <div class="modal-body">
                <c:choose>
                    <c:when test="${recipes.size()==0}">
                        <h6>No Recipes in Library</h6>
                        <c:url var = "addRecipeUrl" value="/addRecipe"/>
                        <a class="btn btn-primary btn-lg" href="${addRecipeUrl}" role="button"> Click to add a recipe!</a>
                    </c:when>
                    <c:otherwise>
                        <div class="md-form mb-5">
                            <!-- add logic to display form depending if user has recipes in library -->
                            <c:url var = "mealForm" value="/addMealPlan">
                                <c:param name="type" value="${param.meal}"/>
                                <c:param name="id" value="${param.mealPlanId}"/>
                            </c:url>
                            <form method="POST" action="${mealForm}">
                                <input type="hidden" name="destination" value="${param.destination}"/>
                                <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                                <div class="form-group">
                                    <label for = "selectRecipe-${param.meal}-${param.dayVal}">Choose a Recipe</label>
                                    <select name="selectRecipe" id = "selectRecipe-${param.meal}-${param.dayVal}" class="form-control validate">
                                        <option>--</option>
                                        <c:forEach var="recipe" items = "${recipes}">
                                            <option value="${recipe.recipeId}">${recipe.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-meal">Add to Meal Plan</button>
                                </div>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</div>