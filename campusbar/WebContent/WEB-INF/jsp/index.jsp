<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

			<section class="content">
					<br><h1 class="text-center">Alimenti</h1>
             <section class="container">
                  <div class="row">
								    <c:forEach items="${prodotti}" var="prodotto">
											<div class="col-lg-3 col-sm-6" >
												<div class="card">
	                          <div class="img-container">
	                              <img class="card-img-top" src="images/<c:out value="${prodotto.id}"/>.png" alt="Card image" >
	                          </div>
														<div class="card-body">
															    <h4 class="card-title"><c:out value="${prodotto.nome}" /></h4>
		                              <p class="card-text"><c:out value="${prodotto.descrizione}" /></p>
		                              <div class="card-info">
		                                  <span class="card-price"><c:out value="${prodotto.prezzo}" /></span>
		                                  <textarea id="nota<c:out value="${prodotto.id}"/>" maxlength="255"  placeholder="Inserisci una nota per questo prodotto"></textarea>
		                              </div>
		                              <input type="number" min="1" required class="card-quant" id="quant<c:out value="${prodotto.id}"/>" placeholder="Qt.">
		                              <button type="button" class="btn" onclick="addToCart(<c:out value="${prodotto.id}"/>)">Aggiungi al carrello</button>
		                          </div>
	                        </div>
												</div>
										</c:forEach>
										
                   </div><!-- chiude il div row -->
               </section>
        </section>
<jsp:include page="footer.html"/>
<script>
	function addToCart(id){
		
		var quant=document.getElementById("quant"+id).value;
		var not=document.getElementById("nota"+id).value;
		
		document.getElementById("quant"+id).value="";
		document.getElementById("nota"+id).value="";
		
		if(!quant){
			alert("inserisci quantita'");
			return;
		}

		$.get("AddToCart?id=" + id + "&quant=" + quant + "&nota=" + not, 
			function(data){
					alert(data);
		});
	
	}
	
	
</script>
</body>
</html>