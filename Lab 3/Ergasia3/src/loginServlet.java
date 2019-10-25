

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/erg3_DB","root","wwwerg3");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='"+username+"'");
						
			if(rs.next()) {
				if(rs.getString(3).contentEquals(password)) {
					// Successful Login
					// Set request attribute for redirection
					request.setAttribute("username", username);
					
					con.close();
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/successfulLogin.jsp");
					dispatcher.forward(request, response);
				}else {
					// Wrong password
					con.close();
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failedLogin.jsp");
					dispatcher.forward(request, response);
				}
			}else {
				// User not found
				con.close();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failedLogin.jsp");
				dispatcher.forward(request, response);
			}
			
			
		}catch(Exception e) {
			out.println("There was an error connecting to the database.");
			out.println(e.toString());
			e.printStackTrace();
		}
		
	}

}
