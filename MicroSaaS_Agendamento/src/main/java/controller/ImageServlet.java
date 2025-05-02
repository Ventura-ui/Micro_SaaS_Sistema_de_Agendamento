package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imagensPrestadores/*")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String basePathPortfolio = "C:\\uploads_portifolio_prestadores\\";
    private final String basePathPrestadores = "C:\\uploads_prestadores\\";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageName = request.getPathInfo(); 
        if (imageName == null || imageName.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Imagem não especificada");
            return;
        }

        imageName = imageName.substring(1); 

        File imageFile = new File(basePathPortfolio, imageName);
        if (!imageFile.exists()) {
            imageFile = new File(basePathPrestadores, imageName);
            if (!imageFile.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem não encontrada");
                return;
            }
        }

        String contentType = Files.probeContentType(imageFile.toPath());
        response.setContentType(contentType);
        Files.copy(imageFile.toPath(), response.getOutputStream());
    }
}

