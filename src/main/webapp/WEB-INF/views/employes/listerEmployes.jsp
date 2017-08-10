<%@page import="dev.paie.entite.RemunerationEmploye"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - Paie</title>
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/js/bootstrap.min.js'/>">
</head>
<body>
		<c:import url="../header.jsp"></c:import>
		<div class="container">
			<div class="row">
				<a class="btn btn-info pull-right" href="employes/creer">Ajouter un employé</a>
			</div>
			
			<div class="row" style="margin-top: 15px;">
				<table class="table table-bordered table-hover">
					<thead>
						<tr style="background: #f5f5f5;">
							<td>Date/Heure création</td>
							<td>Matricule</td>
							<td>Grade</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employe" items="${listeEmployes}">
							<tr>
								<td>${employe.dateHeureCreation}</td>
								<td>${employe.matricule}</td>
								<td>${employe.grade.code}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>