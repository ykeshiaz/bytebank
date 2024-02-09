<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="title" value="Byte Bank"></c:param>
</c:import>



<div class="jumbotron">
    <%--<img src="img/bytebank_03.png" class="img-fluid">--%>
    <%--<img src="img/bytebank_02.png" class="img-fluid">--%>
    <p align="center"><img src="img/bytebank_02.png" class="img-fluid"></p>

    <c:if test="${currentUser!=null}">
        <h1 class="display-3" id = "userGreeting" align="center">WELCOME, ${currentUser.userName}!</h1>
    </c:if>

    <hr class="my-4">
    <p class="lead" align="center">Thank you for choosing Byte Bank as your premium meal planner!</p>

    <c:choose>
        <c:when test="${currentUser!=null}">
            <p align="center" class="lead">
                <c:choose>
                    <c:when test="${recipes.size()==0}">
                        <c:url var = "addRecipeUrl" value="/addRecipe"/>
                        <a class="btn btn-primary btn-lg" style="padding:20px; font-size: 15px;" href="${addRecipeUrl}" role="button"><img src="img/recipes.png" style="width: 19px; margin-right: 10px; margin-bottom:4px">Click to add a recipe!</a>
                    </c:when>
                    <c:otherwise>
                        <c:url var = "recipeUrl" value="/recipes"/>
                        <a class="btn btn-primary btn-lg" style="padding:20px; font-size: 15px;" href="${recipeUrl}" role="button"><img src="img/recipes.png" style="width: 19px; margin-right: 10px; margin-bottom:4px">View Recipes</a>

                        <c:url var = "addMealPlanUrl" value="/mealPlan"/>
                        <a class="btn btn-primary btn-lg" style="padding:20px; font-size: 15px;" href="${addMealPlanUrl}" role="button"><img src="img/mealplan.png" style="width: 19px; margin-right: 10px; margin-bottom:4px">View Meal Plans</a>

                        <c:url var = "groceryUrl" value="/groceryList"/>
                        <a class="btn btn-primary btn-lg" style="padding:20px; font-size: 15px;" href="${groceryUrl}" role="button"><img src="img/grocery.png" style="width: 26px; margin-right: 10px; margin-bottom:0px; margin-top:-3px">View Grocery List</a>
                    </c:otherwise>
                </c:choose>

            </p>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

</div>
<p>Meal planning is hard. You have to come up with what to make based on what is in your pantry, make it to the store, and then keep track of all of the different ways to prepare the food. This meal planner application allows users to create their own library of recipes and plan meals for the week using existing recipes (and ingredients). </p>

<c:if test="${currentUser==null}">
    <p>Start planning your first meal now!</p>
</c:if>

<c:if test="${currentUser!=null}">
    <p>Start planning your meal now!</p>
</c:if>

<c:if test="${currentUser==null}">
    <c:url var = "signUpUrl" value="/users/new"/>
    <a class="btn btn-primary my-2 my-sm-0" href="${signUpUrl}" role="button">Sign Up</a>
</c:if>
<c:import url="/WEB-INF/jsp/footer.jsp">
</c:import>