<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Meus Agendamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/estilo.css" rel="stylesheet">
</head>
<body>

<div class="overlay"></div>

<div class="container mt-5 shadow-lg rounded">
    <h2 class="mb-4">Minha Agenda</h2>

    <form method="get" action="/MicroSaaS_Agendamento/ListarAgendaCliente" class="row g-3 align-items-end mb-4">
        <input type="hidden" name="id_cliente" value="${idCliente}" />
        <div class="col-auto">
            <label for="status" class="form-label">Filtrar por status:</label>
            <select name="status" id="status" class="form-select">
                <option value="">Todos</option>
                <option value="solicitado" ${param.status == 'solicitado' ? 'selected' : ''}>Solicitado</option>
                <option value="aceito" ${param.status == 'aceito' ? 'selected' : ''}>Aceito</option>
                <option value="concluido" ${param.status == 'concluido' ? 'selected' : ''}>Concluído</option>
            </select>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Filtrar</button>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-dark"> <tr>
                    <th>Prestador</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${agendamentos}">
                    <tr>
                        <td>${a.idPrestador}</td> 
                        <td>${a.data}</td>
                        <td>${a.horario}</td>
                        <td>${a.status}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty agendamentos}">
                     <tr>
                         <td colspan="4" class="text-center text-muted">Nenhum agendamento encontrado.</td>
                     </tr>
                 </c:if>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-4">
        <c:choose>
            <c:when test="${pagina > 0}">
                <a class="btn btn-secondary me-2 btn-pagination" href="/MicroSaaS_Agendamento/ListarAgendaCliente?pagina=${pagina - 1}&status=${param.status}">Anterior</a>
            </c:when>
            <c:otherwise>
                <span class="btn btn-secondary disabled me-2 btn-pagination">Anterior</span>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${pagina < totalPaginas - 1}">
                <a class="btn btn-secondary btn-pagination" href="/MicroSaaS_Agendamento/ListarAgendaCliente?pagina=${pagina + 1}&status=${param.status}">Próximo</a>
            </c:when>
            <c:otherwise>
                <span class="btn btn-secondary disabled btn-pagination">Próximo</span>
            </c:otherwise>
        </c:choose>
    </div>

    <hr class="my-5" />

    <p><a href="<%= request.getContextPath() %>/dashboardCliente" class="btn btn-secondary">Voltar</a></p>

    <p class="text-center text-muted mt-4 small">© 2025 TopBarber - Todos os direitos reservados</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>