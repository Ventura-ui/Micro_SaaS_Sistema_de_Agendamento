<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/estilo.css" rel="stylesheet">
</head>
<body>

<div class="overlay"></div>

<div class="container mt-5 shadow-lg rounded">
    <div class="card bg-transparent border-0"> <div class="card-body text-center">
            <h1 class="card-title mb-4">Dashboard do Prestador</h1>
            <hr class="my-4">

            <div class="d-grid gap-3 col-md-8 mx-auto"> <a href="disponibilidade.jsp" class="btn btn-primary">Cadastrar Horários</a>
                <a href="uploadPortfolio.jsp" class="btn btn-success">Adicionar Fotos ao Portfólio</a>
                <a href="/MicroSaaS_Agendamento/ListarAgendamentosServlet" class="btn btn-warning">Ver Agendamentos</a>
                <a href="/MicroSaaS_Agendamento/LogoutServlet" class="btn btn-danger">Fazer Logout</a>
            </div>
        </div>
    </div>
    <p class="text-center text-muted mt-4 small">© 2025 TopBarber - Todos os direitos reservados</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>