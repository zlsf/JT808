package JT809govData.model;

/**
 * ƽ̨ID
 * 
 * @author cuillgln
 *
 */
public class GnssPlatformIdGov809 {

	private final long id;

	private GnssPlatformIdGov809(long id) {
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
		GnssPlatformIdGov809 other = (GnssPlatformIdGov809) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GnssPlatformId [id=" + id + "]";
	}

	public static GnssPlatformIdGov809 build(long id) {
		return new GnssPlatformIdGov809(id);
	}
}
