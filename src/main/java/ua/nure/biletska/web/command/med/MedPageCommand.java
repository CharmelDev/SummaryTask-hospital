package ua.nure.biletska.web.command.med;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.web.command.Command;

public class MedPageCommand extends Command {

    private static final long serialVersionUID = 6686202789697144487L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        return Path.PAGE__DOC;
    }
}

