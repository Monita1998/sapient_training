<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bug Tracking System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Bugs</h3>
			<hr>
			
			<br>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Email</th>
						<th>Owner</th>
						<th>BugType</th>
						<th>Priority</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="bug" items="${listbug}">

						<tr>
							<td><c:out value="${bug.id}" /></td>
							<td><c:out value="${bug.name}" /></td>
							<td><c:out value="${bug.description}" /></td>
							<td><c:out value="${bug.email}" /></td>
							<td><c:out value="${bug.owner}" /></td>
							<td><c:out value="${bug.bugtype}" /></td>
							<td><c:out value="${bug.priority}" /></td>
							<td><c:out value="${bug.status}" /></td>
							<td><a href="editbug?id=<c:out value='${bug.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <%-- <a
								href="deletebug?id=<c:out value='${bug.id}' />">Delete</a></td>--%>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
