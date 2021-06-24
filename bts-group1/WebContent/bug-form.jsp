<%@page import="com.sapient.model.Bugtype"%>
<%@page import="com.sapient.model.Status"%>
<%@page import="com.sapient.model.Priority"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bug Tracking System</title>
<style>
select {
  width: 100%;
  padding: 16px 20px;
  border: none;
  border-radius: 4px;
  background-color: #f1f1f1;
}
</style>
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
				<c:if test="${bug != null}">
					<form action="updatebug" method="post">
				</c:if>
				<c:if test="${bug == null}">
					<form action="insertbug" method="post">
				</c:if>
				<caption>
					
					<h2>
						<c:if test="${bug != null}">
            			Edit Bug
            		</c:if>
						<c:if test="${bug == null}">
            			Add Bug User
            		</c:if>
					</h2>
				</caption>
				<c:if test="${bug != null}">
					<input type="hidden" name="id" value="<c:out value='${bug.id}' />" />
				</c:if>
				<form name ="contactForm">
				<fieldset class="form-group">
					<label>Project </label> 
						<select name="project_id">
						<c:forEach var="project" items="${projectlist}">
							<option value='${project.id}' ${bug.project_id == project.id ? 'selected="selected"' :''}>${project.name}</option>
						</c:forEach>
					</select> 
					</fieldset>

					<fieldset class="form-group">
						<label>Name</label> <input type="text" 
						value="<c:out value='${bug.name}' />" class="form-control"
							name="name" id="name" required="required">
					</fieldset>
					 <fieldset class="form-group">
						<label>Email</label> <input type="email"  
						value="<c:out value='${bug.email}' />" class="form-control"
							name="email" id="email" required="required">
					</fieldset>
	
					<fieldset class="form-group">
						<label>Owner</label> <input type="text" 
						value="<c:out value='${bug.owner}' />" class="form-control"
							name="owner" >
					</fieldset>
	
					<fieldset class="form-group">
						<label>Description</label> <input type="text" 
						value="<c:out value='${bug.description}' />" class="form-control"
							name="description">
					</fieldset>
					
					
					<fieldset class="form-group">
					<label>Bug Type</label> 
						<select name="bugtype" value="<c:out value='${bug.bugtype}' />" >
							<option selected="selected" value="<%=Bugtype.story.name()%>"><%=Bugtype.story.name()%></option>
							<option value="<%=Bugtype.issue.name()%>"><%=Bugtype.issue.name()%></option>
							<option value="<%=Bugtype.defect.name()%>"><%=Bugtype.defect.name()%></option>
					</select> 
					</fieldset>
					
					<fieldset class="form-group">
						<label>Status</label>
						<select name="status" value="<c:out value='${bug.status}' />" >
							<option selected="selected" value="<%=Status.open.name()%>"><%=Status.open.name()%></option>
							<option value="<%=Status.ready.name()%>"><%=Status.ready.name()%></option>
							<option value="<%=Status.development.name()%>"><%=Status.development.name()%></option>
							<option value="<%=Status.closed.name()%>"><%=Status.closed.name()%></option>
					</select> 
					</fieldset>
					
					<fieldset class="form-group">
						<label>Priority</label> 
						<select name="priority" value="<c:out value='${bug.priority}' />" >
							<option selected="selected" value="<%=Priority.Low.name()%>"><%=Priority.Low.name()%></option>
							<option value="<%=Priority.Medium.name()%>"><%=Priority.Medium.name()%></option>
							<option value="<%=Priority.High.name()%>"><%=Priority.High.name()%></option>
							<option value="<%=Priority.Critical.name()%>"><%=Priority.Critical.name()%></option>
					</select>
					</fieldset>
					

					<button type="submit" class="btn btn-success">Submit</button>
			</form>
			</div>
		</div>
	</div>

</body>
</html>