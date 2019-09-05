<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param value="home" name="active_menu"/>
	<jsp:param value="Home" name="title"/>
</jsp:include>

			<section class="content">
					<br><h1 class="text-center">Prodotti piu venduti</h1>

             <section class="container">
                  <div class="row">
								    <c:forEach items="${prodotti}" var="prodotto">
											<div class="col-lg-3 col-sm-6" >
												<div class="card">
	                          <div class="img-container">
	                              <img class="card-img-top" src="images/<c:out value="${prodotto.id}"/>.png" alt="Card image" onerror="src='images/logo.png'" >
	                          </div>
														<div class="card-body">
															<h4 class="card-title"><c:out value="${prodotto.nome}" /></h4>
		                              <div style="overflow-y: auto; height:35px; margin-bottom:10px;"><p class="card-text"><c:out value="${prodotto.descrizione}" /></p></div>
		                              <div class="card-info">
		                                  <span class="card-price"><c:out value="${prodotto.prezzo}" /></span>
		                              </div>
	                          </div>
	                        </div>
											</div>
										</c:forEach>
										
                   </div><!-- chiude il div row -->
               </section>
        </section>
<jsp:include page="footer.html"/>
<script>
	
</script>
</body>
</html>