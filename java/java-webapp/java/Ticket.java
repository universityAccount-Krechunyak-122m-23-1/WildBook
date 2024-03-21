public class Ticket {
	enum Status {
		NEW, INPROGRESS, CLOSED
	}

	private static int counter;
	private int id;
	private Handler handler;
	private Status status = Status.NEW;

	public Ticket() {
		id = ++Ticket.counter;
	}

	public int getId() {
		return id;
	}

	public Handler getHandler() {
		return handler;
	}

	public Status status() {
		return status;
	}

	@Override
	public String toString() {
		return "Ticket #" + id + " " + status + " " + handler;
	}

	public void handle(Handler newHandler) {
		handler = newHandler;
		status = Status.INPROGRESS;
	}

	public void close() {
		status = Status.CLOSED;
	}
}
