<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Cadastro de prestador</title>
</head>
<body>

	<h1>Cadastro de prestador</h1>
	<br><br>
	<hr>
	<br><br>
	<form action="cadastroPrestador" method="post" enctype="multipart/form-data">
		Email: <input type="email" name="email" required="required"><br> <br>
		Senha: <input type="password" name="senha" required="required"><br> <br>
		<br>
		<hr>
		<br>
		Nome fantasia: <input type="text" name="nome_fantasia" required="required"><br> <br>
		Nome completo: <input type="text" name="nome_completo" required="required"><br> <br>
		Descrição: <input type="text" name="descricao" required="required"><br> <br>
		Cidade: <select name="endereco" required="required">
			<option value="São Paulo">Araraquara</option>
			<option value="Rio de Janeiro">São Carlos</option>
			<option value="Belo Horizonte">Matão</option><br> <br>
	
		
		</select><br> <br> Foto de perfil/logo: <input type="file" name="foto_perfil" required="required"
		accept="image/png, image/jpeg"><br> <br>
		
		<button type="submit">Cadastrar</button>
	</form>
	
	<br><br>
	<hr>
	<br><br>
	
</body>
</html>