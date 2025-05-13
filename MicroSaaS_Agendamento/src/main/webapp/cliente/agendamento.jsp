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
</head>
<body>
	<h2>Agendar um horário</h2>

	<form method="get" action="/MicroSaaS_Agendamento/buscarHorariosDisponiveis">
		<input type="hidden" name="id_prestador" value="<%=idPrestador%>" />
		<label for="data_agendamento">Escolha uma data:</label> <input
			type="date" name="data_agendamento" required />
		<button type="submit">Ver Horários</button>
	</form>

	<hr />

	<%
	if (horariosDisponiveis != null && !horariosDisponiveis.isEmpty()) {
	%>
	<h3>
		Horários disponíveis para
		<%=dataSelecionada%>:
	</h3>
	<form method="post" action="AgendarServicoServlet">
		<input type="hidden" name="id_prestador" value="<%=idPrestador%>" />
		<input type="hidden" name="data_agendamento"
			value="<%=dataSelecionada%>" /> <select name="hora_agendamento"
			required>
			<%
			for (String hora : horariosDisponiveis) {
			%>
			<option value="<%=hora%>"><%=hora%></option>
			<%
			}
			%>
		</select>
		<button type="submit">Confirmar Agendamento</button>
	</form>
	<%
	} else if (dataSelecionada != null) {
	%>
	<p>Nenhum horário disponível para esta data.</p>
	<%
	}
	%>
</body>
</html>
