package ua.nure.biletska.db;

import ua.nure.biletska.db.entity.PatientAssignment;

//Assignment status
public enum Status {
    IN_PROGRESS, COMPLETE;

    public static Status getStatus(PatientAssignment patientAssignment) {
        int statusId = patientAssignment.getAssignment_status_id();
        return Status.values()[statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
