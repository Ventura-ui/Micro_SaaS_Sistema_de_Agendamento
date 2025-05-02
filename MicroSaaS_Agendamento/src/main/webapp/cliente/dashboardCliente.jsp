<%@page import="model.Prestador"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bem-vindo Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">

<%
    List<Prestador> debugPrestadores = (List<Prestador>) request.getAttribute("prestadores");
%>

<h2>Bem-vindo!</h2>

<a href="/MicroSaaS_Agendamento/LogoutServlet" class="btn btn-danger mb-3">Fazer Logout</a>

<form method="get" action="/MicroSaaS_Agendamento/dashboardCliente">
    <label for="cidade">Filtrar por cidade:</label>
    <select name="cidade" id="cidade" class="form-select w-25 d-inline-block">
        <option value="">Todas</option>
        <option value="Araraquara">Araraquara</option>
        <option value="São Carlos">São Carlos</option>
        <option value="Matão">Matão</option>
    </select>
    <button type="submit" class="btn btn-primary">Filtrar</button>
</form>

<c:if test="${empty prestadores}">
    <div class="alert alert-warning mt-4">Nenhum prestador encontrado.</div>
</c:if>

<div class="row mt-4">
    <c:forEach var="p" items="${prestadores}">
        <div class="col-md-4 mb-4">
            <div class="card" data-bs-toggle="modal" data-bs-target="#modal${p.id_prestador}">
                <c:choose>
                    <c:when test="${not empty p.foto_perfil}">
                        <img src="/MicroSaaS_Agendamento/imagensPrestadores/${p.foto_perfil}" class="card-img-top" alt="Foto do prestador" style="height:200px; object-fit:cover;">
                    </c:when>
                    <c:otherwise>
                        <img src="https://via.placeholder.com/300x200?text=Sem+Foto" class="card-img-top" alt="Foto padrão" style="height:200px; object-fit:cover;">
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h5 class="card-title">${p.nome_fantasia}</h5>
                    <p class="card-text"><strong>Endereço:</strong> ${p.endereco}</p>
                    <p class="card-text"><strong>Descrição:</strong> ${p.descricao}</p>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal${p.id_prestador}" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">${p.nome_fantasia} - Portfólio</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <c:choose>
                            <c:when test="${not empty p.imagens}">
                                <div id="carousel${p.id_prestador}" class="carousel slide" data-bs-ride="carousel">
                                    <div class="carousel-inner">
                                        <c:forEach var="img" items="${p.imagens}" varStatus="status">
                                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                                <img src="/MicroSaaS_Agendamento/imagensPrestadores/${img}" class="d-block w-100" alt="Imagem do serviço">
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button class="carousel-control-prev" type="button" data-bs-target="#carousel${p.id_prestador}" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon"></span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carousel${p.id_prestador}" data-bs-slide="next">
                                        <span class="carousel-control-next-icon"></span>
                                    </button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p>Este prestador não possui imagens no portfólio.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-success">Agendar</button>
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>