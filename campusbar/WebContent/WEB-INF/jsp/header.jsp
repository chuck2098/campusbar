<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CampusBar-Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="shortcut icon" href="images/favicon.ico" />
</head>
<body>
	<header>
		<nav class="container-fluid ">
			<div class="row text-center">
				<div class="logo">
					<a class="navbar-brand" href="#"> <img src="images/logo.png"
						style="width: 150px" alt="Logo">
					</a>
				</div>
				<c:choose>
					<c:when test="${logUtente.getRuolo().getId_ruolo()==3 || logUtente==null}">
						<c:set value="Search" var="action" scope="page"/>
						<c:set value="Cerca un prodotto" var="placeholder" scope="page"/>
					</c:when>
				</c:choose>
				<c:choose>
						<c:when test="${logUtente.getRuolo().getId_ruolo()==2 }">
							<c:set value="SearchBar" var="action" scope="page"/>
							<c:set value="Cerca id ordine" var="placeholder" scope="page"/>
						</c:when>
				</c:choose>
				<c:choose>
						<c:when test="${logUtente.getRuolo().getId_ruolo()==1 }">
							<c:set value="SearchAdmin" var="action" scope="page"/>
							<c:set value="Cerca prodotto o id ordine" var="placeholder" scope="page"/>
						</c:when>
				</c:choose>
				<div class="form-search">
					<!--mt=margin top    mr=margin-right-->
					<form class="form-inline mt-2" action="<c:out value="${action}"/>" name="formRicerca" method="get">
						<input class="form-control  mr-2" type="text" placeholder="<c:out value="${placeholder}"/>" name="ricerca">
						<button class="btn" type="submit">Search</button>
					</form>
				</div>

				<div class="head-span">
					<c:choose>
						<c:when test="${logUtente!=null}">
							<span class="head-sign"><i class="fas fa-user">&nbsp;&nbsp;<a
									href="Logout"><c:out value="Ciao ${logUtente.nome},"></c:out>Logout</a></i></span>
						</c:when>
						<c:otherwise>
							<span class="head-sign"><i class="fas fa-user">&nbsp;&nbsp;<a
									href="login.html">Accedi/Registrati</a></i></span>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${logUtente.getRuolo().getId_ruolo()==3 || logUtente==null}">
							<span class="head-cart"><i class="fas fa-shopping-cart">&nbsp;&nbsp;<a
									href="Carrello">Carrello</a></i></span>
						</c:when>
					</c:choose>
				</div>

			</div>

		</nav>
		<nav class="navbar navbar-expand-md ">
			<a class="navbar-brand" href="#"></a>

			<button class="navbar-toggler " type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<i class="fas fa-bars"></i>
			</button>

			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">

					<!-- pulsante home -->
					<c:choose>
						<c:when test="${param.active_menu eq 'home'}">
							<li class="nav-item active">
						</c:when>
						<c:otherwise>
							<li class="nav-item">
						</c:otherwise>
					</c:choose>
					<a class="nav-link" href=".">Home</a>
					</li>

					<!-- pulsante categorie -->
					<c:choose>
						<c:when test="${param.active_menu eq 'categorie'}">
							<li class="nav-item active">
						</c:when>
						<c:otherwise>
							<li class="nav-item">
						</c:otherwise>
					</c:choose>
					<a class="nav-link" href="Categorie">Categorie</a>
					</li>

					<!-- pulsante ordini -->
					<c:choose>
						<c:when
							test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==2}">

							<c:choose>
								<c:when test="${param.active_menu eq 'lista_ordini'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="IndexBar">Ordini</a>
							</li>

							<!-- pulsante gestione quantita(bar) -->
							<c:choose>
								<c:when test="${param.active_menu eq 'gestione_quantita'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="GestioneQuantita">Gestione Quantità</a>
							</li>
						</c:when>
					</c:choose>

					<!-- pulsante miei ordini(cliente) -->
					<c:choose>
						<c:when
							test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==3}">
							<c:choose>
								<c:when test="${param.active_menu eq 'myorders'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="MyOrders">Miei Ordini</a>
							</li>
						</c:when>
					</c:choose>
				</ul>

			</div>
		</nav>

	</header>
</body>