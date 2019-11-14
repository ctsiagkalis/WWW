<%@page import="www.erg4.UserDao" 
import="javax.servlet.RequestDispatcher"%>  
<jsp:useBean id="obj" class="www.erg4.User">  
</jsp:useBean>  
<jsp:setProperty property="*" name="obj"/>  
      
<%  
System.out.println("********obj"+obj.getUsername()+":"+obj.getPassword());
int i=UserDao.register(obj);  
if(i==0){
	// successful registration
	request.setAttribute("username", obj.getUsername());
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/successfulRegistration.jsp");
	dispatcher.forward(request, response);
} else{
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failedRegistration.jsp");
	dispatcher.forward(request, response);
}   
%>  