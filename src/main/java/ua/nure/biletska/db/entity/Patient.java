package ua.nure.biletska.db.entity;

import java.sql.Date;

import ua.nure.biletska.db.DAO.DiagnosDAO;

public class Patient extends Entity {

	private static final long serialVersionUID = 8466257860830246236L;
	private String firstName;
	private String lastName;
	private int doctor_id;
	private Date dateOfBirth;
	private String telephoneNumber;
	private String email;
	private boolean isDischarged;
	private int diagnos_id;
	private int allPatient;
	//util param
	private String diagnoseName;

	public String getDiagnoseName() {
		if (diagnoseName == null) {
			return diagnoseName = new DiagnosDAO().getDiagnosById(diagnos_id).getDiagnosName();
		}
		return diagnoseName;
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

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDischarged() {
		return isDischarged;
	}

	public void setDischarged(boolean isDischarged) {
		this.isDischarged = isDischarged;
	}

	public int getDiagnos_id() {
		return diagnos_id;
	}

	public void setDiagnosId(int diagnos) {
		this.diagnos_id = diagnos;
	}
	
	
	@Override
	public String toString() {

		return "Patient ID: " + getId() + ", first name: " + firstName + ", last name: " + lastName + "doctor id: "
				+ doctor_id + ", date of birth: " + dateOfBirth.toString() + "telephon number: " + telephoneNumber
				+ ", email: " + email + ", discharged: " + isDischarged;
	}

}
