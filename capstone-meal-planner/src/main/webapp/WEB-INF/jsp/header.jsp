<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Byte Bank</title>


    <c:url var="iconUrl" value="/img/bytebank_01.png"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">

    <link rel="icon" type="image/png" sizes="32x32" href="/img/bytebank_01.png">

    <c:url var="bootstrapCss" value="/css/bootstrap.css"/>
    <c:url var="siteCss" value="/css/site.css"/>

    <c:url var="jQueryJs" value="/js/jquery.min.js"/>
    <c:url var="jqValidateJs" value="/js/jquery.validate.min.js"/>
    <c:url var="jqvAddMethJs" value="/js/additional-methods.min.js"/>
    <c:url var="jqTimeagoJs" value="/js/jquery.timeago.js"/>
    <c:url var="popperJs" value="/js/popper.min.js"/>
    <c:url var="bootstrapJs" value="/js/bootstrap.min.js"/>
    <c:url var="customJs" value="/js/custom.js"/>

    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link rel="stylesheet" type="text/css" href="${siteCss}">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">


    <script src="${jQueryJs}"></script>
    <script src="${jqValidateJs}"></script>
    <script src="${jqvAddMethJs}"></script>
    <script src="${jqTimeagoJs}"></script>
    <script src="${popperJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${customJs}"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("time.timeago").timeago();

            $("#logoutLink").click(function (event) {
                $("#logoutForm").submit();
            });

            var pathname = window.location.pathname;
            $("nav a[href='" + pathname + "']").parent().addClass("active");

        });
    </script>

</head>
<body>


<nav style="z-index: 1; position:relative" class="navbar navbar-expand-lg navbar-dark bg-primary" id="navigation">

    <c:url var="home" value="/home"/>
    <a href="${home}">
        <c:url var="logoUrl" value="/img/bytebank_04.png"/>
        <img alt="logo" id="headerImage" src="${logoUrl}" class="img-fluid" style="margin-right: 28px; margin-bottom: 6px">
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
            aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">

            <c:if test="${currentUser!=null}">
                <li class="nav-item">
                    <c:url var="mealPlanUrl" value="/mealPlan"/>
                    <a class="nav-link" href="${mealPlanUrl}">Meal Plans</a>
                </li>

                <li class="nav-item">
                    <c:url var="recipeUrl" value="/recipes"/>
                    <a class="nav-link" href="${recipeUrl}">Recipes </a>
                </li>

                <%--				<li class="nav-item">--%>
                <%--					<c:url var = "addRecipeUrl" value="/addRecipe"/>--%>
                <%--					<a class="nav-link" href="${addRecipeUrl}"> Add Recipe</a>--%>
                <%--				</li>--%>


                <li class="nav-item">
                    <c:url var="groceryListUrl" value="/groceryList"/>
                    <a class="nav-link" href="${groceryListUrl}">Grocery List</a>
                </li>
            </c:if>

            <li class="nav-item">
                <c:url var="aboutUrl" value="/about"/>
                <a class="nav-link" href="${aboutUrl}">About Byte Bank</a>
            </li>


        </ul>

        <c:choose>
            <c:when test="${currentUser==null}">
                <c:url var="loginUrl" value="/login"/>
                <a class="btn btn-secondary my-2 my-sm-0" href="${loginUrl}" role="button">Login</a>
                <c:url var="signUpUrl" value="/users/new"/>
                <a class="btn btn-secondary my-2 my-sm-0" href="${signUpUrl}" role="button">Sign Up</a>
            </c:when>
            <c:otherwise>

                <p id="userGreeting">Hello, ${currentUser.userName}! </p>
                <c:url var="logoutUrl" value="/logout"/>
                <form method="post" action="${logoutUrl}">
                    <input type="hidden" name="destination" value="${param.destination}"/>
                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                    <button type="submit" class="btn btn-secondary my-2 my-sm-0">Log Out</button>

                </form>
            </c:otherwise>
        </c:choose>

    </div>
</nav>


<div class="container">