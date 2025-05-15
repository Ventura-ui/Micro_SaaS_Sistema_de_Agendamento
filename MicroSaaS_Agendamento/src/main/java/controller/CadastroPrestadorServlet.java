package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import dao.PrestadorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prestador;
import utils.ConnectionFactory;

@WebServlet("/cadastroPrestador")
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
maxFileSize = 5 * 1024 * 1024,
maxRequestSize = 25 * 1024 * 1024)
public class CadastroPrestadorServlet extends HttpServlet{
	
	List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cadastroPrestador.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome_fantasia = request.getParameter("nome_fantasia");
		String nome_completo = request.getParameter("nome_completo");
		String descricao = request.getParameter("descricao");
		String endereco = request.getParameter("endereco") != null ? request.getParameter("endereco") : "";;
		Part fotoPerfil = request.getPart("foto_perfil");
		
		if (fotoPerfil == null || fotoPerfil.getSize() == 0) {
            throw new ServletException("Foto de perfil é obrigatória");
        }
		
		String contentType = fotoPerfil.getContentType();
		
		if(!tiposPermitidos.contains(contentType)) {
			throw new ServletException("Tipo de arquivo inválido: " + contentType);
		}
		
		String nomeArquivo = UUID.randomUUID().toString() + "_" + Paths.get(fotoPerfil.getSubmittedFileName()).getFileName().toString();
		String uploadPath = "C:\\uploads_prestadores";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdirs();
		}
		Files.createDirectories(Paths.get(uploadPath));
		fotoPerfil.write(uploadPath + File.separator + nomeArquivo);
		
		Prestador prestador = new Prestador();
		prestador.setEmail(email);
		prestador.setSenha(senha);
		prestador.setNome_fantasia(nome_fantasia);
		prestador.setNome_completo(nome_completo);
		prestador.setDescricao(descricao);
		prestador.setEndereco(endereco);
		prestador.setFoto_perfil(nomeArquivo);
		
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			PrestadorDAO dao = new PrestadorDAO();
			dao.cadastrarPrestador(conn, prestador);
			conn.close();
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (Exception e) {
			request.setAttribute("mensagem", "Erro ao realizar cadastro: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request, response);
		}
	}
}
