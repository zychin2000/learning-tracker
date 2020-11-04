package sjsu.cs157a.models;

import java.io.Serializable;

	public class User implements Serializable {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 11234567898764654L;
	    
	    private String userID;
	    private String firstName;
	    private String lastName;
	    private String phoneNo;
	    private String email;
	    private String password;

		public User(String userID, String firstName, String lastName, String phoneNo, String email, String password) {
			this.userID = userID;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNo = phoneNo;
			this.email = email;
			this.password = password;
		}


		public String getUserID() {
			return userID;
		}
	
		public void setUserID(String userID) {
			this.userID = userID;
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


