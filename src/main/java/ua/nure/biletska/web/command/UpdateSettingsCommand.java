package ua.nure.biletska.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.web.command.nurse.OpenNurseMedicalCardCommand;

/**
 * i18n command
 * @author Olha Biletska
 *
 */
public class UpdateSettingsCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        String localeToSet = request.getParameter("localeToSet");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }
        log.debug("Command finished");
        if (request.getSession(false).getAttribute("medRole").equals(Role.ADMIN)) {
            return Path.PAGE__ADMIN;
        }
        if (request.getSession(false).getAttribute("medRole").equals(Role.DOCTOR)) {
            return Path.PAGE__DOC;
        }
        if (request.getSession(false).getAttribute("medRole").equals(Role.NURSE)) {
            return new OpenNurseMedicalCardCommand().execute(request, response);
        }
        return Path.PAGE__ERROR_PAGE;
    }
}

