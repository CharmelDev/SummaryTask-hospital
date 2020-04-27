package ua.nure.biletska.web.command;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Write file command.
 */
public class DownloadFileCommand extends Command {

    private static final long serialVersionUID = -7672039037483101596L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("download command");
        response.setContentType("application/txt");
        response.addHeader("Content-Disposition", "attachment; filename=" + "text.txt");
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File text = new File("C:\\Users\\User\\IdeaProjects\\hospitall\\text.txt");

            FileInputStream input = new FileInputStream(text);
            buf = new BufferedInputStream(input);
            int readBytes = 0;

            while ((readBytes = buf.read()) != -1) {
                stream.write(readBytes);
            }

        } catch (IOException e) {
            throw new ServletException(e.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }

            if (buf != null) {
                buf.close();
            }
        }
        return null;
    }
}

