<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Error</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h2>Error</h2>
	<div class="mensagem">${mensagem}</div>
	<a href="index.jsp" class="voltar">Voltar ao menu</a>
</body>
</html>