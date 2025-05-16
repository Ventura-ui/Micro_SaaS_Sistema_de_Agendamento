<%@ page import="java.util.*, java.time.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<%
	int idPrestador = Integer.parseInt(request.getParameter("id_prestador"));
	request.setAttribute("id_prestador", idPrestador);

	List<String> horariosDisponiveis = (List<String>) request.getAttribute("horariosDisponiveis");
	String dataSelecionada = (String) request.getAttribute("dataSelecionada");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agendamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4 text-primary">Agendar um horário</h2>

    <form method="get" action="/MicroSaaS_Agendamento/buscarHorariosDisponiveis" class="mb-4">
        <input type="hidden" name="id_prestador" value="<%=idPrestador%>" />
        <div class="mb-3">
            <label for="data_agendamento" class="form-label">Escolha uma data:</label>
            <input type="date" name="data_agendamento" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-primary">Ver Horários</button>
    </form>

    <hr />

    <%
    if (horariosDisponiveis != null && !horariosDisponiveis.isEmpty()) {
    %>
    <h4 class="text-success mb-3">
        Horários disponíveis para <%=dataSelecionada%>:
    </h4>
    <form method="post" action="agendamentoServlet" class="mb-4">
        <input type="hidden" name="id_prestador" value="<%=idPrestador%>" />
        <input type="hidden" name="data_agendamento" value="<%=dataSelecionada%>" />
        <div class="mb-3">
            <label for="hora_agendamento" class="form-label">Selecione o horário:</label>
            <select name="hora_agendamento" class="form-select" required>
                <%
                for (String hora : horariosDisponiveis) {
                %>
                <option value="<%=hora%>"><%=hora%></option>
                <%
                }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Confirmar Agendamento</button>
    </form>
    <%
    } else if (dataSelecionada != null) {
    %>
    <div class="alert alert-warning">Nenhum horário disponível para esta data.</div>
    <%
    }
    %>

    <c:if test="${not empty mensagem}">
        <div class="alert alert-info">${mensagem}</div>
    </c:if>

    <hr class="mt-5" />
    <a href="/MicroSaaS_Agendamento/dashboardCliente" class="btn btn-secondary mt-2">Voltar</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
