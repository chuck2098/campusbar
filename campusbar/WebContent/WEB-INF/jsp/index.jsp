<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param value="home" name="active_menu"/>
	<jsp:param value="Home" name="title"/>
</jsp:include>

   <div id="alertBox">
        
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" onClick='CloseAlert();'></p>
    </div>

			<section class="content">
		<!-- if per verificare il titolo da mettere  -->
		<c:choose>      
			 <c:when test="${categoria != null }">
			 		<br><h1 class="text-center"><c:out value="${categoria.getNomeCategoria()}"/></h1>
			 </c:when>
			 <c:otherwise>
				<c:choose>
					<c:when  test="${pattern != null }">
						<br><h1 class="text-center">Risultati per '<c:out value="${pattern}"/>'</h1>
					</c:when>
					<c:otherwise>
						<br><h1 class="text-center">Prodotti piu venduti</h1>
					</c:otherwise>
				</c:choose>
			 </c:otherwise>
		 </c:choose>

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
		                                <c:choose>
		                              		<c:when test="${logUtente.getRuolo().getId_ruolo()==3 || logUtente==null}">
		                                  	<textarea id="nota<c:out value="${prodotto.id}"/>" maxlength="255"  placeholder="Inserisci una nota per questo prodotto"></textarea>
				                              </c:when>
					                          </c:choose>
		                              </div>
		                              	<c:if test="${logUtente.getRuolo().getId_ruolo()==3 || logUtente==null}">
				                              <input type="number" min="1" required class="card-quant" id="quant<c:out value="${prodotto.id}"/>" placeholder="Qt.">
				                              <button type="button" class="btn" onclick ="addToCart(<c:out value="${prodotto.id}"/>)">Aggiungi al carrello</button>
			                             </c:if>
		                          </div>
	                        </div>
												</div>
										</c:forEach>
										
                   </div><!-- chiude il div row -->
               </section>
        </section>
<jsp:include page="footer.html"/>
<script> 

	function CloseAlert(){
		$("#alertBox").hide();
		$('body').html(reloadPage());
	}
	
	function OpenAlert(){
		$("#alertBox").show(70);
	}


	function reloadPage(){
		resp="";
		jQuery.ajaxSetup({async:false});
		
		$.get("Carrello");
		
		$.get(document.URL,
				function(data){
					resp=data;
				});
		jQuery.ajaxSetup({async:true});
		return resp;
	}
	
	function addToCart(id){
		
		var quant=document.getElementById("quant"+id).value;
		var not=document.getElementById("nota"+id).value;
		
		document.getElementById("quant"+id).value="";
		document.getElementById("nota"+id).value="";
		
		if(!quant){
			$("#msg").text("Inserisci Quantita'");
			OpenAlert();
			return;
		}

		$.get("AddToCart?id=" + id + "&quant=" + quant + "&nota=" + not, 
			function(data){
				$("#msg").text(data);
				OpenAlert();
		});
	
	}
	
	
</script>
</body>
</html>