<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload de Portfólio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/estilo.css" rel="stylesheet">
</head>
<body>

<div class="overlay"></div>

<div class="container mt-5 shadow-lg rounded">
    <div class="card bg-transparent border-0"> <div class="card-body">
            <h2 class="card-title text-center mb-4">Enviar Imagens para o Portfólio</h2>

            <form action="/MicroSaaS_Agendamento/uploadPortfolio" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="imagens" class="form-label">Escolha imagens (JPEG ou PNG):</label>
                    <input type="file" class="form-control" name="imagens" id="imagens" multiple accept="image/png, image/jpeg" required>
                </div>
                <div class="text-center">
                    <input type="submit" class="btn btn-primary" value="Enviar">
                </div>
            </form>

            <c:if test="${not empty sessionScope.mensagem}">
                <div class="alert alert-info mt-4" role="alert">
                    ${sessionScope.mensagem}
                </div>
                <c:remove var="mensagem" scope="session" />
            </c:if>

            <div class="text-center mt-4">
                <a href="dashboardPrestador.jsp" class="btn btn-secondary">Voltar</a>
            </div>
        </div>
    </div>
    <p class="text-center text-muted mt-4 small">© 2025 TopBarber - Todos os direitos reservados</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>