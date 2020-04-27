package ua.nure.biletska.web.command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.CategoryDAO;
import ua.nure.biletska.db.DAO.MedicalStaffDAO;
import ua.nure.biletska.db.entity.Category;
import ua.nure.biletska.db.entity.MedicalStaff;
import ua.nure.biletska.web.command.Command;

public class AdminShowListOfMedStaffCommand extends Command {

    private static final long serialVersionUID = 4969236479946943478L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        // check the session
        if (request.getSession(false) == null) {
            errorMessage = "You are not register";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        // check the role
        if (request.getSession(false).getAttribute("medRole") == null
                || !request.getSession(false).getAttribute("medRole").equals(Role.ADMIN)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        List<MedicalStaff> medicalList = new MedicalStaffDAO().getDoctors();

        for (MedicalStaff medicalStaff : medicalList) {
            medicalStaff
                    .setCategoryName(new CategoryDAO().getCategoryById(medicalStaff.getCategoryId()).getCategoryName());
            medicalStaff.setNumberOfPatients(new MedicalStaffDAO().getCountOfPatientsForDoctorId(medicalStaff.getId()));
        }

        if (request.getParameter("sorting_order") == null
                || request.getParameter("sorting_order").equals("Sort Id")) {
            Collections.sort(medicalList, (MedicalStaff o1, MedicalStaff o2) -> (int) (o1.getId() - o2.getId()));
        } else if (request.getParameter("sorting_order").equals("Sort Name")) {
            Collections.sort(medicalList,
                    (MedicalStaff o1, MedicalStaff o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        } else if (request.getParameter("sorting_order").equals("Sort Specialization")) {
            Collections.sort(medicalList,
                    (MedicalStaff o1, MedicalStaff o2) -> o1.getCategoryName().compareTo(o2.getCategoryName()));

        } else if (request.getParameter("sorting_order").equals("Sort Number of pations")) {
            Collections.sort(medicalList,
                    (MedicalStaff o1, MedicalStaff o2) -> (int) (o1.getNumberOfPatients() - o2.getNumberOfPatients()));
        }
        List<Category> categoryList = new ArrayList<Category>();
        for (MedicalStaff medicalStaff : medicalList) {
            categoryList.add(new CategoryDAO().getCategoryById(medicalStaff.getCategoryId()));
        }
        request.setAttribute("med_staff_list", medicalList);
        request.setAttribute("category_list", categoryList);
        return Path.PAGE_MEDICAL_STAFF_LIST_ADMIN;
    }
}

