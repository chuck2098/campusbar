<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CampusBar-HomePage</title>
</head>
<body>
    <c:forEach items="${prodotti}" var="prodotto">
			<div col="1/3">
				<h3>
					<a href="Prodotto?id=<c:out value="${prodotto.id}"/>"><c:out value="${prodotto.nome}" /></a>
				</h3>
				<a href="Prodotto?id=<c:out value="${prodotto.id}"/>"><img src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg"></a>
				<h4>Prezzo: <c:out value="${prodotto.prezzo}" /> &euro;</h4>
			</div>
		</c:forEach>
</body>
</html>