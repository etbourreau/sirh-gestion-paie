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
				<a class="btn btn-info pull-right" href="<c:url value='/mvc/bulletins/creer'/>">Ajouter un bulletin</a>
			</div>
			
			<div class="row" style="margin-top: 15px;">
				<table class="table table-bordered table-hover">
					<thead>
						<tr style="background: #f5f5f5;">
							<td>Date/Heure création</td>
							<td>Période</td>
							<td>Matricule</td>
							<td>Salaire brut</td>
							<td>Net imposable</td>
							<td>Net à payer</td>
							<td>Actions</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bulletin" items="${listeBulletins}">
							<tr>
								<td>${bulletin.key.dateHeureCreation}</td>
								<td>${bulletin.key.periode.dateDebut} - ${bulletin.key.periode.dateFin}</td>
								<td>${bulletin.key.remunerationEmploye.matricule}</td>
								<td>${bulletin.value.salaireBrut}</td>
								<td>${bulletin.value.netImposable}</td>
								<td>${bulletin.value.netAPayer}</td>
								<td><a class="btn btn-info" href="<c:url value='/mvc/bulletins/visualiser/${bulletin.key.id}'/>">Visualiser</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>