<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualiser bulletin</title>
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.css'/>">
	
	<style>
		table.table-bordered{
			border:0;
		}
		
		tr.header{
			background: #f5f5f5;
		}
		
		.table-bordered>tbody>tr.between{
			font-weight: bold;
		}
		
		.table-hover>tbody>tr.between:hover{
			background: white;
		}
		
		.table-bordered>tbody>tr.between>td{
			border: 0;
		}
	</style>
</head>
<body class="container">

	<c:import url="../header.jsp"></c:import>
	<h2>${test}</h2>
	<div class="container" style="margin-top: 15px;">
			<div class="row">
				<div class="col-sm-4 col-xs-6">
					<strong>Entreprise</strong><br>
					${bulletin.remunerationEmploye.entreprise.denomination}<br>
					SIRET : ${bulletin.remunerationEmploye.entreprise.siret}
				</div>
				<div class="col-md-3 col-md-offset-5 col-sm-4 col-sm-offset-4 col-xs-6">
					<strong>Période</strong><br>
					Du ${bulletin.periode.dateDebut} au ${bulletin.periode.dateDebut}<br>
					<br>
					<strong>Matricule</strong> ${bulletin.remunerationEmploye.matricule}
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">
					
					<table class="table table-bordered table-hover">
						<tbody>
							<tr class="between">
								<td>Salaire</td>
							</tr>
							
							<tr class="header">
								<th>Rubriques</th>
								<th>Base</th>
								<th>Taux salarial</th>
								<th>Montant salarial</th>
								<th>Taux patronal</th>
								<th>Cot. patronales</th>
							</tr>
							<tr>
								<td><strong>Salaire de base</strong></td>
								<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}h</td>
								<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
								<td>
									<fmt:formatNumber
										type="number"
										value="${bulletin.remunerationEmploye.grade.nbHeuresBase * bulletin.remunerationEmploye.grade.tauxBase * 12}"
										maxFractionDigits="0"
										pattern="#" />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><strong>Prime Except.</strong></td>
								<td></td>
								<td></td>
								<td>${bulletin.primeExceptionnelle}</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><strong>Salaire Brut</strong></td>
								<td></td>
								<td></td>
								<td>${resultCalculRemun.salaireBrut}</td>
								<td></td>
								<td></td>
							</tr>
							
							<tr class="between">
								<td>Cotisations</td>
							</tr>
							
							<tr class="header">
								<th>Rubriques</th>
								<th>Base</th>
								<th>Taux salarial</th>
								<th>Montant salarial</th>
								<th>Taux patronal</th>
								<th>Cot. patronales</th>
							</tr>
							<c:forEach
								items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}"
								var="cotisation">
								<tr>
									<td>${cotisation.libelle}</td>
									<td>${resultCalculRemun.salaireBrut}</td>
									<td>${cotisation.tauxSalarial}</td>
									<td>
										<c:if test="${cotisation.tauxSalarial != null}">
											<fmt:formatNumber
												type="number"
												value="${resultCalculRemun.salaireBrut * cotisation.tauxSalarial}"
												maxFractionDigits="0"
												pattern="#" />
										</c:if>
									</td>
									<td>${cotisation.tauxPatronal}</td>
									<td><c:if test="${cotisation.tauxPatronal != null}">
											<fmt:formatNumber type="number"
												value="${resultCalculRemun.salaireBrut * cotisation.tauxPatronal}"
												maxFractionDigits="0"
												pattern="#" />
										</c:if></td>
								</tr>
							</c:forEach>
							<tr>
								<td><strong>Total Retenue</strong></td>
								<td></td>
								<td></td>
								<td>
									<fmt:formatNumber
										type="number"
										value="${totalRetenueMontantSalarial}"
										maxFractionDigits="0"
										pattern="#" />
								</td>
								<td></td>
								<td>
									<fmt:formatNumber
										type="number"
										value="${totalRetenueCotisationsPatronales}"
										maxFractionDigits="0"
										pattern="#" />
								</td>
							</tr>
							
							<tr class="between">
								<td>
									NET Imposable: ${resultCalculRemun.netImposable} €
								</td>
							</tr>
					
							<tr class="header">
								<th>Rubriques</th>
								<th>Base</th>
								<th>Taux salarial</th>
								<th>Montant salarial</th>
								<th>Taux patronal</th>
								<th>Cot. patronales</th>
							</tr>
							<c:forEach
								items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}"
								var="cotisation">
								<tr>
									<td>${cotisation.libelle}</td>
									<td>${resultCalculRemun.salaireBrut}</td>
									<td>${cotisation.tauxSalarial}</td>
									<td>
										<c:if test="${cotisation.tauxSalarial != null}">
											<fmt:formatNumber
												type="number"
												value="${resultCalculRemun.salaireBrut * cotisation.tauxSalarial}"
												maxFractionDigits="0"
												pattern="#" />
										</c:if>
									</td>
									<td></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-5 col-sm-offset-7 col-xs-6 col-xs-offset-6">
					<strong>NET A PAYER :</strong> ${resultCalculRemun.netAPayer} €
				</div>
			</div>
		</div>
</body>
</html>