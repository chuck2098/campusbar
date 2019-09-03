<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_bar" name="active_menu"/>
</jsp:include>

        <div class="container" id="bars">
				<h2 style="text-align:center; cursor:pointer;" onclick="location.reload()" >Gestione Bar </h2><br><br>
				<c:if test="${pattern!=null}">
					<h4 class="text-center">Risultati per la ricerca '<c:out value="${pattern}"/>'</h4><br>
				</c:if>
				<div class="row">
					<c:forEach items="${edifici}" var="edificio">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/ed<c:out value="${edificio.getId_edificio()}"/>.png"
										alt="Card image" onerror="src='images/logo.png'">
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
					<a style="cursor:pointer;"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="Inserisci nuovo bar"></a>
					
					<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewbar" style="padding:5px; height:auto;">
						<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_modifiche()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<span>Nome bar </span>
							<input type=text id='nome' style='width:80%; height:40px;'>
							<br><br> 
							<span>Orario chiusura </span>
							<input type=number id='orario' style='width:70px; height:50px;'>&nbsp;&nbsp;&nbsp;
							<span id="matr_attuale" style="font-size:19px;"></span>
							<br><br>
							<span>Email </span>
							<input type=email id='email' style='width:80%; height:50px;'>
							<br><br>
							<span>Password </span>
							<input type=password id='pass' style='width:50%; height:50px;'>
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
					<div class="chooseBar" id="insertbar" style="padding:5px; height:auto;">
						<h5 style="float:right; position:absolute; bottom:5px; right:6px;"><a href="#" onclick="chiudi_inserimento()">Chiudi</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<span>Nome bar </span>
							<input type=text id='nome_bar' style='width:80%; height:40px;'>
							<br><br> 
							<span>Orario chiusura </span>
							<input type=number id='orario_bar' placeholder="00" style='width:70px; height:50px;'>&nbsp;
							Matricola <input type=text id='matricola_bar' style='width:30%; height:50px;'><br><br>
							<span>Email</span>
							<input type=email id='email_bar' style='width:80%; height:50px;'>
							<br><br>
							<span>Password</span>
							<input type=password id='password_bar' style='width:60%; height:50px;'>
							<br><br>
						</div><br>
						<button id='submitOrder' style='width:auto; margin:auto; margin-left:15px;' onclick='inserisci()'>Inserisci</button>
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
						var res1=data["utenteEdificio"];
						uploadEdificio(res);
						uploadUtenteEdificio(res1);
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
			$("#filename").val("ed"+disponib[i]["id_edificio"]+".png");
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id_edificio"]+")'>Aggiorna</button>");
		}
	}
	
	function uploadUtenteEdificio(disponib){
		
		for(i=0;i<disponib.length;i++){
			$("#pass").val(disponib[i]["password"]);
			$("#email").val(disponib[i]["email"]);
			$("#matr_attuale").text(" Matricola:"+disponib[i]["matricola"]);
			$("#matr_attuale").val(disponib[i]["matricola"]);
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
					orario:$("#orario").val(),
					email:$("#email").val(),
					pass:$("#pass").val(),
					matr:$("#matr_attuale").val()
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
		
		$.post("InsertBar",{
			nome:$("#nome_bar").val(),
			orario:$("#orario_bar").val(),
			email:$("#email_bar").val(),
			pass:$("#password_bar").val(),
			matr:$("#matricola_bar").val()
		},
		function(data){
			alert(data);
			chiudi_inserimento();
			$('#bars').load(document.URL +  ' #bars');
		});
	}
	
	function apri_inserimento(){
		$("#insertbar").fadeIn();
	}
	
	function chiudi_inserimento(){
		$("#insertbar").fadeOut();
	}
</script>
</body>
</html>