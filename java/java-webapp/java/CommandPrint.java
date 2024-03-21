
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/print")
public class CommandPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "";
		ServiceDesk serviceDesk = ServiceDesk.getInsatnce();
		for (Ticket.Status status : Ticket.Status.values()) {
			result = result + serviceDesk.tickets(status);
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
