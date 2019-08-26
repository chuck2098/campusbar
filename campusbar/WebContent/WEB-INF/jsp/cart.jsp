<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

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
								      	   <c:forEach items="${cart}" var="carrello">
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
					</div>


<jsp:include page="footer.html"/>
<script>
	function del(cod){
		alert();
		$.get("DelToCart?id=" + cod,
				function(data){
						alert(data);
						$('#cart').load(document.URL +  ' #cart');
			});
	}
</script>
</body>
</html>