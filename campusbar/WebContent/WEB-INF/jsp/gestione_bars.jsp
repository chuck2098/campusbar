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
					<div class="col-lg-3 col-sm-6" style="text-align:center;">
						<a style="cursor:pointer; margin:auto;"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="Inserisci nuovo bar"></a>
					</div>
					
					<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewbar">
						<h5 style="float:right; width:24px; position:relative; right:5px; border:2px solid #eee; cursor:pointer;" onclick="chiudi_modifiche()"><a style="text-decoration:none;color:#eee;" >x</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome' placeholder="Nome bar" style='width:80%; height:40px; display:inline;' class="form-control">
							<br><br> 
							<span>Chiude alle</span>
							<input type=number id='orario' min="0" style='width:70px; height:40px; display:inline; margin-bottom:15px;' class="form-control">&nbsp;&nbsp;&nbsp;
							<span id="matr_attuale" style="font-size:19px;"></span>
							<br><br>
							<input type=email id='email' style='width:80%; height:40px; display:inline;' class="form-control">
							<br><br>
							<span>Password </span>
							<input type=password id='pass' style='width:50%; height:40px; display:inline;' class="form-control">
							<br><br>
							<form id="uploadForm" enctype='multipart/form-data'>
	                <input type="file" name="file" id="sampleFile" form="uploadForm" />
	                <input type="hidden" id="filename">
	                <input type="button" onclick="uploadImg()" value="Carica" />
	            </form>
						</div><br><br>
						<div id='btn_modifica' style='margin-top:-25px; margin-left:10px;'></div>
					</div>
					
					<!-- div nascosto per inserimento -->
					<div class="chooseBar" id="insertbar">
						<h5 style="float:right; width:24px; position:relative; right:5px; border:2px solid #eee; cursor:pointer;" onclick="chiudi_inserimento()"><a style="text-decoration:none;color:#eee;" >x</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome_bar' placeholder="Nome bar" style='width:90%; height:40px; display:inline;' class="form-control">
							<br><br> 
							<span>Chiude alle</span>
							<input type=number id='orario_bar' min="0" placeholder="00" style='width:70px; height:50px; display:inline; margin-right:3%;' class="form-control">
							<input type=text id='matricola_bar' placeholder="Matricola" style='width:40%; height:50px; display:inline; padding-left:5px;' class="form-control"><br><br>
							<input type=email id='email_bar' placeholder="Email" style='width:90%; height:40px; display:inline;' class="form-control">
							<br><br>
							<span>Password</span>
							<input type=password id='password_bar' style='width:67%; height:40px; display:inline;' class="form-control">
							<br><br>
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
	
	function eliminaBar(cod){
		 $.get("DeleteBar?id=" + cod, 
					function(data){
							//$('#bars').load(document.URL +  ' #bars');
							$('body').html(reloadPage());
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
			
		if($("#nome").val().trim().length==0 || $("#orario").val().trim().length==0 || 
				 $("#email").val().trim().length==0 || $("#pass").val().trim().length==0 || 
				 $("#matr_attuale").val().trim().length==0){
				
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
	
	function inserisci(){
		
		if($("#nome_bar").val().trim().length==0 || $("#orario_bar").val().trim().length==0 || 
			 $("#email_bar").val().trim().length==0 || $("#password_bar").val().trim().length==0 || 
			 $("#matricola_bar").val().trim().length==0){
			
				alert("Compila tutti i campi");
				return;
		}
		
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
			$('body').html(reloadPage());
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