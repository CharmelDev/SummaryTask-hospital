import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

import ua.nure.biletska.db.DAO.PatientDAO;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.web.Controller;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServlet {

	@Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private PatientDAO patientDao;

    @InjectMocks
    private Controller servlet = new Controller();
	
    @Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }
    
    @Test
    public void shouldSetErrorAttributeByUnknnownCommand() throws ServletException, IOException {
        when(session.getAttribute("errorMessage")).thenReturn("No such command");
        servlet.doGet(request, response);
        verify(request, times(1)).setAttribute("errorMessage", "No such command");
        verify(requestDispatcher, times(1)).forward(request, response);
    }
    
    @Test
    public void shouldDoAdmisListOfMedicalStaffCommand() throws ServletException, IOException {
         when(session.getAttribute("medRole")).thenReturn("ADMIN");
         when(request.getParameter("command")).thenReturn("show_list_of_medical_staff");
         servlet.doGet(request, response);
         verify(requestDispatcher, times(1)).forward(request, response);
         verify(request, times(0)).setAttribute("errorMessage", "No such command");    
    }
    
    @Test
    public void shouldDoAdmisListOfPatientsCommand() throws ServletException, IOException {
    	 when(request.getParameter("command")).thenReturn("show_list_of_patiens");
    	 when(session.getAttribute("medRole")).thenReturn("ADMIN");
    	 when(patientDao.getPatients()).thenReturn(new ArrayList<Patient>());
    	 servlet.doGet(request, response);
    	 verify(requestDispatcher, times(1)).forward(request, response);
    	 verify(request, times(0)).setAttribute("errorMessage", "No such command"); 
    }
    
    @Test
    public void sholdDoAdmisAddMedicalStaffCommand() throws ServletException, IOException {
    	 when(request.getParameter("command")).thenReturn("add_med_staff");
    	 when(session.getAttribute("medRole")).thenReturn("ADMIN");
    	 when(request.getParameter("login")).thenReturn("login");
    	 when(request.getParameter("password")).thenReturn("password");
    	 when(request.getParameter("last_name")).thenReturn("lasName");
    	 when(request.getParameter("first_name")).thenReturn("firstName");
    	 when(request.getParameter("category")).thenReturn("travmatologist");
    	 when(request.getSession(false)).thenReturn(session);
    	 when(request.getParameter("role")).thenReturn("1");
    	 servlet.doGet(request, response);
    	 verify(requestDispatcher, times(1)).forward(request, response);
    	 verify(request, times(0)).setAttribute("errorMessage", "admin_jsp.errorInFirstName");
    	 verify(request, times(0)).setAttribute("errorMessage", "admin_jsp.errorInLastName");
    }
    
    @Test
    public void shoulDoAdminAddPatientCommand() throws ServletException, IOException {
    	when(request.getParameter("command")).thenReturn("add_patient");
   	 	when(session.getAttribute("medRole")).thenReturn("ADMIN");
   	 	when(request.getParameter("first_name")).thenReturn("firstName");
   	 	when(request.getParameter("last_name")).thenReturn("lastName");
   	 	when(request.getParameter("doctor")).thenReturn("2");
   	 	when(request.getParameter("telephon_number")).thenReturn("+380999999999");
   	 	when(request.getParameter("email")).thenReturn("mail@mail.mail");
   	 	servlet.doGet(request, response);
   	 	verify(requestDispatcher, times(1)).forward(request, response);
   	 	verify(request, times(0)).setAttribute("errorMessage", "You try to send form again");
    }
    
    @Test
    public void shouldNotDoOpenMedStaffCommand() throws ServletException, IOException {
    	when(request.getParameter("command")).thenReturn("openAddMedStaffForm");
    	when(session.getAttribute("medRole")).thenReturn("None");
    	when(session.getAttribute("recordSuccessful")).thenReturn(anyString());
    	when(request.getSession(false)).thenReturn(session);
    	servlet.doGet(request, response);
    	verify(request, times(1)).setAttribute("errorMessage", "Wrong priviliges");
        verify(requestDispatcher, times(1)).forward(request, response);
    }
    
    @Test
    public void shouldDoOpenMedStaffCommand() throws ServletException, IOException {
    	when(request.getSession(false)).thenReturn(session);
    	when(request.getParameter("command")).thenReturn("openAddMedStaffForm");
    	when(session.getAttribute("medRole")).thenReturn("ADMIN");
    	when(session.getAttribute("recordSuccessful")).thenReturn(anyString());
    	when(request.getSession(false).getAttribute("medRole")).thenReturn(anyString());
    	servlet.doGet(request, response);
        verify(requestDispatcher, times(1)).forward(request, response);
        verify(request, times(0)).setAttribute("errorMessage", "No such command");
    }
    
 }
