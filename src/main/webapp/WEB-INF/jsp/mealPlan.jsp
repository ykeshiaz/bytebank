<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="title" value="Byte Bank: Meal Planner"></c:param>
</c:import>


<h1>Meal Plan</h1>

<form method="GET">
    <label for="date">Date:</label>
    <input type="date" id="date" name="date">
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<table id="mealPlanTable" class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Day Of The Week</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <th scope="col">${dayNames.get(dayVal)}<br><br><p>${weekDates.get(dayVal)}</p></th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
    <tr class="table-active">
        <th scope="row">BreakFast</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <td>
                <c:choose>
                    <c:when test="${mealPlans.get(dayVal).breakfastRecipe==null}">
                        <c:import url="/WEB-INF/jsp/mealPlanForm.jsp">
                            <c:param name="dayVal" value="${dayVal}"/>
                            <c:param name="date" value="${weekDates.get(dayVal)}"/>
                            <c:param name="dayName" value="${dayNames.get(dayVal)}"/>
                            <c:param name="meal" value="Breakfast"/>
                            <c:param name="mealPlanId" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:import>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/recipes/details" var="recipeUrl">
                            <c:param name="id" value="${mealPlans.get(dayVal).breakfastRecipeId}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="btn btn-meal">${mealPlans.get(dayVal).breakfastRecipe.name}</a>
                        <c:url value="/deleteMeal" var="deleteMeal">
                            <c:param name="type" value="Breakfast"/>
                            <c:param name="id" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:url>
                        <form action="${deleteMeal}" method="POST">
                            <input type="hidden" name="destination" value="${param.destination}"/>
                            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                            <button type="submit" class="close" aria-label="Close">
                                &times;
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>

    </tr>

    <tr class="table-active">
        <th scope="row">Lunch</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <td>
                <c:choose>
                    <c:when test="${mealPlans.get(dayVal).lunchRecipe==null}">
                        <c:import url="/WEB-INF/jsp/mealPlanForm.jsp">
                            <c:param name="dayVal" value="${dayVal}"/>
                            <c:param name="date" value="${weekDates.get(dayVal)}"/>
                            <c:param name="dayName" value="${dayNames.get(dayVal)}"/>
                            <c:param name="meal" value="Lunch"/>
                            <c:param name="mealPlanId" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:import>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/recipes/details" var="recipeUrl">
                            <c:param name="id" value="${mealPlans.get(dayVal).lunchRecipeId}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="btn btn-meal">${mealPlans.get(dayVal).lunchRecipe.name}</a>
                        <c:url value="/deleteMeal" var="deleteMeal">
                            <c:param name="type" value="Lunch"/>
                            <c:param name="id" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:url>
                        <form action="${deleteMeal}" method="POST">
                            <input type="hidden" name="destination" value="${param.destination}"/>
                            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                            <button type="submit" class="close" aria-label="Close">
                            &times;
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>
    </tr>

    <tr class="table-active">
        <th scope="row">Dinner</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <td>
                <c:choose>
                    <c:when test="${mealPlans.get(dayVal).dinnerRecipe==null}">
                        <c:import url="/WEB-INF/jsp/mealPlanForm.jsp">
                            <c:param name="dayVal" value="${dayVal}"/>
                            <c:param name="date" value="${weekDates.get(dayVal)}"/>
                            <c:param name="dayName" value="${dayNames.get(dayVal)}"/>
                            <c:param name="meal" value="Dinner"/>
                            <c:param name="mealPlanId" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:import>

                    </c:when>
                    <c:otherwise>
                        <c:url value="/recipes/details" var="recipeUrl">
                            <c:param name="id" value="${mealPlans.get(dayVal).dinnerRecipeId}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="btn btn-meal">${mealPlans.get(dayVal).dinnerRecipe.name}</a>
                        <c:url value="/deleteMeal" var="deleteMeal">
                            <c:param name="type" value="Dinner"/>
                            <c:param name="id" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:url>
                        <form action="${deleteMeal}" method="POST">
                            <input type="hidden" name="destination" value="${param.destination}"/>
                            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                            <button type="submit" class="close" aria-label="Close">
                                &times;
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>

    </tr>

    <tr class="table-active">
        <th scope="row">Snack</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <td>
                <c:choose>
                    <c:when test="${mealPlans.get(dayVal).snackRecipe==null}">

                        <c:import url="/WEB-INF/jsp/mealPlanForm.jsp">
                            <c:param name="dayVal" value="${dayVal}"/>
                            <c:param name="date" value="${weekDates.get(dayVal)}"/>
                            <c:param name="dayName" value="${dayNames.get(dayVal)}"/>
                            <c:param name="meal" value="Snack"/>
                            <c:param name="mealPlanId" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:import>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/recipes/details" var="recipeUrl">
                            <c:param name="id" value="${mealPlans.get(dayVal).snackRecipeId}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="btn btn-meal">${mealPlans.get(dayVal).snackRecipe.name}</a>
                        <c:url value="/deleteMeal" var="deleteMeal">
                            <c:param name="type" value="Snack"/>
                            <c:param name="id" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:url>
                        <form action="${deleteMeal}" method="POST">
                            <input type="hidden" name="destination" value="${param.destination}"/>
                            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                            <button type="submit" class="close" aria-label="Close">
                                &times;
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>

    </tr>

    <tr class="table-active">
        <th scope="row">Dessert</th>
        <c:forEach var = "dayVal" begin="0" end="6">
            <td>
                <c:choose>
                    <c:when test="${mealPlans.get(dayVal).dessertRecipe==null}">
                        <c:import url="/WEB-INF/jsp/mealPlanForm.jsp">
                            <c:param name="dayVal" value="${dayVal}"/>
                            <c:param name="date" value="${weekDates.get(dayVal)}"/>
                            <c:param name="dayName" value="${dayNames.get(dayVal)}"/>
                            <c:param name="meal" value="Dessert"/>
                            <c:param name="mealPlanId" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:import>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/recipes/details" var="recipeUrl">
                            <c:param name="id" value="${mealPlans.get(dayVal).dessertRecipeId}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="btn btn-meal">${mealPlans.get(dayVal).dessertRecipe.name}</a>
                        <c:url value="/deleteMeal" var="deleteMeal">
                            <c:param name="type" value="Dessert"/>
                            <c:param name="id" value="${mealPlans.get(dayVal).mealPlanId}"/>
                        </c:url>
                        <form action="${deleteMeal}" method="POST">
                            <input type="hidden" name="destination" value="${param.destination}"/>
                            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                            <button type="submit" class="close" aria-label="Close">
                                &times;
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td></td>
        <c:forEach var = "dayVal" begin="0" end="6">
            <c:url var = "groceryListUrl" value="/groceryList">
                <c:param name="mp_id" value="${mealPlans.get(dayVal).mealPlanId}"/>
            </c:url>
            <td><a type="button" class="btn btn-warning" href = "${groceryListUrl}">Get Grocery List</a></td>
        </c:forEach>
    </tr>


</table>

<c:import url="/WEB-INF/jsp/footer.jsp">
</c:import>