<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>

        <div class="container" id="orders">
				<h2 style="text-align:center;">Gestione quantità prodotti </h2><br>
					<div class="row">
						<div class='table-responsive' style='overflow-x: auto; text-align: center;'>
							<table>
								<thead>
										<tr>
											<th style='color: #4C4C4C;'>Prodotto</th>
											<th style='color: #4C4C4C;'>Quantità</th>
											<th style='color: #4C4C4C;'>-</th>
										</tr>
									</thead>
								<tbody>
								 <c:forEach items="${prodotti}" var="prodotto">
								 		<tr style="font-weight: bold;">
								 			<td style="width:32%;"><c:out value="${prodotto.getNome()}"/></td>
									 		<td><input type=number step="0.01" style="width:70px; height:40px; padding-right:2px; border: 3px solid #f1f8f8;" value=<c:out value="${prodotto.getPrezzo()}"/>></td>
									 		<td><button id="submitOrder" style="width:auto;" onclick="updateProduct(<c:out value="${ordine.getId_ordine()}"/>)">Modifica</button></td>
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