<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - Paie</title>
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/js/bootstrap.min.js'/>">
<script src="<c:url value='/res/jQuery/jquery-3.2.1.js'/>">alert("jQuery indisponible!");</script>
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">
		<c:if test="${param.error != null}">
			<div class="row alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				Nom d'utilisateur ou mot de passe invalide !
			</div>
		</c:if>
		<form class="row form-horizontal" method="post">
			<sec:csrfInput/>
			<div class="row form-group">
				<label class="col-md-3 control-label" for="periode">Période</label>
				<div class="col-md-8">
					<select id="periode" name="periode" class="form-control">
						<c:forEach var="periode" items="${listePeriodes}">
							<option value="${periode.id}">${periode.dateDebut} - ${periode.dateFin}</option>
						</c:forEach>
						<c:if test="${listePeriodes.isEmpty()}">
							<option value="">Aucune période disponible...</option>
						</c:if>
					</select>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-3 control-label" for="employe">Matricule</label>
				<div class="col-md-8">
					<select id="employe" name="employe" class="form-control">
						<c:forEach var="employe" items="${listeEmployes}">
							<option value="${employe.id}">${employe.matricule}</option>
						</c:forEach>
						<c:if test="${listeEmployes.isEmpty()}">
							<option value="">Aucun matricule disponible...</option>
						</c:if>
					</select>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-3 control-label" for="prime">Prime exceptionnelle</label>
				<div class="col-md-8">
					<input id="prime" name="prime" type="number" step="0.01" min="0" value="0"
						placeholder="Prime exceptionnelle de l'employé" class="form-control input-md"
						required>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-10 col-xs-offset-1" style="text-align: right;">
					<button id="btnInit" class="btn btn-danger">Réinitialiser</button>
					<input type="submit" class="btn btn-success" value="Valider">
				</div>
			</div>
		</form>
	</div>

	<script>
		var periode;
		var employe;
		var prime;

		$(document).ready(function() {
			periode = $("#periode").val();
			employe = $("#employe").val();
			prime = $("#prime").val();
		});

		$("#btnInit").click(function() {
			$("#periode").val(periode);
			$("#employe").val(employe);
			$("#prime").val(prime);
			return false;
		});
	</script>
</body>
</html>