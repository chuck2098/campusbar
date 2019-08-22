<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"> 
        <title>CampusBar-Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="container-fluid ">
                <div class="row text-center">
                        <div class="col-md-3">
                                <a class="navbar-brand" href="#">
                                        <img src="images/logo.png" style="width: 150px" alt="Logo">
                                    </a>
                        </div>
                        <div class="col-md-6 head-search-sign">
                            <!--mt=margin top    mr=margin-right-->
                                <form class="form-inline mt-2" action="/action_page.php">
                                    <input class="form-control  mr-2" type="text" placeholder="Cerca un prodotto">
                                    <button class="btn" type="submit">Search</button>
                                </form>
                        </div>
                        <div class="col-md-3 head-search-sign" >
                                <table class="head-search-sign">
                                    <tr>
                                    <c:choose>
                                    	<c:when test="${logUtente!=null}">
                                    		 <td align="left"><span class="head-sign"><i class="fas fa-user">&nbsp;&nbsp;<a href="Logout">Logout</a></i></span></td>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<td align="left"><span class="head-sign"><i class="fas fa-user">&nbsp;&nbsp;<a href="sign.html">Accedi/Registrati</a></i></span></td>
                                    	</c:otherwise>
                                    </c:choose>
                                        <td align="right"><span class="head-cart"><i class="fas fa-shopping-cart">&nbsp;&nbsp;<a href="Carrello">Carrello</a></i></span></td>
                                    </tr>
                                </table>

                        </div>
                </div>

            </nav>
                <nav class="navbar navbar-expand-md ">
                        <a class="navbar-brand" href="#"></a>

                        <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                                <i class="fas fa-bars"></i>
                         </button>

                        <div class="collapse navbar-collapse" id="collapsibleNavbar">
                            <ul class="navbar-nav">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Categorie</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Miei Ordini</a>
                            </li>    
                            </ul>
            
                        </div>
                    </nav>

        </header>