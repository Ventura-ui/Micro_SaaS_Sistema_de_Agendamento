package controller.filtro;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/cliente/*","/prestador/*"})
public class SessionFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession sessao = httpRequest.getSession(false);
		
		if(sessao != null && sessao.getAttribute("usuarioLogado") != null) {
			chain.doFilter(httpRequest, response);
		}else {
			request.setAttribute("erro", "Não é possível acessar essa página sem estar logado!");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
