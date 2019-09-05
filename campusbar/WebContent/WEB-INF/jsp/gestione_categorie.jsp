<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_categorie" name="active_menu"/>
	<jsp:param value="Gestione Categorie" name="title"/>
</jsp:include>

    <div id="alertBox">
        
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" onClick='CloseAlert();'></p>
    </div>
    
        <div class="container" id="bars">
				<h2 style="text-align:center; cursor:pointer;" onclick="location.reload()">Gestione Categorie </h2><br><br>
				<c:if test="${pattern!=null}">
					<h4 class="text-center">Risultati per la ricerca '<c:out value="${pattern}"/>'</h4><br>
				</c:if>
				<div class="row">
					<c:forEach items="${categorie}" var="categoria">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="img-container">
									<img class="card-img-top"
										src="images/cat<c:out value="${categoria.getId_categoria()}"/>.png"
										alt="Card image" onerror="src='images/logo.png'">
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
					<div class="col-lg-3 col-sm-6" style="text-align:center;">
						<a style="cursor:pointer; margin:auto;"><img src="images/add.png" onclick="apri_inserimento()" style="width:256px;" title="Inserisci nuova categoria"></a>
					</div>
					
					<!-- div nascosto per modifica -->	
					<div class="chooseBar" id="viewcate">
					  <h5 style="float:right; width:24px; position:relative; right:5px; border:2px solid #eee; cursor:pointer;" onclick="chiudi_modifiche()"><a style="text-decoration:none;color:#eee;">x</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome_cat' style='width:85%; height:40px; margin:auto;' class="form-control">
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
					<div class="chooseBar" id="insertcate">
						<h5 style="float:right; width:24px; position:relative; right:5px; border:2px solid #eee; cursor:pointer;" onclick="chiudi_inserimento()"><a style="text-decoration:none;color:#eee;">x</a></h5><br>
						<div class='table-responsive' style='overflow-x: auto; text-align: center; width:100%; margin-top:2px;'>
							<input type=text id='nome_categoria' placeholder="Nome categoria" style='width:85%; height:40px; margin:auto;' class="form-control">
							<br>
						</div>
						<button id='submitOrder' style='width:auto; margin:auto; margin-left:15px;' onclick='inserisci()'>Inserisci</button>
					</div>
				
				</div>
</div>
<jsp:include page="footer.html"/>
<script>
	function CloseAlert(){
		$("#alertBox").hide();
		$('body').html(reloadPage());
	}
	
	function OpenAlert(){
		$("#alertBox").show(70);
	}

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

	function eliminaCategoria(cod){
		 $.get("DeleteCategoria?id=" + cod, 
					function(data){
						//alert(data);
						$('body').html(reloadPage());
				});
	} 
	function apri_modifiche(cod){
		
		 $.get("VisualizzaCategorieAdmin?id=" + cod, 
					function(data){
						var res=data["categoria"];
						uploadCategoria(res);
						$("#viewcate").fadeIn();
				});
	}
	
	function chiudi_modifiche(){
		$("#viewcate").fadeOut();
	}
	function apri_inserimento(){
		$("#insertcate").fadeIn();
	}
	
	function chiudi_inserimento(){
		$("#insertcate").fadeOut();
	}
	
	function uploadCategoria(disponib){
		
		for(i=0;i<disponib.length;i++){
			$("#nome_cat").val(disponib[i]["nomeCategoria"]);
			$("#filename").val("cat"+disponib[i]["id_categoria"]+".png");
			$("#btn_modifica").html("<button id='submitOrder' style='width:auto; margin:auto;' onclick='update("+disponib[i]["id_categoria"]+")'>Aggiorna</button>");
		}
	}
	
	//chiama la servlet che si occupa di aggiornare il bar
	function update(cod){
			
		if($("#nome_cat").val().trim().length==0 ){
			$("#msg").text("Compila tutti i campi");
			OpenAlert();
			return;
		}


				$.post("EditCategory",{
					id:cod,
					nome_cat:$("#nome_cat").val(), 
					
				},
				function(data){
					$("#msg").text(data);
					chiudi_modifiche();
					OpenAlert();
					
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

		if($("#nome_categoria").val().trim().length==0 ){
			$("#msg").text("Compila tutti i campi");
			OpenAlert();
			
			return;
		}
		
		$.post("InsertCategoriaAdmin",{
			nome:$("#nome_categoria").val(),
		},
		function(data){
			$("#msg").text(data);
			
			chiudi_inserimento();
			OpenAlert();
			$('body').html(reloadPage());
			//$('#bars').load(document.URL +  ' #bars');
		});
	}
	
	
</script>
</body>
</html>