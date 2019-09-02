<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_prod" name="active_menu"/>
</jsp:include>

        <div class="container" id="prodotti">
				<h2 style="text-align:center;" onclick="reload()">Gestione Prodotti</h2><br><br>
				<c:choose>					
					<c:when test="${categorie!=null}">
						<h3 style="text-align:center;">Categoria Alimento:
							<select id="categorie" onchange="uploadProdotti()" style="width:200px; height:40px;" class="browser-default custom-select">
								<option></option>
								<c:forEach items="${categorie}" var="categoria">
								 	<option  value="<c:out value="${categoria.getId_categoria()}"/>"> <c:out value="${categoria.getNomeCategoria()}"/> </option>
								</c:forEach>
							</select>
						</h3><br>
					</c:when>
					<c:otherwise>
						<h3 style="text-align:center;">Categoria Alimento: <c:out value="${prodotti.get(0).getCategoria().getNomeCategoria() }"></c:out></h3><br>
					</c:otherwise>
				</c:choose>
				<div class="row">
					<c:forEach items="${prodotti}" var="prodotto">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/<c:out value="${prodotto.getId()}"/>.png"
										alt="Card image">
								</div>
								<div class="card-body">
									<h4 class="card-title" style="height:35px; font-size:22px;" > <c:out value="${prodotto.getNome()}" /> </h4>
			          		<div style="overflow-y: auto; height:35px; margin-bottom:10px;"><p class="card-text"><c:out value="${prodotto.descrizione}" /></p></div>
                       <div class="card-info">
                           <span class="card-price"><c:out value="${prodotto.prezzo}" /></span>
                    	</div>
									<button type="button" class="btn" style="float:left; width:auto; padding-left:13px; padding-right:13px;" onclick="if(confirm('Sei sicuro di voler eliinare?')) eliminaProdotto(<c:out value="${prodotto.getId()}"/>)">Elimina</button>
									<button type="button" class="btn" style="float:right; width:auto; padding-left:13px; padding-right:13px;" onclick="apri_modifiche(<c:out value="${prodotto.getId()}"/>)">Modifica</button>
								</div>
							</div>
						</div>
					</c:forEach>
					
				<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewproduct" style="padding:5px; height:auto; top:15%;">
						<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_modifiche()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome' style='width:90%; height:40px;'><br><br>
							<input type=text id='descrizione' style='width:90%; height:40px;'><br><br>
							Prezzo<input type=number id='prezzo' style='width:70px; height:50px; padding-left:2px;'><br><br>
							<span id="cat_attuale" style="font-size:19px;"></span><br>
							Cambia categoria:
							<select id="categoria_prodotto" style="width:200px; height:40px;" class="browser-default custom-select">
								<option></option>
								<c:forEach items="${categorie}" var="categoria">
								 	<option  value="<c:out value="${categoria.getId_categoria()}"/>"> <c:out value="${categoria.getNomeCategoria()}"/> </option>
								</c:forEach>
							</select>
						</div><br>
						<div id='btn_modifica' style=''></div>
					</div>
					
					<!-- div nascosto per inserimento -->
					<div class="chooseBar" id="insertbar" style="padding:5px;">
					<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_inserimento()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome_bar' placeholder="Nome del bar" style='width:90%; height:40px;'>
							<br><br> 
							<span>Orario chiusura </span>
							<input type=number id='orario_bar' placeholder="00" style='width:70px; height:50px;'>
						</div><br>
						<button id='submitOrder' style='width:auto; margin:auto; margin-left:15px;' onclick='inserisci()'>Inserisci</button>
					</div>
					
				</div>
			</div>
<jsp:include page="footer.html"/>
<script>
//ritorna i prodotti dell acategoria selezionata
	function uploadProdotti(){
		var cat=$('#categorie').find(":selected").val();
		window.location.href="GestioneProdottiAdmin?id=" + cat;	
	}
	
	function eliminaProdotto(cod){
		 $.get("DeleteProdotto?id=" + cod, 
					function(data){
							alert(data);
							$('#prodotti').load(document.URL +  ' #prodotti');
				});
	}
	
	function apri_modifiche(cod){
		
		 $.get("VisualizzaProdotto?id=" + cod, 
				function(data){
					var res=data["prodotto"];
					uploadProdotto(res);
					$("#viewproduct").fadeIn();		
				});
	}unexpected
	
	function uploadProdotto(disponib){
		
		for(i=0;i<disponib.length;i++){
			$("#nome").val(disponib[i]["nome"]);
			$("#descrizione").val(disponib[i]["descrizione"]);
			$("#prezzo").val(disponib[i]["prezzo"]);
			$("#cat_attuale").text("Categoria attuale:"+disponib[i]["categoria"]["nomeCategoria"]);
			$("#cat_attuale").val(disponib[i]["categoria"]["id_categoria"]);
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id"]+")'>Aggiorna</button>");
		}
	}
	
	function update(cod){
		
		if($("#nome").val().trim().length==0 || $("#descrizione").val().trim()==0 || $("#prezzo").val().trim()==0 ){
			alert("Compila tutti i campi");
			return;
		}	
		var cat=$('#categoria_prodotto').find(":selected").val();
		
		if(cat.trim().length==0) cat=$("#cat_attuale").val();
			
				$.post("EditProduct",{
					id:cod,
					nome:$("#nome").val(),
					descr:$("#descrizione").val(),
					prezzo:$("#prezzo").val(),
					cat:cat,
				},
				function(data){
					alert(data);
					chiudi_modifiche();
					window.location.href="GestioneProdottiAdmin";
				});
	}
	
	function chiudi_modifiche(){
		$("#viewproduct").fadeOut();	
	}
	
	function reload(){
		location.href="GestioneProdottiAdmin";
	}
</script>
</body>
</html>