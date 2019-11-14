<%@page import="www.erg4.UserDao"
import="javax.servlet.RequestDispatcher"%>  
      
<%  
String username = request.getParameter("username");
String password = request.getParameter("password");
int i=UserDao.login(username, password);  
if(i==0){
	// successful login
	request.setAttribute("username", username);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/successfulLogin.jsp");
	dispatcher.forward(request, response);
} else{
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failedLogin.jsp");
	dispatcher.forward(request, response);
}
      
%>  