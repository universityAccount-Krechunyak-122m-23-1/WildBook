package a03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Handler {
	private static int counter;
	private int id;

	public Handler() {
		id = ++counter;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Handler #" + id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Handler other = (Handler) obj;
		return id == other.id;
	}
}

class Ticket {
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

class ServiceDesk {
	private List<Ticket> tickets = new ArrayList<Ticket>();

	private List<Handler> handlers = new ArrayList<Handler>();

	private static ServiceDesk instance;

	private ServiceDesk() {
		super();
	}

	public static ServiceDesk getInsatnce() {
		if (instance == null) {
			instance = new ServiceDesk();
		}
		return instance;
	}

	public Handler addHandler() {
		Handler handler = new Handler();
		handlers.add(handler);

		System.out.println("ServiceDesk.addHandler(), " + handler);
		return handler;
	}

	public Handler checkHandler(int id) {
		return handlers.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}

	public List<Handler> handlers() {
		return handlers;
	}

	public Ticket addTicket() {
		Ticket ticket = new Ticket();
		tickets.add(ticket);

		System.out.println("ServiceDesk.add(), " + ticket);
		return ticket;
	}

	public Handler handle(Handler handler) {
		Handler result = null;
		if (handler != null) {
			for (Ticket ticket : tickets) {
				if (ticket.status() == Ticket.Status.NEW) {
					ticket.handle(handler);
					result = handler;
					break;
				}
			}
		}

		System.out.println("ServiceDesk.handle(), " + handler);
		return result;
	}

	public void close(Handler handler) {
		for (Ticket ticket : tickets) {
			if (ticket.getHandler() == handler && ticket.status() == Ticket.Status.INPROGRESS) {
				ticket.close();
				break;
			}
		}

		System.out.println("ServiceDesk.close(), " + handler);
	}

	public List<Ticket> tickets(Ticket.Status status) {
		return tickets.stream().filter(x -> x.status() == status).collect(Collectors.toList());
	}

	public List<Ticket> tickets(Handler handler) {
		return tickets.stream().filter(x -> x.getHandler() == handler && x.status() == Ticket.Status.INPROGRESS)
				.collect(Collectors.toList());
	}

	public Ticket checkTicket(int id) {
		return tickets.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}

	public void print() {
		System.out.println("Tickets:");
		if (tickets == null || tickets.size() == 0) {
			System.out.println("<EMPTY>");
		} else {
			for (Ticket ticket : tickets) {
				System.out.println(ticket);
			}
		}
		System.out.println("---");
	}
}

public class Demo {

	public static void main(String[] args) {
		System.out.println("Begin...");

		ServiceDesk desk = ServiceDesk.getInsatnce();
		desk.print();
		desk.addTicket(); // 1
		desk.addTicket(); // 2
		desk.addTicket(); // 3
		desk.print();

		Handler h1 = new Handler();
		desk.handle(h1);
		desk.print();

		Handler h2 = new Handler();
		desk.addTicket(); // 4
		desk.handle(h1);
		desk.handle(h2);
		desk.addTicket(); // 5
		desk.addTicket(); // 6
		desk.print();

		System.out.println(desk.tickets(Ticket.Status.CLOSED));
		System.out.println(desk.tickets(Ticket.Status.NEW));
		System.out.println(desk.tickets(Ticket.Status.INPROGRESS));
		System.out.println("---");

		System.out.println(desk.tickets(h1));
		System.out.println(desk.tickets(h2));
		System.out.println("---");

		System.out.println(desk.checkTicket(1));
		System.out.println(desk.checkTicket(2));
		System.out.println(desk.checkTicket(999));
		System.out.println("---");

		desk.close(h1);
		desk.print();

		System.out.println(desk.tickets(Ticket.Status.CLOSED));
		System.out.println(desk.tickets(Ticket.Status.NEW));
		System.out.println(desk.tickets(Ticket.Status.INPROGRESS));
		System.out.println("---");

		System.out.println("Done.");
	}

}
