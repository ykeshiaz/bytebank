<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="title" value="Byte Bank"></c:param>
</c:import>


<h1>Grocery Lists</h1>

<div class="list-group">
    <a href="#" class="list-group-item list-group-item-action active">
        My Grocery List
    </a>
    <c:choose>
        <c:when test="${groceries.size()==0}">
            <c:url var = "recipeUrl" value="/recipes"/>
            <a href="${recipeUrl}" class="list-group-item list-group-item-action">By Recipe
            </a>
            <c:url var = "mealPlanUrl" value="/mealPlan"/>
            <a href="${mealPlanUrl}" class="list-group-item list-group-item-action">By Meal Plan
            </a>
        </c:when>
        <c:otherwise>
            <c:forEach var = "ingredient" items="${groceries}">
                <p class="list-group-item">${ingredient.quantity} ${ingredient.name}</p>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp">
</c:import>