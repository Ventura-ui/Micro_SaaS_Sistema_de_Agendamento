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
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/estilo.css" rel="stylesheet">
</head>
<body>

<div class="overlay"></div>

<div class="container mt-5 shadow-lg rounded">
    <div class="card bg-transparent border-0"> <div class="card-body">
            <h2 class="card-title text-center mb-4">Histórico de Mudança de Status</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-striped text-center align-middle">
                    <thead class="table-dark"> <tr>
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
                        <c:if test="${empty logs}">
                            <tr>
                                <td colspan="3" class="text-center text-muted">Nenhum registro de log encontrado.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <div class="text-center mt-4">
                <a href="javascript:history.back()" class="btn btn-secondary">Voltar</a>
            </div>
        </div>
    </div>
    <p class="text-center text-muted mt-4 small">© 2025 TopBarber - Todos os direitos reservados</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>