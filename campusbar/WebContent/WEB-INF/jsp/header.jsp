<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>CampusBar-<c:out value="${param.title}"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link rel="shortcut icon" href="images/favicon.ico" />
	
</head>
<body>
	<header>
		<nav class="container-fluid ">
			<div class="row text-center">
				<div class="logo">
					<a class="navbar-brand" href="#"> <img src="images/logo.png"
						style="width: 200px" alt="Logo">
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
							<c:set value="Cerca un bar, un prodotto o una categoria" var="placeholder" scope="page"/>
							<c:set value="admin" var="admin" scope="page"/>
						</c:when>
				</c:choose>
				<div class="form-search">
					<!--mt=margin top    mr=margin-right-->
					<form class="form-inline <c:out value="${admin}"/> mt-2" action="<c:out value="${action}"/>" name="formRicerca" method="get">
						<input class="form-control <c:out value="${admin}"/>" placeholder="<c:out value="${placeholder}"/>" name="ricerca"><br>
							<c:choose>
								<c:when test="${admin!=null}">
									<div style="margin:auto; margin-left:auto; ">
										<button class="btn" type="submit" onclick="ricercaBar()">Bar</button>&nbsp;&nbsp;
										<button class="btn" type="submit" onclick="ricercaProdotto()" >Prodotto</button>&nbsp;&nbsp;
										<button class="btn" type="submit" onclick="ricercaCategoria()">Categoria</button>
									</div>
								</c:when>
								<c:otherwise>
									<button class="btn" type="submit">Cerca</button>&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
						<c:if test="${logUtente.getRuolo().getId_ruolo()==3}">
							<h5 style=' background-color:#96a398; margin:auto; margin-top:12px; border:2px solid #96a398;'>Bar di default:<b><c:out value="${logUtente.getEdificio().getNome()}"/></b></h5>
						</c:if>
					</form>
				</div>

				<div class="head-span">
					<c:choose>
						<c:when test="${logUtente!=null}">
							<c:if test="${logUtente.getRuolo().getId_ruolo()==2 || logUtente.getRuolo().getId_ruolo()==1}">
								<span class="head-sign admin">
							</c:if>
							<c:if test="${logUtente.getRuolo().getId_ruolo()!=2}">
								<span class="head-sign">
							</c:if>
								<i class="fas fa-user">&nbsp;&nbsp;
							<a href="Logout"><c:out value="Ciao ${logUtente.nome},"></c:out>Logout</a></i></span>
						</c:when>
						<c:otherwise>
							<span class="head-sign"><i class="fas fa-user">&nbsp;&nbsp;<a
									href="login.html">Accedi/Registrati</a></i></span>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${logUtente.getRuolo().getId_ruolo()==3 || logUtente==null}">
							<span class="head-cart"><i class="fas fa-shopping-cart">&nbsp;&nbsp;
							<c:if test="${cart!=null}">
								<c:set value="${cart.size()}" var="num_elem" scope="page"/>
							</c:if>
							<a href="Carrello">Carrello(<c:out value="${num_elem }"/>)</a></i></span>
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

					
					<c:choose>
						<c:when test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==2}">
							
							<!-- pulsante ordini -->
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
							
						<!-- pulsante totali per prodotto(bar) -->
							<c:choose>
								<c:when test="${param.active_menu eq 'totali_per_prodotto'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="VisualizzaTotaliBar">Totali</a>
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
					<!-- pulsante cambia password(cliente) -->
					<c:choose>
						<c:when
							test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==3}">
							<c:choose>
								<c:when test="${param.active_menu eq 'pass'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="VisualizzaUpdatePassword">Cambio Password</a>
							</li>
						</c:when>
					</c:choose>
					<!-- pulsante elimina account(cliente) -->
					<c:choose>
						<c:when
							test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==3}">
							<c:choose>
								<c:when test="${param.active_menu eq 'delAccount'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" onclick="if(confirm('Sei sicuro di voler eliminare?')) eliminaAccount()">Elimina Account</a>
							</li>
						</c:when>
					</c:choose>
					
				<!-- pulsanti per gestione (admin) -->
					<c:choose>
						<c:when test="${logUtente!=null && logUtente.getRuolo().getId_ruolo()==1}">
							<!-- pulsante gestion bar -->
							<c:choose>
								<c:when test="${param.active_menu eq 'gestione_bar'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="GestioneBar">Gestisci Bar</a>
							</li>
							
							<!-- pulsante gestione utenti -->
							<c:choose>
								<c:when test="${param.active_menu eq 'gestione_prod'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="GestioneProdottiAdmin">Gestisci Prodotti</a>
							</li>
							
							<!-- pulsante gestione categorie -->
							<c:choose>
								<c:when test="${param.active_menu eq 'gestione_categorie'}">
									<li class="nav-item active">
								</c:when>
								<c:otherwise>
									<li class="nav-item">
								</c:otherwise>
							</c:choose>
							<a class="nav-link" href="GestioneCategorieAdmin">Gestisci Categorie</a>
							</li>
						</c:when>
					</c:choose>
				</ul>

			</div>
		</nav>

	</header>
	<script>
	
	function eliminaAccount(){
			location.href="EliminaAccount";
	}
	
	
	function ricercaBar(){
		 
			document.formRicerca.method = "get";
			document.formRicerca.action =  "SearchAdminBar";
			document.formRicerca.submit();
		}
	function ricercaProdotto(){
		//if(document.formRicerca.ricerca.value.trim()=="") return;
		document.formRicerca.method = "get";
		document.formRicerca.action =  "SearchAdminProduct";
		document.formRicerca.submit();
		
	}function ricercaCategoria(){
		 
		document.formRicerca.method = "get";
		document.formRicerca.action =  "SearchAdminCategories";
		document.formRicerca.submit();
	}
	</script>
	
</body>