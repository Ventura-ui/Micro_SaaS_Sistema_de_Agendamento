<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">Cadastro de Prestador</h2>

            <form action="cadastroPrestador" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" class="form-control" id="senha" name="senha" required>
                </div>

                <hr>

                <div class="mb-3">
                    <label for="nome_fantasia" class="form-label">Nome Fantasia:</label>
                    <input type="text" class="form-control" id="nome_fantasia" name="nome_fantasia" required>
                </div>

                <div class="mb-3">
                    <label for="nome_completo" class="form-label">Nome Completo:</label>
                    <input type="text" class="form-control" id="nome_completo" name="nome_completo" required>
                </div>

                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição:</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" required>
                </div>

                <div class="mb-3">
                    <label for="endereco" class="form-label">Cidade:</label>
                    <select class="form-select" id="endereco" name="endereco" required>
                        <option value="">Selecione a cidade</option>
                        <option value="Araraquara">Araraquara</option>
                        <option value="São Carlos">São Carlos</option>
                        <option value="Matão">Matão</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="foto_perfil" class="form-label">Foto de perfil / Logo:</label>
                    <input type="file" class="form-control" id="foto_perfil" name="foto_perfil" accept="image/png, image/jpeg" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
