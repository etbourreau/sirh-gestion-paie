<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - Paie</title>
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
	
	<style>
		body{
			background: black;
			margin: 0;
			padding: 0;
			-webkit-touch-callout: none; /* iOS Safari */
	    	-webkit-user-select: none; /* Safari */
	     	-khtml-user-select: none; /* Konqueror HTML */
	       	-moz-user-select: none; /* Firefox */
	        -ms-user-select: none; /* Internet Explorer/Edge */
            user-select: none;
		}
		
		.page{
			text-align: center;
			width: 100%;
			height: 100%;
		}
		
		a.btn{
			position: absolute;
			top: 10px;
			left: 10px;
		}
		
		a.btn:focus{
			outline: 0;
		}
		
		.img-responsive{
			display: inline;
			height: 100%;
		}
	</style>
</head>
<body oncontextmenu="return false" onkeydown="return false">
	<div class="page">
		<a href="../" class="btn btn-danger">Back</a>
		<img class="img-responsive" src="<c:url value='/res/1.gif'/>"/>
	</div>
</body>
</html>