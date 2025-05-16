<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Disponibilidade</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Cadastrar Horários de Disponibilidade</h2>

            <form action="/MicroSaaS_Agendamento/CadastrarDisponibilidadeServlet" method="post">
                <div class="table-responsive">
                    <table class="table table-bordered align-middle text-center">
                        <thead class="table-light">
                            <tr>
                                <th>Dia da Semana</th>
                                <th>Início</th>
                                <th>Fim</th>
                                <th>Descanso Início</th>
                                <th>Descanso Fim</th>
                                <th>Tempo de Serviço</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" begin="1" end="7">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${i == 1}">Segunda</c:when>
                                            <c:when test="${i == 2}">Terça</c:when>
                                            <c:when test="${i == 3}">Quarta</c:when>
                                            <c:when test="${i == 4}">Quinta</c:when>
                                            <c:when test="${i == 5}">Sexta</c:when>
                                            <c:when test="${i == 6}">Sábado</c:when>
                                            <c:when test="${i == 7}">Domingo</c:when>
                                        </c:choose>
                                    </td>
                                    <td><input type="time" class="form-control" name="inicio_${i}" /></td>
                                    <td><input type="time" class="form-control" name="fim_${i}" /></td>
                                    <td><input type="time" class="form-control" name="descanso_inicio_${i}" /></td>
                                    <td><input type="time" class="form-control" name="descanso_fim_${i}" /></td>
                                    <td><input type="time" class="form-control" name="tempo_servico_${i}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <input type="hidden" name="idPrestador" value="${idPrestador}" />

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary px-4">Salvar Disponibilidade</button>
                </div>
            </form>

            <c:if test="${not empty sessionScope.mensagem}">
                <div class="alert alert-info text-center mt-4">${sessionScope.mensagem}</div>
                <c:remove var="mensagem" scope="session" />
            </c:if>

            <div class="text-center mt-4">
                <a href="dashboardPrestador.jsp" class="btn btn-outline-secondary">Voltar</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
