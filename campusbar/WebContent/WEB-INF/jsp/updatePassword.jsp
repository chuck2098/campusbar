<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp">
	<jsp:param value="pass" name="active_menu" />
	<jsp:param value="Cambio Password" name="title"/>
</jsp:include>

    <div id="alertBox">
        
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" onClick='CloseAlert();'></p>
    </div>

<div class="container" id="orders">
	<h2 style="text-align: center;">Cambio Password</h2>
	<br>
	
	<div class="row">
						<div class='table-responsive'  style='overflow-x: auto; text-align: center;'>
							<table>
								
								<thead>
										<tr>
											<td> <input type="password" class="form-control"  id="1"  name="pass1" placeholder="Password attuale" onchange=" return ValidatePassword(password)" required="required"></td>
										</tr>
										<tr>
											<td ><input type="password" class="form-control"  id="2" name="pass2" placeholder="Password attuale" onchange=" return ValidatePassword(password)" required="required"></td>
										</tr>
										<tr>
											<td ><input type="password" class="form-control" minlength="6" id="3" name="pass3" placeholder="nuova Password" onchange=" return ValidatePassword(password)" required="required"></td>
										</tr>
										<tr>
											<td><br><button type="submit" onclick="Update(); ValidatePassword()" id="submitOrder" style="padding:8px;">Cambia</button></td>
										</tr>
									</thead>

							</table>
						</div>
					</div>
	

</div>
<jsp:include page="footer.html" />
<script>
function CloseAlert(){
	$("#alertBox").hide();
}

function OpenAlert(){
	$("#alertBox").show(70);
}

function ValidatePassword(pass){
	var pass=document.getElementById(3).value; 
	
	var passFormat1 = /[0-9]+/;
	var passFormat2=/[a-z]+/;
	var passFormat3=/[A-Z]+/;	
	if(pass.value.match(passFormat1) && pass.value.match(passFormat2)  && pass.value.match(passFormat3)){
		return true;
	}
	else{
		$("#msg").text("Hai inserito una password non valida");
		OpenAlert();
		pass.focus();
		return false;
	}
}
function Update(){
	
	var pass1=document.getElementById(1).value;
	var pass2=document.getElementById(2).value;
	var pass3=document.getElementById(3).value;
	
	document.getElementById(1).value="";
	document.getElementById(2).value="";
	document.getElementById(3).value="";
	
	if(!pass1 && !pass2 && !pass3){
		$("#msg").text("completa tutti i campi prima di procedere'");
		OpenAlert();
		return;
	}
		

		$.get("UpdatePassword?pass1=" + pass1 + "&pass2=" + pass2 + "&pass3=" + pass3, 
			function(data){
			$("#msg").text(data);
			OpenAlert();
		});
	}


 
</script>
</body>
</html>