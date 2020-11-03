package sjsu.cs157a.model;

import java.io.Serializable;

public class UserLogin implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private String studentID;
	    private String password;
	    
		public String getStudentID() {
			return studentID;
		}
		public void setStudentID(String studentID) {
			this.studentID = studentID;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}


}
