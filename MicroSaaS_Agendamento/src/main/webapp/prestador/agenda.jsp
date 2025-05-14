<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Agenda de serviços</title>
</head>
<body>
	<h2>Minha Agenda</h2>

    <form method="get" action="ListarAgendamentosServlet">
        <input type="hidden" name="id_prestador" value="${idPrestador}" />
        <label>Filtrar por status:</label>
        <select name="status">
            <option value="">Todos</option>
            <option value="solicitado" ${param.status == 'solicitado' ? 'selected' : ''}>Solicitado</option>
            <option value="aceito" ${param.status == 'aceito' ? 'selected' : ''}>Aceito</option>
            <option value="concluido" ${param.status == 'concluido' ? 'selected' : ''}>Concluído</option>
        </select>
        <button type="submit">Filtrar</button>
    </form>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Cliente</th>
            <th>Data</th>
            <th>Hora</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>

        <c:forEach var="a" items="${agendamentos}">
            <tr>
                <td>${a.idCliente}</td>
                <td>${a.data}</td>
                <td>${a.horario}</td>
                <td>${a.status}</td>
                <td>
                    <c:choose>
                        <c:when test="${a.status == 'solicitado'}">
                            <form method="post" action="AtualizarStatusServlet">
                                <input type="hidden" name="id_agendamento" value="${a.idAgendamento}" />
                                <input type="hidden" name="novo_status" value="aceito" />
                                <button type="submit">Aceitar</button>
                            </form>
                        </c:when>
                        <c:when test="${a.status == 'aceito'}">
                            <form method="post" action="AtualizarStatusServlet">
                                <input type="hidden" name="id_agendamento" value="${a.idAgendamento}" />
                                <input type="hidden" name="novo_status" value="concluido" />
                                <button type="submit">Concluir</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <span>-</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
