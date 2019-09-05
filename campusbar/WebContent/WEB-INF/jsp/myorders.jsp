<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp">
	<jsp:param value="myorders" name="active_menu" />
	<jsp:param value="Lista Ordini" name="title"/>
</jsp:include>

<div class="container" id="orders">
	<h2 style="text-align: center;">Lista Ordini</h2>
	<br>
	<div class="row">

		<div class='table-responsive'
			style='border: 2px solid #2A80B9; border-radius: 5px; overflow-x: auto; white-space: nowrap; text-align: center; width: 105%;'>
			<table style='width: 100%;'>
				<thead>
					<tr>
						<th style='color: #4C4C4C;'>Ordine</th>
						<th style='color: #4C4C4C;'>Consegnato</th>
						<th style='color: #4C4C4C;'>Data Ordine</th>
						<th style='color: #4C4C4C;'>Nota Ordine</th>
						<th style='color: #4C4C4C;'>Bar</th>
						<th style='color: #4C4C4C;'>Dettagli</th>
						<th style='color: #4C4C4C;'>Elimina</th>
					</tr>
				</thead>
				<tbody id='Ordini'>
					<c:forEach items="${ordini}" var="ordine">
						<tr style="font-weight: bold;">
							<td><c:out value="${ordine.getId_ordine()}" /></td>
							<c:choose>
								<c:when test="${ordine.getConsegnato()==true}">
									<td>si</td>
								</c:when>
								<c:otherwise>
									<td>no</td>
								</c:otherwise>
							</c:choose>

							<td><c:out value="${ordine.getData_ordine()}" /></td>
							<td><textarea maxlength='255' rows='2' readonly='readonly'
									style='width: 150px; resize: none;'><c:out
										value="${ordine.getNota_ordine()}" /></textarea></td>
							<td><c:out value="${ordine.getEdificio().getNome()}" /></td>
							<td><a href=#
								onclick="OpenDetails(<c:out value="${ordine.getId_ordine()}"/>)">Dettagli</a></td>
							<c:choose>
								<c:when test="${ordine.getConsegnato()==true}">
									<td>-</td>
								</c:when>
								<c:otherwise>
									<td><a href=#
										onclick="DelOrder(<c:out value="${ordine.getId_ordine()}"/>)">Elimina</a></td>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<c:forEach items="${ordini}" var="ordine">
		<div class="detailsOrder"
			id="details<c:out value="${ordine.getId_ordine()}"/>">

			<h2 style="text-align: center;">
				Dettaglio Ordine
				<c:out value="${ordine.getId_ordine()}" />
			</h2>
			<br>
			<div class='table-responsive'
				style='border: 2px solid #f1f8f8; border-radius: 5px; overflow-x: auto; white-space: nowrap; text-align: center;'>
				<table style='width: 100%;'>
					<thead>
						<tr>
							<th style='color: #f1f8f8; width: auto;'>Cod.Prodotto</th>
							<th style='color: #f1f8f8; width: auto;'>Prodotto</th>
							<th style='color: #f1f8f8; width: auto;'>Prezzo</th>
							<th style='color: #f1f8f8; width: auto;'>Qnt. Ordinata</th>
							<th style='color: #f1f8f8; width: auto;'>Note</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ordine.getDettaglio()}" var="dettaglio">
							<tr style="font-weight: bold;">
								<td><c:out value="${dettaglio.getProdotto().getId()}" /></td>
								<td><img width='100px'
									src='images/<c:out value="${dettaglio.getProdotto().getId()}"/>.png'><br>
									<c:out value="${dettaglio.getProdotto().getNome()}" /></td>
								<td><c:out value="${dettaglio.getPrezzo_acquisto()}" /></td>
								<td><c:out value="${dettaglio.getQuantita()}" /></td>
								<td><textarea maxlength='255' rows='2' readonly='readonly'
										style='width: 150px; resize: none;'><c:out
											value="${dettaglio.getNota()}" /></textarea></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<br>
			<h4>
				<button id="submitOrder" style="border: 3px solid #f1f8ee;"
					onclick="CloseDetails(<c:out value="${ordine.getId_ordine()}"/>)">chiudi</button>
			</h4>
		</div>
	</c:forEach>
</div>
<jsp:include page="footer.html" />
<script>

	var t=setInterval(updateOrders, 5000);
 function OpenDetails(ord){
	 clearInterval(t);
	 $("#details"+ord).fadeToggle();
	 $("#details"+ord).visible();
		//document.getElementById("chooseBars").style.display = "block";
 }
 function CloseDetails(ord){
	 $("#details"+ord).fadeToggle();
		//document.getElementById("chooseBars").style.display = "block";
 }
 function DelOrder(ord){
	 $.get("DeleteOrder?id=" +ord, 
				function(data){
						alert(data);
						$('#orders').load(document.URL +  ' #orders');
			});
 }

 
</script>
</body>
</html>