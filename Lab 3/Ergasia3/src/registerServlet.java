

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		// TODO What happens when a user tries to register with an existing username
		// TODO Successful registration page or print? Same for login
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/erg3_DB","root","wwwerg3");
			Statement stmt = con.createStatement();
			
			// Set request attribute for redirection
			request.setAttribute("username", username);
			
			// Check whether the user already exists
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='"+username+"'");
			
			if(rs.next()) {
				// User already exists
				con.close();
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failedRegistration.jsp");
				dispatcher.forward(request, response);
				
			}else {		
				stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('"+username+"','"+password+"')");
				out.println("Your Record has been successfully inserted");
				
				con.close();
				
				// successful registration
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/successfulRegistration.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		}catch(Exception e) {
			out.println("There was an error connecting to the database.");
			out.println(e.toString());
			e.printStackTrace();
		}
		


		
	}

}
