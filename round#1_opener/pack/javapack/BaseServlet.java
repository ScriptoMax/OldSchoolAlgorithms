package javapack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class BaseServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/")) {
			request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
		} else if(path.equals("/welcome")) {
			request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
		}
	}

}
