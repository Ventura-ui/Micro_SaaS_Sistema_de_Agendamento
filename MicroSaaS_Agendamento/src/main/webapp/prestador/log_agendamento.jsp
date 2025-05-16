<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log do Agendamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Histórico de Mudança de Status</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-striped text-center align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Status Antigo</th>
                            <th>Status Novo</th>
                            <th>Data da Alteração</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${logs}">
                            <tr>
                                <td>${log.status_antigo}</td>
                                <td>${log.status_novo}</td>
                                <td><fmt:formatDate value="${log.data_alteracao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="text-center mt-4">
                <a href="javascript:history.back()" class="btn btn-outline-secondary">Voltar</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
