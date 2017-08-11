<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row" style="margin-top: 15px;">
		<a class="btn btn-primary" href="<c:url value='/mvc/employes'/>">Employés</a>
		<a class="btn btn-primary" href="<c:url value='/mvc/bulletins'/>">Bulletins</a>
	</div>
	<div class="row" style="text-align: center;">
		<h1 style="font-weight: bold;">${titre}</h1>
	</div>
</div>