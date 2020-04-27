package ua.nure.biletska.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.biletska.web.command.admin.AddMedStaffCommand;
import ua.nure.biletska.web.command.admin.AddPatientCommand;
import ua.nure.biletska.web.command.admin.AdminPageCommand;
import ua.nure.biletska.web.command.admin.AdminShowListOfMedStaffCommand;
import ua.nure.biletska.web.command.admin.AdminShowListOfPatientsCommand;
import ua.nure.biletska.web.command.admin.OpenAddPatientFormCommand;
import ua.nure.biletska.web.command.admin.OpenMedStaffFormCommand;
import ua.nure.biletska.web.command.med.CompleteAssignmentCommand;
import ua.nure.biletska.web.command.med.DischargePatientCommand;
import ua.nure.biletska.web.command.med.MakeAnAppoinmentCommand;
import ua.nure.biletska.web.command.med.MedPageCommand;
import ua.nure.biletska.web.command.med.OpenMedicalCardCommand;
import ua.nure.biletska.web.command.med.OpenPatientCardCommand;
import ua.nure.biletska.web.command.med.OpenPatientListDocCommand;
import ua.nure.biletska.web.command.med.SetDiagnoseCommand;
import ua.nure.biletska.web.command.nurse.CompleteNurceAssignmentCommand;
import ua.nure.biletska.web.command.nurse.OpenNurseMedicalCardCommand;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("updateSettings", new UpdateSettingsCommand());
        commands.put("noCommand", new NoCommand());

        commands.put("download_file", new DownloadFileCommand());

        // admin commands
        commands.put("adminPage", new AdminPageCommand());
        commands.put("openAddPatientForm", new OpenAddPatientFormCommand());
        commands.put("openAddMedStaffForm", new OpenMedStaffFormCommand());
        commands.put("add_patient", new AddPatientCommand());
        commands.put("add_med_staff", new AddMedStaffCommand());
        commands.put("show_list_of_patiens", new AdminShowListOfPatientsCommand());
        commands.put("show_list_of_medical_staff", new AdminShowListOfMedStaffCommand());

        // med commands
        commands.put("openMedPage", new MedPageCommand());
        commands.put("show_list_of_patients_doc", new OpenPatientListDocCommand());
        commands.put("patient_card", new OpenPatientCardCommand());
        commands.put("add_assignment", new MakeAnAppoinmentCommand());
        commands.put("complete_assignment", new CompleteAssignmentCommand());
        commands.put("set_diagnose", new SetDiagnoseCommand());
        commands.put("discharged_patient", new DischargePatientCommand());
        commands.put("show_medical_card", new OpenMedicalCardCommand());

        // nurce
        commands.put("complete_nurce_assignment", new CompleteNurceAssignmentCommand());
        commands.put("open_nurce_page", new OpenNurseMedicalCardCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}

