<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Senha:</label><br>
        <input type="password" name="senha" required><br><br>

        <label>Tipo de Usuário:</label><br>
        <select name="tipoUsuario" required>
            <option value="cliente">Cliente</option>
            <option value="prestador">Prestador</option>
        </select><br><br>

        <input type="submit" value="Entrar">
    </form>

    <c:if test="${not empty erro}">
        <p style="color:red;">${erro}</p>
    </c:if>
</body>
</html>