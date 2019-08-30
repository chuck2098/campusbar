<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

			<section class="content">
					<br><h1 class="text-center">Categorie</h1>
             <section class="container">
                  <div class="row">
								    <c:forEach items="${categorie}" var="categoria">
											<div class="col-lg-3 col-sm-6" >
					<div class="card" >
	                          <div class="img-container">
	                              <img class="card-img-top" style="width: 100px ; height: 200px " src="images/cat<c:out value="${categoria.id_categoria}"/>.png" alt="Card image" >
	                         </div>
	                         
							<div class="card-body">
										<h4 class="card-title"><c:out value="${categoria.nomeCategoria}" /></h4>
		                              
		                                <button type="button" class="btn" onclick ="">Visualizza Categoria</button>
		                     </div>
	                   </div>
												</div>
										</c:forEach> 
										
                   </div><!-- chiude il div row -->
               </section>
        </section>
<jsp:include page="footer.html"/>
<script>
	
	
	
</script>
</body>
</html>