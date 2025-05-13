package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dao.ImagemPortfolioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prestador;
import utils.ConnectionFactory;

@WebServlet("/uploadPortfolio")
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
maxFileSize = 5 * 1024 * 1024,
maxRequestSize = 25 * 1024 * 1024)
public class UploadPortfolioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	String uploadPath = "C:\\uploads_portifolio_prestadores";
	List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Prestador prestador = (Prestador) request.getSession().getAttribute("usuarioLogado");
	    int idPrestador = prestador.getId_prestador();
	    List<String> imagensSalvas = new ArrayList<String>();
	    boolean salvouAlgumaImagem = false; 
	    
	    try {
	        Connection conn = ConnectionFactory.getConnection();
	        
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
	        Files.createDirectories(Paths.get(uploadPath));
	        
	        List<Part> arquivos = request.getParts().stream()
	                .filter(part -> "imagens".equals(part.getName()) && part.getSize() > 0)
	                .collect(Collectors.toList());
	        
	        for(Part part : arquivos) {
	            String contentType = part.getContentType();
	            
	            if(!tiposPermitidos.contains(contentType)) {
	                continue;
	            }
	            
	            String nomeArquivo = UUID.randomUUID().toString() + "_" + Paths.get(part.getSubmittedFileName()).getFileName().toString();
	            part.write(uploadPath + File.separator + nomeArquivo);
	            ImagemPortfolioDAO dao = new ImagemPortfolioDAO();
	            dao.salvarImagem(conn, idPrestador, nomeArquivo);
	            
	            imagensSalvas.add(nomeArquivo);
	            salvouAlgumaImagem = true; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Ocorreu um erro ao processar o upload: " + e.getMessage());
	    }
	    
	    String mensagem = salvouAlgumaImagem ? "Imagens enviadas com sucesso!" : "Nenhuma imagem válida foi enviada.";
	    request.getSession().setAttribute("mensagem", mensagem);
	    request.getSession().setAttribute("imagensSalvas", imagensSalvas); 
	    response.sendRedirect(request.getContextPath() + "/prestador/uploadPortfolio.jsp");
	}
	
}
