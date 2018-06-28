/*
Name: Jeremy Moore and Alec Maly
Net ID: jmoore28 and amaly1
COSC 311 Course Project
*/

package pro.jmoore.stablemarriage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        // IF DRIVER CLASS ISN'T IN SESSION OBJECT,
        // CREATE NEW DRIVER OBJECT AND STORE IN SESSION OBJECT
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute("drivers");
        
        // PROCESS WHICH BUTTON WAS PUSHED
        String action = request.getParameter("action");
        action = (driver == null) ? "reset" : action;
        switch (action) {
            case "reset":
                driver = new Driver(5);
                session.setAttribute("drivers", driver);
                break;
            case "next":
                driver.round();
                //driver.roundWomen();
                break;
            case "step":
                driver.step();
                //driver.stepWomen();
                break;
        }
        try {
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
