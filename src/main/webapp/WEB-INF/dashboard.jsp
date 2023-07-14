<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<nav class="d-flex justify-content-between align-items-center">
			<h1>Bienvenid@ ${usuarioEnSesion.nombre}</h1> 
			<a href="/logout" class="btn btn-danger">Cerrar Sesion</a>
		</nav>
		<div class="row">
			<a href="#">Low Sign Up</a>
			<a href="#">High Sign Up</a>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Cursos</th>
						<th>Instructor</th>
						<th>Registros</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cursos}" var="curso">
						<tr>
							<td><a href="/mostrar/${curso.id}">${curso.nombreCurso}</a></td>
							<td>${curso.instructor}</td>
							<td>${curso.usuarios.size()}/${curso.capacidad}</td>
							<td>
								<a href="/agregar/${curso.id}">Agregar</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table> 
		</div>
		<a href="/nuevo" class="btn btn-success">Agregar Curso</a>
	</div>
</body>
</html>