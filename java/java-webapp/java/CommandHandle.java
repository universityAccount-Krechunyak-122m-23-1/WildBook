
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/handle")
public class CommandHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "";
		ServiceDesk serviceDesk = ServiceDesk.getInsatnce();

		String param = request.getParameter("handler");
		try {
			int handlerId = Integer.valueOf(param);
			Handler handler = serviceDesk.handlers().stream().filter(x -> x.getId() == handlerId).findAny()
					.orElse(null);
			handler = serviceDesk.handle(handler);
			if (handler != null) {
				result = "handler: " + handler;
			} else {
				result = "Unknown handler: " + param + " or queue is empty";
			}
		} catch (Exception e) {
			result = "Invalid handler ID: " + param;
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
