<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de cliente</title>
</head>
<body>

	<h1>Cadastro de cliente</h1>
	<br><br>
	<hr>
	<br><br>
	<form action="cadastroCliente" method="post">
		Email: <input type="email" name="email" required="required"><br> <br>
		Senha: <input type="password" name="senha" required="required"><br> <br>
		<br>
		<hr>
		<br>
		Nome: <input type="text" name="nome" required="required"><br> <br>
		CPF: <input type="text" name="cpf" required="required"><br> <br>
		Endereço: <input type="text" name="endereco" required="required"><br> <br>
		Contato: <input type="tel" name="contato" required="required"><br> <br>
		
		<button type="submit">Cadastrar</button>
	</form>
	
	<br><br>
	<hr>
	<br><br>
	
</body>
</html>