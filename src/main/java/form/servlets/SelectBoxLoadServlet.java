package form.servlets;

import com.google.gson.Gson;
import form.database.DatabaseConnection;
import form.database.Option;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/loadSelectBox")
public class SelectBoxLoadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        DatabaseConnection dbc = new DatabaseConnection();
        List<Option> optionList;
        optionList = dbc.retrieveOptionsFromDatabase();
        Gson gson = new Gson();
        String jsonOptionList = gson.toJson(optionList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonOptionList);

    }
}
