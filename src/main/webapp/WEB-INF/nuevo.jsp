<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Curso</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Nuevo Curso</h1>
		<form:form action="/nuevo" method="post" modelAttribute="curso">
			<div> 
				<form:label path="nombreCurso">Nombre</form:label>
				<form:input path="nombreCurso" class="form-control" placeholder="Nombre"/>
				<form:errors path="nombreCurso" class="text-danger"></form:errors>
			</div>
			<div> 
				<form:label path="instructor">Instructor</form:label>
				<form:input path="instructor" class="form-control" placeholder="Instructor"/>
				<form:errors path="instructor" class="text-danger"></form:errors>
			</div>
			<div> 
				<form:label path="capacidad">Capacidad</form:label>
				<form:input path="capacidad" class="form-control" />
				<form:errors path="capacidad" class="text-danger"></form:errors>
			</div>
			<form:hidden path="curso" value="${userInSession.id}"/>
			<input type="submit" class="btn btn-success mt-3" value="Crear"/>
		</form:form>
	</div>
</body>
</html>