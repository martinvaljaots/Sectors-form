package form.servlets;

import form.database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submit")
public class FormSubmitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String[] sectors = request.getParameterValues("sectors[]");
        boolean agreeToTerms = Boolean.parseBoolean(request.getParameter("agree"));

        DatabaseConnection dbc = new DatabaseConnection();
        dbc.insertOrUpdateDatabaseSubmission(name, sectors, agreeToTerms, true);
    }
}
