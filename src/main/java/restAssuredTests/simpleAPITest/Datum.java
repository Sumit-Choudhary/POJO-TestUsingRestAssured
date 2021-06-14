package restAssuredTests.simpleAPITest;

public class Datum {

	private Integer id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Datum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("id");
		sb.append('=');
		sb.append(((this.id == null) ? "<null>" : this.id));
		sb.append(',');
		sb.append("email");
		sb.append('=');
		sb.append(((this.email == null) ? "<null>" : this.email));
		sb.append(',');
		sb.append("first_name");
		sb.append('=');
		sb.append(((this.first_name == null) ? "<null>" : this.first_name));
		sb.append(',');
		sb.append("last_name");
		sb.append('=');
		sb.append(((this.last_name == null) ? "<null>" : this.last_name));
		sb.append(',');
		sb.append("avatar");
		sb.append('=');
		sb.append(((this.avatar == null) ? "<null>" : this.avatar));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
