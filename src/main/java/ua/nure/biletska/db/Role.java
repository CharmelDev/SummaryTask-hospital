package ua.nure.biletska.db;

import ua.nure.biletska.db.entity.MedicalStaff;

public enum Role {
    ADMIN, DOCTOR, NURSE;

    public static Role getRole(MedicalStaff med) {
        int roleID = med.getRoleId();
        return Role.values()[roleID];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
