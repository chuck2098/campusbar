<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_prod" name="active_menu"/>
</jsp:include>
<style>
.card-title {
	height: 35px;
	font-size: 22px;
}

h5 {
	float: right;
	width: 24px;
	position: relative;
	right: 5px;
	border: 2px solid #eee;
	cursor: pointer;
}

table-responsive {
	overflow-x: auto;
	text-align: center;
	width: 100%;
	margin-top: 2px;
}

#nome{
width:30%; 
 display:inline;
 float:left; 
 height:40px;'
}
#descrizione{
width:67%; 
display:inline; 
float:right;
height:40px;
}
#prezzo{
	display:inline;
	float:left; 
	width:70px; 
	height:50px; 
	padding-left:2px; 
	margin-top:2%;
}
#cat_attuale{
font-size:19px; 
font-weight:400;
}
#categoria_prodotto{
width:200px; 
height:40px;
}
#nome_prod{
	width:90%; 
	height:40px;
}
#descrizione_prod{
	width:90%; 
	height:40px;
}
#prezzo_prod{
	width:92px; 
	height:50px; 
	margin-bottom:20px; 
	margin-top:15px; 
	display:inline; 
	float:left;
}
#categoria_prodotto_insert{
	width:53%; 
	height:40px;
}
</style>
        <div class="container" id="prodotti">
				<h2 style="text-align:center; cursor:pointer;" onclick="location.href='GestioneProdottiAdmin'">Gestione Prodotti</h2><br><br>
				<c:if test="${pattern!=null}">
					<h4 class="text-center">Risultati per la ricerca '<c:out value="${pattern}"/>' </h4><br>
				</c:if>

				<!-- se non c'e nessuna cate selezionata,le carico,cate non e' null poiche la servlet me le passa-->
				<c:choose>					
					<c:when test="${categorie!=null}">
					<!-- stiamo nella pagina principale -->
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
					<!-- stiamo nella categoria -->
						<c:if test="${prodotti!=null}">
							<h3 style="text-align:center;">Categoria Alimento: <c:out value="${prodotti.get(0).getCategoria().getNomeCategoria() }"></c:out></h3><br>
						</c:if>
					</c:otherwise>
				</c:choose>
				<div class="row">
					<c:forEach items="${prodotti}" var="prodotto">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/<c:out value="${prodotto.getId()}"/>.png"
										alt="Card image" onerror="src='images/logo.png'">
								</div>
								<div class="card-body">
									<h4 class="card-title" > <c:out value="${prodotto.getNome()}" /> </h4>
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
					<div class="col-lg-3 col-sm-6" style="text-align:center;">
						<a style="cursor:pointer; margin:auto;"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="Inserisci nuovo prodotto"></a>
					</div>
					
				<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewproduct">
						<h5 onclick="chiudi_modifiche()"><a style="text-decoration:none;color:#eee;">x</a></h5><br>
						<br>
						<div class='table-responsive'>
							<input type=text id='nome' class="form-control">
							<input type=text id='descrizione' class="form-control"><br><br><br>
							<input type=number id='prezzo' min=0 step=0.01 placeholder="Prezzo" class="form-control"><br>
							<span id="cat_attuale"></span><br><br><!-- categoria attuale la setto sempre quando mi carico il dettasglio prodotto -->
							<c:if test="${categorie!=null }"> <!-- se non sto nella categoria specifica,le carico tutte -->
								Cambia categoria:
								<select id="categoria_prodotto" class="browser-default custom-select">
									<option></option>
									<c:forEach items="${categorie}" var="categoria">
									 	<option  value="<c:out value="${categoria.getId_categoria()}"/>"> <c:out value="${categoria.getNomeCategoria()}"/> </option>
									</c:forEach>
								</select>
							</c:if>
							<br><br>
							<!-- form per upload dell'immagine -->
							<form id="uploadForm" enctype='multipart/form-data'>
	                <input type="file" name="file" id="sampleFile" form="uploadForm"/>
	                <input type="hidden" id="filename">
	                <input type="button" onclick="uploadImg()" value="Carica" />
	            </form>
						</div><br>
						<div id='btn_modifica' style=''></div>
					</div>
					
					<!-- DIV NASCOSTO PER L'INSERIMENTO -->
					<div class="chooseBar" id="insertproduct" >
					 <h5 onclick="chiudi_inserimento()"><a style="text-decoration:none;color:#eee;">x</a></h5><br>
						<div class='table-responsive'>
							<input type=text id='nome_prod' placeholder="Nome" class="form-control"><br><br>
							<input type=text id='descrizione_prod' placeholder="Descrizione" class="form-control"><br>
							<input type=number id='prezzo_prod' min=0 step=0.01 placeholder="Prezzo" class="form-control">
							<h6>Categoria</h6>
							<select id="categoria_prodotto_insert" class="browser-default custom-select">
								<c:choose>
									<c:when test="${categorie!=null }">
										<option></option>
										<c:forEach items="${categorie}" var="categoria">
										 	<option  value="<c:out value="${categoria.getId_categoria()}"/>"> <c:out value="${categoria.getNomeCategoria()}"/> </option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option selected="selected" value="<c:out value="${prodotti.get(0).getCategoria().getId_categoria()}"/>"> <c:out value="${prodotti.get(0).getCategoria().getNomeCategoria()}"/> </option>	
									</c:otherwise>
								</c:choose>
							</select>
						</div><br>
						<button id='submitOrder' style='width:auto; margin:auto; margin-left:15px;' onclick='inserisci()'>Inserisci</button>
					</div>
					
				</div>
			</div>
<jsp:include page="footer.html"/>
<script>

	function reloadPage(){
		resp="";
		jQuery.ajaxSetup({async:false});
		
		$.get(document.URL,
				function(data){
					resp=data;
				});
		jQuery.ajaxSetup({async:true});
		return resp;
	}

	//ritorna i prodotti della categoria selezionata
	function uploadProdotti(){
		var cat=$('#categorie').find(":selected").val();
		window.location.href="GestioneProdottiAdmin?id=" + cat;	
	}
	
	function eliminaProdotto(cod){
		 $.get("DeleteProdotto?id=" + cod, 
					function(data){
							//$('#prodotti').load(document.URL +  ' #prodotti');
						$('body').html(reloadPage());
				});
	}
	
	function apri_modifiche(cod){
		
		 $.get("VisualizzaProdotto?id=" + cod, 
				function(data){
					var res=data["prodotto"];
					uploadProdotto(res);
					$("#viewproduct").fadeIn();		
				});
	}
	
	//funzione che carica il dettaglio del prodotto nel div della modifica
	function uploadProdotto(disponib){
		
		for(i=0;i<disponib.length;i++){
			//mi serve per salvare il filename della foto da caricare(id.png)
			$("#filename").val(disponib[i]["id"]+".png");
			$("#nome").val(disponib[i]["nome"]);
			$("#descrizione").val(disponib[i]["descrizione"]);
			$("#prezzo").val(disponib[i]["prezzo"]);
			$("#cat_attuale").text("Categoria attuale:"+disponib[i]["categoria"]["nomeCategoria"]);
			$("#cat_attuale").val(disponib[i]["categoria"]["id_categoria"]);
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id"]+")'>Aggiorna</button>");
		}
	}
	
	//funzione che chiama la servlet di modifica
	function update(cod){
		
		if($("#nome").val().trim().length==0 || $("#descrizione").val().trim()==0 || $("#prezzo").val().trim()==0 ){
			alert("Compila tutti i campi");
			return;
		}	
		var cat=$('#categoria_prodotto').find(":selected").val();
		
		if(cat===undefined || cat.trim().length==0) cat=$("#cat_attuale").val().trim();
		
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
					$('body').html(reloadPage());
				});
	}
	
	function uploadImg(){
	       
		var filen=$("#filename").val();
	  var sampleFile = document.getElementById("sampleFile").files[0];

		var formdata = new FormData();
		
		formdata.append("filename", filen);
		formdata.append("sampleFile", sampleFile);
		
		var xhr = new XMLHttpRequest();       
		
		xhr.open("POST","Upload", true);
		
		xhr.send(formdata);
		
		xhr.onload = function(e) {
		    if (this.status == 200) {
		       alert(this.responseText);
		    }
		};                    
	}
	
	function chiudi_modifiche(){
		$("#viewproduct").fadeOut();	
	}
	
	function apri_inserimento(){
		$("#insertproduct").fadeIn();
	}
	
	function inserisci(){
		
		if($("#nome_prod").val().trim().length==0 || $("#descrizione_prod").val().trim()==0 || $("#prezzo_prod").val().trim()==0 ){
			alert("Compila tutti i campi");							
			return;
		}	
		
		var cat=$('#categoria_prodotto_insert').find(":selected").val();
		
		if(cat.trim().length==0) cat=$("#cat_attuale").val().trim();
		
		if(cat.length==0){
			alert("inserisci categoria");
			return;
		}
		
		$.post("InsertProduct",{
			nome:$("#nome_prod").val(),
			descr:$("#descrizione_prod").val(),
			prezzo:$("#prezzo_prod").val(),
			cate:cat
		},
		function(data){
			alert(data);
			chiudi_inserimento();
			window.location.href="GestioneProdottiAdmin?id="+cat
		});
		
	}
	
	function chiudi_inserimento(){
		$("#insertproduct").fadeOut();
	}
</script>
</body>
</html>