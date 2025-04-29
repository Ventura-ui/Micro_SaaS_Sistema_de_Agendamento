<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Upload de Portfólio</title>
</head>
<body>
    <h2>Enviar Imagens para o Portfólio</h2>
    
    <form action="/MicroSaaS_Agendamento/uploadPortfolio" method="post" enctype="multipart/form-data">
        <input type="file" name="imagens" multiple accept="image/png, image/jpeg" required />
        <input type="submit" value="Enviar" />
    </form>

    <c:if test="${not empty mensagem}">
        <p>${mensagem}</p>
    </c:if>
    
    <br><br><br>
    
    <p><a href="dashboardPrestador.jsp">Voltar</a></p>
</body>
</html>
