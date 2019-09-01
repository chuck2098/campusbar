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
									<h4 class="card-title" style="height:60px; font-size:22px;" >
										<c:out value="${edificio.getNome()}" />
									</h4>
									<button type="button" class="btn" style="float:left; width:auto; padding-left:13px; padding-right:13px;" onclick="eliminaBar(<c:out value="${edificio.getId_edificio()}"/>)">Elimina</button>
									<button type="button" class="btn" style="float:right; width:auto; padding-left:13px; padding-right:13px;" onclick="apri_modifiche(<c:out value="${edificio.getId_edificio()}"/>)">Modifica</button>
								</div>
							</div>
						</div>
					</c:forEach>
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
							alert(data);
							$('#bars').load(document.URL +  ' #bars');
				});
	}
</script>
</body>
</html>