<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Agenda de serviços</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4 text-primary">Minha Agenda</h2>

    <form method="get" action="/MicroSaaS_Agendamento/ListarAgendamentosServlet" class="row g-3 align-items-end mb-4">
        <input type="hidden" name="id_prestador" value="${idPrestador}" />
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
            <thead class="table-light">
                <tr>
                    <th>Cliente</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Status</th>
                    <th>Ação 1</th>
                    <th>Ação 2</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${agendamentos}">
                    <tr>
                        <td>${a.idCliente}</td>
                        <td>${a.data}</td>
                        <td>${a.horario}</td>
                        <td>${a.status}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.status == 'solicitado'}">
                                    <form method="post" action="/MicroSaaS_Agendamento/AtualizarStatusServlet">
                                        <input type="hidden" name="id_agendamento" value="${a.idAgendamento}" />
                                        <input type="hidden" name="novo_status" value="aceito" />
                                        <button type="submit" class="btn btn-success btn-sm">Aceitar</button>
                                    </form>
                                </c:when>
                                <c:when test="${a.status == 'aceito'}">
                                    <form method="post" action="/MicroSaaS_Agendamento/AtualizarStatusServlet">
                                        <input type="hidden" name="id_agendamento" value="${a.idAgendamento}" />
                                        <input type="hidden" name="novo_status" value="concluido" />
                                        <button type="submit" class="btn btn-warning btn-sm">Concluir</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-muted">-</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form method="get" action="/MicroSaaS_Agendamento/VerLogServlet">
                                <input type="hidden" name="id_agendamento" value="${a.idAgendamento}" />
                                <button type="submit" class="btn btn-info btn-sm">Ver Log</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-4">
        <c:choose>
            <c:when test="${pagina > 0}">
                <a class="btn btn-secondary me-2" href="/MicroSaaS_Agendamento/ListarAgendamentosServlet?pagina=${pagina - 1}">Anterior</a>
            </c:when>
            <c:otherwise>
                <span class="btn btn-secondary disabled me-2">Anterior</span>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${pagina < totalPaginas - 1}">
                <a class="btn btn-secondary" href="/MicroSaaS_Agendamento/ListarAgendamentosServlet?pagina=${pagina + 1}">Próximo</a>
            </c:when>
            <c:otherwise>
                <span class="btn btn-secondary disabled">Próximo</span>
            </c:otherwise>
        </c:choose>
    </div>

    <hr class="my-5" />

    <p><a href="dashboardPrestador.jsp" class="btn btn-outline-dark">Voltar</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
