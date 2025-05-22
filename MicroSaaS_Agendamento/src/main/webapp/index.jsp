<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TopBarber - Agendamento Online</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<link href="<%= request.getContextPath() %>/css/estilo.css" rel="stylesheet"> <%-- Caminho corrigido aqui --%>
</head>
<body>

	<div class="overlay"></div>

	<div class="container mt-5 shadow-lg rounded">
        <div class="text-center mb-4">
            <img src="<%= request.getContextPath() %>/imagens/TopBarber.png" alt="TopBarber Logo" class="img-fluid" style="max-height: 150px;">
        </div>
		<p class="text-center text-light mb-4">Sua barbearia moderna,
			agora online.</p>

		<div class="text-center mb-4">
			<p>Agende seu horário com os melhores barbeiros da região.</p>
			<p>Serviços profissionais no conforto do seu lar ou em nossas instalações.</p>
		</div>

		<hr>

		<div class="d-grid gap-3 col-md-8 mx-auto">
			<a href="login.jsp" class="btn btn-primary">Fazer Login</a> <a
				href="cadastroCliente.jsp" class="btn btn-success">Cadastro de
				Cliente</a> <a href="cadastroPrestador.jsp" class="btn btn-warning">Cadastro
				de Barbeiro</a>
		</div>

		<hr>
		<p class="text-center text-muted mt-4 small">© 2025 TopBarber -
			Todos os direitos reservados</p>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>