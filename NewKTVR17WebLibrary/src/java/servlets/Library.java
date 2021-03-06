/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import static entity.Reader_.city;
import static entity.Reader_.id;
import static entity.Reader_.phone;
import static entity.Reader_.surname;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.ReaderFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "Library", urlPatterns = {"/newBook", "/addBook","/newReader","/addReader"})
public class Library extends HttpServlet {

    @EJB BookFacade bookFacade;
    @EJB ReaderFacade readerFacade;

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
        request.setCharacterEncoding("UTF8");

        String path = request.getServletPath();
        if ("/newBook".equals(path)) {
            /*String textToPage = "Текст для вставки в страницу page1";
            request.setAttribute("textToPage", textToPage); // называеться как на страничке / как в java code содержимое этой переменной*/
            request.getRequestDispatcher("/WEB-INF/pages/newBook.jsp").forward(request, response);

        } else if ("/addBook".equals(path)) {

            String name = request.getParameter("book-name");
            String author = request.getParameter("author");
            String year = request.getParameter("year");
            String ISBN = request.getParameter("ISBN");

            Book book = new Book(name, author, new Integer(year), ISBN);
  
            bookFacade.create(book);
            
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/pages/page2.jsp").forward(request, response);
        }else if ("/newReader".equals(path)) {
            /*String textToPage = "Текст для вставки в страницу page1";
            request.setAttribute("textToPage", textToPage); // называеться как на страничке / как в java code содержимое этой переменной*/
            request.getRequestDispatcher("/WEB-INF/pages/newReader.jsp").forward(request, response);

        } else if ("/addReader".equals(path)) {

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phone = request.getParameter("phone");
            String city = request.getParameter("city");
            
            Reader reader=new Reader(name,surname,phone,city);
           
            request.setAttribute("reader", reader);
            readerFacade.create(reader);
            request.getRequestDispatcher("/WEB-INF/pages/page2.jsp").forward(request, response);
        }//отсюда создаются переменные в page2- ${reader.surname}
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
