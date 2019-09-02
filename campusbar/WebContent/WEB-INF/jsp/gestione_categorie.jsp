<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_bar" name="active_menu"/>
</jsp:include>

        <div class="container" id="bars">
				<h2 style="text-align:center;">Gestione Categorie </h2><br><br>
				<div class="row">
					<c:forEach items="${categorie}" var="categoria">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/cat<c:out value="${categoria.getId_categoria()}"/>.png"
										alt="Card image">
								</div>
								<div class="card-body">
									<h4 class="card-title" style="height:60px; font-size:21px;" >
										<c:out value="${categoria.getNomeCategoria()}" />
									</h4>
								<div class="card-info">
									<h5 style="font-weight:bold;">id Categoria <c:out value="${categoria.getId_categoria()}" /></h5>
								</div>
								<br>
						<button type="button" class="btn" style="float:left; width:auto; padding-left:13px; padding-right:13px;" onclick="if(confirm('Sei sicuro di voler eliminare?')) eliminaCategoria(<c:out value="${categoria.getId_categoria()}"/>)">Elimina</button>
									<button type="button" class="btn" style="float:right; width:auto; padding-left:13px; padding-right:13px;" onclick="apri_modifiche(<c:out value="${categoria.getId_categoria()}"/>)">Modifica</button>
								</div>
							</div>
						</div>
					</c:forEach>
					<a style="cursor:pointer;"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="inserisci una nuova categoria"></a>
					
					<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewbar" style="padding:5px; height:auto;">
					<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_modifiche()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome' style='width:90%; height:40px;'>
							<br><br> 
							<span>Orario chiusura </span>
							<input type=number id='idCat' style='width:70px; height:50px;'>
							<br><br>
							<form id="uploadForm" enctype='multipart/form-data'>
	                <input type="file" name="file" id="sampleFile" form="uploadForm"/>
	                <input type="hidden" id="filename">
	                <input type="button" onclick="uploadImg()" value="Carica" />
	            </form>
						</div><br><br>
						<div id='btn_modifica' style='margin-top:-25px; margin-left:10px;'></div>
					</div>
					
					<!-- div nascosto per inserimento -->
					<div class="chooseBar" id="insertbar" style="padding:5px;">
					<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_inserimento()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome_cat' placeholder="nome Categoria" style='width:90%; height:40px;'>
							<br><br> 
							<span>id </span>
							<input type=number id='id categoria' placeholder="00" style='width:70px; height:50px;'>
						</div><br>
						<button id='submitOrder' style='width:auto; margin:auto; margin-left:15px;' onclick='inserisci()'>Inserisci</button>
					</div>
				
				</div>
</div>
<jsp:include page="footer.html"/>
<script>
	function eliminaCategoria(cod){
		 $.get("DeleteCategoria?id=" + cod, 
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
	function apri_inserimento(){
		$("#insertbar").fadeIn();
	}
	
	function chiudi_inserimento(){
		$("#insertbar").fadeOut();
	}
	
	function uploadEdificio(disponib){
		
		for(i=0;i<disponib.length;i++){
			$("#nome").val(disponib[i]["nome"]);
			$("#orario").val(disponib[i]["orario_chiusura"]);
			$("#filename").val("ed"+disponib[i]["id_edificio"]+".png");
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id_edificio"]+")'>Aggiorna</button>");
		}
	}
	
	//chiama la servlet che si occupa di aggiornare il bar
	function update(cod){
			
		if($("#nome").val().trim().length==0 || $("#orario").val().trim().length==0){
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
	
	function inserisci(){
		
		$.post("Insertbar",{
			nome:$("#nome_bar").val(),
			orario:$("#orario_bar").val()
		},
		function(data){
			alert(data);
			chiudi_inserimento();
			$('#bars').load(document.URL +  ' #bars');
		});
	}
	
	
</script>
</body>
</html>