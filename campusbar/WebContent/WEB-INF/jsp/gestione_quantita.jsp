<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_quantita" name="active_menu"/>
	<jsp:param value="Gestione Quantita'" name="title"/>
</jsp:include>

        <div class="container" id="orders">
				<h2 style="text-align:center;">Gestione quantità prodotti </h2><br><br>
				<h3 style="text-align:center;">Categoria Alimento:
					<select id="categorie" onchange="uploadDisponibilita()" style="width:200px; height:40px;" class="browser-default custom-select">
						<option></option>
						<c:forEach items="${categorie}" var="categoria">
						 	<option  value="<c:out value="${categoria.getId_categoria()}"/>"> <c:out value="${categoria.getNomeCategoria()}"/> </option>
						</c:forEach>
					</select>
				</h3><br><br>
					<div class="row">
						<div class='table-responsive' id="disponibilitaCategoria" style='overflow-x: auto; text-align: center;'>
							<table>
								<thead>
										<tr>
											<th style='color: #4C4C4C;'>Prodotto</th>
											<th style='color: #4C4C4C;'>Quantità</th>
											<th style='color: #4C4C4C;'>Modifica Qnt.</th>
										</tr>
									</thead>
								<tbody id="giacenze"></tbody>
							</table>
						</div>
					</div>
			</div>
<jsp:include page="footer.html"/>
<script>
	function uploadDisponibilita(){
		
		var cat=$('#categorie').find(":selected").val();
		
		$.get("GestioneQuantita?id=" + cat, 
				function(data){
					var res=data["disponibilita"];
					uploadDisp(res);
			});	
	}
	
	function uploadDisp(disponib){
		
		var response="";
		for(i=0;i<disponib.length;i++){
			response+="<tr>";
			response+="<td>"+disponib[i]["prodotto"]["nome"]+"</td>";
			response+="<td>"+"<input type=number id='q"+disponib[i]["prodotto"]["id"]+"' style='width:70px; height:40px; padding-right:2px; border: 3px solid #f1f8f8;' value="+disponib[i]["quantita"]+"></td>";
			response+="<td>"+"<button id='submitOrder' style='width:auto; padding:12px;' onclick='updateQuantity("+disponib[i]["prodotto"]["id"]+")'>Aggiorna</button></td>";
			response+="</tr>";
		}
		$("#giacenze").html(response);
	}

 function updateQuantity(prod){
	 var q=$("#q"+prod).val();
	 $.get("UpdateQuantity?id=" + prod+"&q="+q, 
				function(data){
						alert(data);
			});
 }
</script>
</body>
</html>