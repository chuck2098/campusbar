<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="gestione_prod" name="active_menu"/>
</jsp:include>

        <div class="container" id="prod">
				<h2 style="text-align:center;">Gestione Prodotti </h2><br><br>
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
									<h4 class="card-title" style="height:60px; font-size:22px;" >
										<c:out value="${prodotto.getNome()}" />
									</h4>
									<button type="button" class="btn" style="float:left; width:auto; padding-left:13px; padding-right:13px;" onclick="if(confirm('Sei sicuro di voler eliinare?')) eliminaProdotto(<c:out value="${prodotto.getId()}"/>)">Elimina</button>
									<button type="button" class="btn" style="float:right; width:auto; padding-left:13px; padding-right:13px;" onclick="apri_modifiche(<c:out value="${prodotto.getId()}"/>)">Modifica</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
</div>
<jsp:include page="footer.html"/>
<script>
	function eliminaProdotto(cod){
		 $.get("DeleteProdotto?id=" + cod, 
					function(data){
							alert(data);
							$('#prod').load(document.URL +  ' #prod');
				});
	}
	function apri_modifiche(cod){
		
		 $.get("VisualizzaProdotti?id=" + cod, 
					function(data){
							alert(data);
							$('#bars').load(document.URL +  ' #bars');
				});
	}
</script>
</body>
</html>