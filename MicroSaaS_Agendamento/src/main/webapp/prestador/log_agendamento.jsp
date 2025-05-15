<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Log do agendamento</title>
</head>
<body>
	<h2>Histórico de Mudança de Status</h2>

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>Status Antigo</th>
			<th>Status Novo</th>
			<th>Data da Alteração</th>
		</tr>
		<c:forEach var="log" items="${logs}">
			<tr>
				<td>${log.status_antigo}</td>
				<td>${log.status_novo}</td>
				<td>${log.data_alteracao}</td>
			</tr>
		</c:forEach>
	</table>

	<br>

</body>
</html>
