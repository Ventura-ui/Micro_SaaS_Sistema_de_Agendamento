<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body text-center">
            <h1 class="card-title text-primary mb-4">Dashboard do Prestador</h1>
            <hr class="my-4">
            
            <div class="d-grid gap-3 col-6 mx-auto">
                <a href="disponibilidade.jsp" class="btn btn-outline-primary btn-lg">Cadastrar Horários</a>
                <a href="uploadPortfolio.jsp" class="btn btn-outline-success btn-lg">Adicionar Fotos ao Portfólio</a>
                <a href="/MicroSaaS_Agendamento/ListarAgendamentosServlet" class="btn btn-outline-info btn-lg">Ver Agendamentos</a>
                <a href="/MicroSaaS_Agendamento/LogoutServlet" class="btn btn-outline-danger btn-lg">Fazer Logout</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
