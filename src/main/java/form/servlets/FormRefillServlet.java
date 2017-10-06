package form.servlets;

import com.google.gson.Gson;
import form.database.DatabaseConnection;
import form.database.Submission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/refill")
public class FormRefillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseConnection dbc = new DatabaseConnection();
        Submission submission = dbc.retrieveUserSubmissionFromDatabase();
        Gson gson = new Gson();
        String jsonSubmission = gson.toJson(submission);
        log(jsonSubmission);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonSubmission);
    }
}
