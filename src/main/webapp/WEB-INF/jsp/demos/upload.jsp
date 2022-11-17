<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<c:import url="/WEB-INF/jsp/common/header.jsp" />--%>

<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <c:url var="formAction" value="/upload" />

        <form method="POST" action="${formAction}" enctype="multipart/form-data">

            <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

            <div class="form-group">
                <label for="file">File: </label>
                <input type="file" name="file" id="file" />
            </div>

            <button type="submit" class="btn btn-primary">upload</button>

        </form>
    </div>
    <div class="col-sm-4"></div>
</div>

<%--<c:import url="/WEB-INF/jsp/common/footer.jsp" />--%>