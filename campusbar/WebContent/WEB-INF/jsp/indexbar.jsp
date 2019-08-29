<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>

        <div class="container" id="cart">
				<h2 style="text-align:center;">Lista Ordini </h2><br>
					<div class="row">
				
						<div class='table-responsive' style='border: 2px solid #2A80B9; border-radius: 5px; overflow-x: auto; white-space: nowrap; text-align: center;'>
							<table style='width: 95%;'>
								<thead>
										<tr>
											<th style='color: #4C4C4C;'>Codice Ordine</th>
											<th style='color: #4C4C4C;'>Utente</th>
											<th style='color: #4C4C4C;'>Data Ordine</th>
											<th style='color: #4C4C4C;'>Nota Ordine</th>
											<th style='color: #4C4C4C;'>N° Prodotti</th>
											<th style='color: #4C4C4C;'>Dettagli ordine</th>
											<th style='color: #4C4C4C;'>Elimina Ordine</th>
										</tr>
									</thead>
								<tbody id='Ordini'>
								 <c:forEach items="${ordini}" var="ordine">
								 		<tr style="font-weight: bold;">
								 			<td><c:out value="${ordine.getId_ordine()}"/></td>
									 		<td><c:out value="${ordine.getDettaglio().get(0).getCliente().getMatricola()}"/></td>
									 		<td><c:out value="${ordine.getData_ordine()}"/></td>
									 		<td><textarea maxlength='255' rows='2' readonly='readonly' style='width:150px;resize: none;'><c:out value="${ordine.getNota_ordine()}"/></textarea></td>
									 		<td><c:out value="${ordine.getDettaglio().size()}"/></td>
									 		<td><a href=#>Dettagli</a></td>
									 		<td><a href=#>Elimina</a></td>

									 	</tr>
								 </c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			</div>
<jsp:include page="footer.html"/>
<script>

</script>
</body>
</html>