
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String htmText = "<!DOCTYPE html>\r\n"
			+ "<html lang=\"en\">\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"utf-8\">\r\n"
			+ "<title>Insert title here</title>\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "	<h1>Result</h1>\r\n"
			+ "<p>\r\n"
			+ "Hello $$$"
			+ "</p>\r\n"
			+ "</body>\r\n"
			+ "</html>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Test.doGet()");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(htmText.replace("$$$", request.getParameter("user")));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Test.doPost()");
		doGet(request, response);
	}

}
