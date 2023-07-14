<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar Curso</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>${curso.nombreCurso}</h1>	
			<p>Instructor: ${curso.instructor}</p>
			<p>Registros: ${curso.capacidad}</p>
		</div>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Fecha Registro</th>
						<th>Accion</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${curso.usuarios}" var="c">
                        <tr>
                            <td>${c.nombre }</td>
                            <td>${c.createdAt}</td>
                            <td>
                                <a href="#">remover</a>
                            </td>
                        </tr>
                	</c:forEach>	
				</tbody>
			</table> 
		</div>
		<a href="/editar/${curso.id}" class="btn btn-success">Editar</a>
		<form action="/borrar/${curso.id}" method="post">
             <input type="hidden" name="_method" value="DELETE">
             <input type="submit" value="Borrar" class="btn btn-danger">
         </form>
	</div>
</body>
</html>