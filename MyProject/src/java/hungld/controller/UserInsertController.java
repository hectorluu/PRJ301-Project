/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.controller;

import hungld.user.UserDAO;
import hungld.user.UserDTO;
import hungld.user.UserInsertErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserInsertController", urlPatterns = {"/UserInsertController"})
public class UserInsertController extends HttpServlet {

    private static final String ERROR = "insert.jsp";
    private static final String SUCCESS = "login.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = false;
        UserInsertErrorDTO error = new UserInsertErrorDTO("", "", "", "", "");
        try {
            String userID = request.getParameter("userID");
            String fullname = request.getParameter("fullname");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            if (userID.length() < 3 || userID.length() > 10) {
                check = true;
                error.setUserIDError("UserID must be in [3,10]");
            }
            if (fullname.length() < 5 || fullname.length() > 50) {
                check = true;
                error.setFullnameError("Full name must be in [5,50]");
            }
            if (roleID.length() < 2 || roleID.length() > 10) {
                check = true;
                error.setRoleIDError("roleID must be in [2,10]");
            }
            if (!confirm.equals(password)) {
                check = true;
                error.setConfirmError("Password not match!!!");
            }
            if (check) {
                request.setAttribute("ERROR_USER", error);
            } else {
                UserDAO dao = new UserDAO();

                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    error.setUserIDError("UserID is existed.");
                    request.setAttribute("ERROR_USER", error);
                } else {
                    UserDTO user = new UserDTO(userID, fullname, roleID, password);
                    dao.insertUser(user);
                    url = SUCCESS;
                }
            }
        } catch (Exception ex) {
            log("Exception at _ UserInsertController :" + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
