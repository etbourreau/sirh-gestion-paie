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
	</head>
	<body class="container">
		<c:import url="header.jsp"></c:import>
		<c:if test="${param.error != null}">
			<div class="row alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				Nom d'utilisateur ou mot de passe invalide !
			</div>
		</c:if>
		<form class="row form-horizontal" method="post">
			<sec:csrfInput/>
			<div class="row form-group">
				<label class="col-md-2 control-label" for="username">Utilisateur</label>
				<div class="col-md-9">
					<input id="username" name="username" type="text"
						placeholder="Nom d'utilisateur" class="form-control input-md"
						required>
				</div>
			</div>
			
			<div class="row form-group">
				<label class="col-md-2 control-label" for="password">Mot de passe</label>
				<div class="col-md-9">
					<input id="password" name="password" type="password"
						placeholder="Mot de passe" class="form-control input-md"
						required>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-10 col-xs-offset-1" style="text-align: right;">
					<input type="submit" class="btn btn-success" value="Se connecter">
				</div>
			</div>
		</form>
	</body>
</html>