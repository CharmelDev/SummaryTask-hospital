package ua.nure.biletska.db.entity;

public class Diagnos extends Entity {

	private static final long serialVersionUID = 8466257860808349874L;
	private String diagnosName;

	public String getDiagnosName() {
		return diagnosName;
	}

	public void setDiagnosName(String diagnosName) {
		this.diagnosName = diagnosName;
	}

	@Override
	public String toString() {
		return "Diagnos id: " + getId() + ", diagnos name: " + diagnosName;
	}
}
