
public class Star {
	private String stagename;
	private String firstname;
	private String lastname;
	private String dob;
	
	public Star(){}
	
	public Star(String stagename, String firstname, String lastname, String dob)
	{
		this.stagename = stagename;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
	}
	
	public String getStagename() {
		return stagename;
	}
	public void setStagename(String stagename) {
		this.stagename = stagename;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Star Details - ");
		sb.append("Stagename:" + getStagename());
		sb.append(", First:" + getFirstname());
		sb.append(", Last:" + getLastname());
		sb.append(", DOB:" + getDob());
		sb.append(".");
		
		return sb.toString();
	}
}
