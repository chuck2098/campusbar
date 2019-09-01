<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_bar" name="active_menu"/>
</jsp:include>

        <div class="container" id="bars">
				<h2 style="text-align:center;">Gestione Bar </h2><br><br>
				<div class="row">
					<c:forEach items="${edifici}" var="edificio">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/ed<c:out value="${edificio.getId_edificio()}"/>.png"
										alt="Card image">
								</div>
								<div class="card-body">
									<h4 class="card-title" style="height:60px; font-size:21px;" >
										<c:out value="${edificio.getNome()}" />
									</h4>
								<div class="card-info">
									<h5 style="font-weight:bold;">Chiude alle ore <c:out value="${edificio.getOrario_chiusura()}" /></h5>
								</div>
								<br>
						<button type="button" class="btn" style="float:left; width:auto; padding-left:13px; padding-right:13px;" onclick="if(confirm('Sei sicuro di voler eliinare?')) eliminaBar(<c:out value="${edificio.getId_edificio()}"/>)">Elimina</button>
									<button type="button" class="btn" style="float:right; width:auto; padding-left:13px; padding-right:13px;" onclick="apri_modifiche(<c:out value="${edificio.getId_edificio()}"/>)">Modifica</button>
								</div>
							</div>
						</div>
					</c:forEach>
					<a style="cursor:pointer;"  onclick="chiudi_modifiche()"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="Inserisci nuovo bar"></a>
					<div class="chooseBar" id="viewbar" style="padding:5px;">
					<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_modifiche()">Chiudi</a></h5><br>
						<div class='table-responsive' id="disponibilitaCategoria" style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome' style='width:90%; height:40px;'>
							<br><br> 
							<span>Orario chiusura </span>
							<input type=number id='orario' style='width:70px; height:50px;'>
						</div><br><br>
						<div id='btn_modifica' style='margin-top:-25px; margin-left:10px;'></div>
					</div>
					
					<div class="chooseBar" id="insertbar" style="padding:5px;">
						<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_modifiche()">Chiudi</a></h5><br>
							<div class='table-responsive' id="disponibilitaCategoria" style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
								<input type=text id='nome' placeholder="Nome bar" style='width:90%; height:40px;'>
								<input type=text id='orario' placeholder="Orario chiusura" style='width:70px; height:50px;'>
								"<button id='submitOrder' style='width:auto; margin:auto;' onclick='inserisciBar()'>Inserisci</button>"
							</div>
					</div>
				</div>
</div>
<jsp:include page="footer.html"/>
<script>
	function eliminaBar(cod){
		 $.get("DeleteBar?id=" + cod, 
					function(data){
							alert(data);
							$('#bars').load(document.URL +  ' #bars');
				});
	}
	function apri_modifiche(cod){
		
		 $.get("VisualizzaBar?id=" + cod, 
					function(data){
						var res=data["edificio"];
						uploadEdificio(res);
						$("#viewbar").fadeIn();
				});
	}
	
	function chiudi_modifiche(){
		$("#viewbar").fadeOut();
	}
	
	function uploadEdificio(disponib){
		
		for(i=0;i<disponib.length;i++){
			$("#nome").val(disponib[i]["nome"]);
			$("#orario").val(disponib[i]["orario_chiusura"]);
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id_edificio"]+")'>Aggiorna</button>");
		}
	}
	
	//chiama la servlet che si occupa di aggiornare il bar
	function update(cod){
			
		if($("#nome").val()=="" || $("#orario").val()==""){
			alert("Compila tutti i campi");
			return;
		}

		
				$.post("Editbar",{
					id:cod,
					nome:$("#nome").val(),
					orario:$("#orario").val()
				},
				function(data){
					alert(data);
					chiudi_modifiche();
					$('#bars').load(document.URL +  ' #bars');
				});
	}
	
	function apri_inserimento(){
		alert();
		$("#insertbar").fadeIn();
	}
</script>
</body>
</html>