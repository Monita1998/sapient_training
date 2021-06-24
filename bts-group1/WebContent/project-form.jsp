<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>User Management Application</title>
<title>Bug Tracking System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${project != null}">
					<form action="updateproject" method="post">
				</c:if>
				<c:if test="${project == null}">
					<form action="insertproject" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${project != null}">
        			Edit Project
            		</c:if>
						<c:if test="${project == null}">
            			Add New Project
            		</c:if>
					</h2>
				</caption>

				<c:if test="${project != null}">
					<input type="hidden" name="id"
						value="<c:out value='${project.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${project.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${project.description}' />"
						class="form-control" name="description">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>

				
			</div>
		</div>
	</div>

</body>
</html>
