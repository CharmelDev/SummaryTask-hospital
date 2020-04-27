package ua.nure.biletska.db;

import ua.nure.biletska.db.entity.PatientAssignment;

public enum Assignment {
    PROCEDURE, PILLS, OPERATION;

    public static Assignment getAssignment(PatientAssignment patientAssignment) {
        int assignmentID = patientAssignment.getAssignment_id();
        return Assignment.values()[assignmentID];
    }

    public static int getIndex(String assignment) {
        for (int i = 0; i < Assignment.values().length; i++) {
            if (assignment.equalsIgnoreCase((String) Assignment.values()[i].getName()))
                return i;
        }
        return -1;
    }

    public String getName() {
        return name().toLowerCase();
    }
}

