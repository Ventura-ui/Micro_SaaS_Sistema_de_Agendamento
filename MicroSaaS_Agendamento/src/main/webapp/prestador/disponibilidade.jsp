<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Disponibilidade</title>
</head>
<body>
	<h2>Cadastrar horários</h2>

	<form action="/MicroSaaS_Agendamento/CadastrarDisponibilidadeServlet" method="post">
		<table border="1">
			<thead>
				<tr>
					<th>Dia da Semana</th>
					<th>Horário Início</th>
					<th>Horário Fim</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="1" end="7">
					<tr>
						<td><c:choose>
								<c:when test="${i == 1}">Domingo</c:when>
								<c:when test="${i == 2}">Segunda</c:when>
								<c:when test="${i == 3}">Terça</c:when>
								<c:when test="${i == 4}">Quarta</c:when>
								<c:when test="${i == 5}">Quinta</c:when>
								<c:when test="${i == 6}">Sexta</c:when>
								<c:when test="${i == 7}">Sábado</c:when>
							</c:choose></td>
						<td><input type="time" name="inicio_${i}" /></td>
						<td><input type="time" name="fim_${i}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="idPrestador" value="${idPrestador}" />
		<button type="submit">Salvar Disponibilidade</button>
	</form>
	
	<c:if test="${not empty mensagem}">
        <p>${mensagem}</p>
    </c:if>
    <br><br><br>

	<p>
		<a href="dashboardPrestador.jsp">Voltar</a>
	</p>
</body>
</html>
