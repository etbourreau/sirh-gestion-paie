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
				<label class="col-md-2 control-label" for="matricule">Matricule</label>
				<div class="col-md-9">
					<input id="matricule" name="matricule" type="text"
						placeholder="Matricule de l'employé" class="form-control input-md"
						required>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="entreprise">Entreprise</label>
				<div class="col-md-9">
					<select id="entreprise" name="entreprise" class="form-control">
						<c:forEach var="entreprise" items="${listeEntreprises}">
							<option value="${entreprise.id}">${entreprise.denomination}</option>
						</c:forEach>
						<c:if test="${listeEntreprises.isEmpty()}">
							<option value="">Aucune entreprise disponible...</option>
						</c:if>
					</select>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="profil">Profil</label>
				<div class="col-md-9">
					<select id="profil" name="profil" class="form-control">
						<c:forEach var="profil" items="${listeProfils}">
							<option value="${profil.id}">${profil.code}</option>
						</c:forEach>
						<c:if test="${listeProfils.isEmpty()}">
							<option value="">Aucun profil disponible...</option>
						</c:if>
					</select>
				</div>
			</div>

			<div class="row form-group">
				<label class="col-md-2 control-label" for="grade">Grade</label>
				<div class="col-md-9">
					<select id="grade" name="grade" class="form-control">
						<c:forEach var="grade" items="${listeGrades}">
							<option value="${grade.id}">${grade.code} - ${grade.prixAnnuel} € / an</option>
						</c:forEach>
						<c:if test="${listeGrades.isEmpty()}">
							<option value="">Aucun grade disponible...</option>
						</c:if>
					</select>
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
		var matricule;
		var entreprise;
		var profil;
		var grade;

		$(document).ready(function() {
			matricule = $("#matricule").val();
			entreprise = $("#entreprise").val();
			profil = $("#profil").val();
			grade = $("#grade").val();
		});

		$("#btnInit").click(function() {
			$("#matricule").val(matricule);
			$("#entreprise").val(entreprise);
			$("#profil").val(profil);
			$("#grade").val(grade);
			return false;
		});
	</script>
</body>
</html>