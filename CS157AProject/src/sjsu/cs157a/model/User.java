package sjsu.cs157a.model;

import java.io.Serializable;

	public class User implements Serializable {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 11234567898764654L;
	    
	    private String StudentID;
	    private String firstName;
	    private String lastName;
	    private String phoneNo;
	    private String email;
	    private String password;
	    
	    
		public String getStudentID() {
			return StudentID;
		}
	
		public void setStudentID(String studentID) {
			this.StudentID = studentID;
		}
	
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
	
	    
	}


