<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="totali_per_prodotto" name="active_menu"/>
	<jsp:param value="Totali Del Bar" name="title"/>
</jsp:include>

        <div class="container" id="orders">
				<h2 style="text-align:center;">Totali per prodotto</h2><br><br>
				<div style="margin:auto; text-align:center; font-size:18px; width:100%;">
					<div >Data iniziale <input type="date" id="data_start" class="browser-default custom-select" style=" margin-bottom:10px;"></div>
					<div>Data finale &nbsp; &nbsp;<input type="date" id="data_end" class="browser-default custom-select" > </div><br>
					<button id='submitOrder' style='width:auto; padding:12px;' onclick='uploadAmount()'>Carica totali</button>
				</div><br><br>
					<div class="row">
						<div class='table-responsive' id="disponibilitaCategoria" style='overflow-x: auto; text-align: center;'>
							<table>
								<thead>
										<tr>
											<th style='color: #4C4C4C;'>Prodotto</th>
											<th style='color: #4C4C4C; width:40px;'>Qnt.</th>
											<th style='color: #4C4C4C;'>Prezzo</th>
											<th style='color: #4C4C4C;'>Totale €</th>
										</tr>
									</thead>
								<tbody id="totali"></tbody>
							</table>
						</div>
					</div>
			</div>
<jsp:include page="footer.html"/>
<script>
	function uploadAmount(){
		
		$.post("VisualizzaTotaliBar",{
			dateStart:$("#data_start").val(),
			dateEnd:$("#data_end").val()
		},
		function(data){
			var res=data["totali"];
			uploadTotali(res);
		});
		
	}
	
	function uploadTotali(tot){
		
		var response="";
		var quant=0,totali=0,prez=0;
		for(i=0;i<tot.length;i++){
			response+="<tr>";
				if(isEmpty(tot[i]["prodotto"]))
					response+="<td style='font-size:18px; font-weight:700;''>Non Disponibile</td>";
				else
					response+="<td style='font-size:18px; font-weight:700;''>"+tot[i]["prodotto"]["nome"]+"</td>";
				response+="<td style='font-size:18px; font-weight:700;'>"+tot[i]["quantita"]+"</td>";
				response+="<td style='font-size:18px; font-weight:700;'>"+Math.round(tot[i]["prezzo_acquisto"] * 100) / 100+"</td>";
				response+="<td style='font-size:18px; font-weight:700;'>"+Math.round(tot[i]["totale"]*100)/100+"</td>";
			response+="</tr>";
			quant+=tot[i]["quantita"];
			prez+=tot[i]["prezzo_acquisto"];
			totali+=Math.round(tot[i]["totale"]*100)/100;
		}
		response+="<td style='font-size:20px; font-weight:700;''>Totale</td>";
		response+="<td style='font-size:20px; font-weight:700;''>"+quant+"</td>";
		response+="<td style='font-size:20px; font-weight:700;''>"+prez+"</td>";
		response+="<td style='font-size:20px; font-weight:700;''>"+totali+" €</td>";
		
		$("#totali").html(response);
		
	}
	
	function isEmpty(obj) {
	    for(var key in obj) {
	        if(obj.hasOwnProperty(key))
	            return false;
	    }
	    return true;
	}
</script>
</body>
</html>