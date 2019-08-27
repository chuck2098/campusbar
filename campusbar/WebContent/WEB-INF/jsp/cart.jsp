<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
 <%!
	float totale;
%>
        <div class="container" id="cart">
        	<h2 style="text-align:center;">Carrello</h2><br>
            <div class="row">
             
							<div class='table-responsive' style='border:2px solid #2A80B9; border-radius:5px;overflow-x: auto;white-space: nowrap; text-align:center;'>
							     <table>
								     	<thead>
								         <tr>
								         <th style='color:#4C4C4C;'>Categoria</th>
								         <th style='color:#4C4C4C;'>Prodotto</th>
								         <th style='color:#4C4C4C;'>Prezzo</th>
								         <th style='color:#4C4C4C;'>Quantità Ordinata</th>
								         <th style='color:#4C4C4C;'>Note Prodotto</th>
								         <th style='color:#4C4C4C;'>Elimina</th>
								         </tr>
								      </thead>
								      <tbody>
								      	<c:set var="tot" value="0" scope="page"/>
								      	   <c:forEach items="${cart}" var="carrello">
								      	   <c:set var="t" value="${carrello.getPrezzo_acquisto()}" scope="page"/>
								      	   <c:set var="tot" value="${(tot + t) *carrello.getQuantita()}" scope="page"/>
								      	   	<tr>
									      	   	<td><c:out value="${carrello.getProdotto().getCategoria().getNomeCategoria()}"></c:out></td>
									      	   	<td><img width='100px' src='images/<c:out value="${carrello.getProdotto().getId()}"/>.png'><br><b><c:out value="${carrello.getProdotto().getNome()}"></c:out></b></td>
									      	   	<td><c:out value="${carrello.getPrezzo_acquisto()}"></c:out></td>
									      	   	<td><c:out value="${carrello.getQuantita()}"></c:out></td>
									      	   	<td><c:out value="${carrello.getNota()}"></c:out></td>
									      	   	<td><a href=# onclick="del(<c:out value="${carrello.getId_dettaglio()}"/>)" >elimina</a></td>
									      	   </tr>
								      	  </c:forEach> 
								      </tbody>
							     </table>
							</div>
						</div>
					<br><br>
					<div id="confirmOrder">
						<c:choose>
             	<c:when test="${cart!=null}">
								<span>Totale da pagare: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${tot}"/> €</span><br><br>
		            		<textarea name='notet' id='messaget' maxlength='255' placeholder='Aggiungi nota per questo ordine.'></textarea><br><br>
		            		<button id="submitOrder" onclick="confirm()"> Conferma Ordine</button>
	            	</c:when>
	            </c:choose>
            	
					</div>
					</div>


<jsp:include page="footer.html"/>
<script>

	function del(cod){
		$.get("DelToCart?id=" + cod,
				function(data){
						alert(data);
						$('#cart').load(document.URL +  ' #cart');
			});
	}
	
	function confirm(){
		
		var not=$.trim($("#messaget").val());
		
		$.get("ConfirmCart?not="+not,
				function(data){
						alert(data);
						$('#cart').load(document.URL +  ' #cart');
			});
	}
</script>
</body>
</html>