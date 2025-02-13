<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="lista_ordini" name="active_menu"/>
	<jsp:param value="Ordini" name="title"/>
</jsp:include>

    <div id="alertBox">
        
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" onClick='CloseAlert();'></p>
    </div>

        <div class="container" id="orders">
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
									 		<td><a href=# onclick="OpenDetails(<c:out value="${ordine.getId_ordine()}"/>)">Dettagli</a></td>
									 		<td><a href=# onclick="DelOrder(<c:out value="${ordine.getId_ordine()}"/>)">Elimina</a></td>
									 	</tr>
								 </c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<c:forEach items="${ordini}" var="ordine">
						<div class="detailsOrder" id="details<c:out value="${ordine.getId_ordine()}"/>" >
						<h5 style="float:right; width:24px; position:relative; right:5px; border:2px solid #eee; cursor:pointer;" onclick="CloseDetails(<c:out value="${ordine.getId_ordine()}"/>)"><a style="text-decoration:none;color:#eee;">x</a></h5><br><br>
							<h2 style="text-align:center;">Dettaglio Ordine <c:out value="${ordine.getId_ordine()}"/></h2><br>
							<div class='table-responsive' style='border: 2px solid #f1f8f8; border-radius: 5px; overflow: auto; white-space: nowrap; text-align: center; max-height:100%;'>
								<table style='width: 95%;'>
									<thead>
										<tr>
                      <th style='color:#f1f8f8; width:auto;'>Cod.Prodotto</th>
                      <th style='color:#f1f8f8; width:auto;'>Prodotto</th>
                      <th style='color:#f1f8f8; width:auto;'>Prezzo</th>
                      <th style='color:#f1f8f8; width:auto;'>Qnt. Ordinata</th>
                      <th style='color:#f1f8f8; width:auto;'>Note</th>
                     </tr>
										</thead>
									<tbody>
									<c:set var="tot" value="0" scope="page"/>
									 <c:forEach items="${ordine.getDettaglio()}" var="dettaglio">
					      	   <c:set var="t" value="${dettaglio.getPrezzo_acquisto()*dettaglio.getQuantita()}" scope="page"/>
					      	   <c:set var="tot" value="${(tot + t)}" scope="page"/>
									 		<tr style="font-weight: bold;">
									 			<td><c:out value="${dettaglio.getProdotto().getId()}"/></td>
										 		<td><img width='100px' src='images/<c:out value="${dettaglio.getProdotto().getId()}"/>.png'><br><c:out value="${dettaglio.getProdotto().getNome()}"/></td>
										 		<td><c:out value="${dettaglio.getPrezzo_acquisto()}"/></td>
											 	<td><c:out value="${dettaglio.getQuantita()}"/></td>
										 		<td><textarea maxlength='255' rows='2' readonly='readonly' style='width:150px;resize: none;'><c:out value="${dettaglio.getNota()}"/></textarea></td>
										 	</tr>
									 </c:forEach>
									</tbody>
								</table>
							</div><br>
							<span>Totale da pagare: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${tot}"/> €</span>
							<br><br>
							<h4><button id="submitOrder" style="border:3px solid #f1f8ee;" onclick="deliver(<c:out value="${ordine.getId_ordine()}"/>)">Consegna</button></h4>
						</div>
				</c:forEach> 
			</div>
<jsp:include page="footer.html"/>
<script>

	function CloseAlert(){
		$("#alertBox").hide();
		
	}
	
	function OpenAlert(){
		$("#alertBox").show(70);
	}


	var t;
	startTimer();
	
	function stopTimer(){
		clearTimeout(t);
	}
	function startTimer(){
		t=setInterval(updateOrders, 5000);
	}

 function DelOrder(ord){
	 $.get("DeleteOrder?id=" +ord, 
				function(data){
							 $("#msg").text(data);
							 OpenAlert();
							$('#orders').load(document.URL +  ' #orders');
			});
 }
 function updateOrders(){
	 $.get("IndexBar", 
				function(data){
					$('#orders').load(document.URL +  ' #orders');
			});
 }
 function OpenDetails(ord){
	 stopTimer();
	 $("#details"+ord).fadeToggle();
	 $("#details"+ord).visible();
		//document.getElementById("chooseBars").style.display = "block";
 }
 function CloseDetails(ord){
	 $("#details"+ord).fadeToggle();
	 startTimer();
		//document.getElementById("chooseBars").style.display = "block";
 }
 
 function deliver(ord){
	 
	 $.get("DeliveryOrder?id=" +ord, 
				function(data){
						 $("#msg").text(data);
						 
						CloseDetails(ord);
						OpenAlert();
						startTimer();
			}); 
 }
 
</script>
</body>
</html>