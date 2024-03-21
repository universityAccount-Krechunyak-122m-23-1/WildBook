import java.util.Objects;

public class Handler {
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
