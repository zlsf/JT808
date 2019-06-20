package JT809Data.model;

/**
 * 平台ID
 * 
 * @author cuillgln
 *
 */
public class GnssPlatformId {

	private final long id;

	private GnssPlatformId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GnssPlatformId other = (GnssPlatformId) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GnssPlatformId [id=" + id + "]";
	}

	public static GnssPlatformId build(long id) {
		return new GnssPlatformId(id);
	}
}
