package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.*;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletViewReqOU
 */
@WebServlet("/ServletViewReqOU")
public class ServletViewReqOU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletViewReqOU() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer result = 0;
	    String error = "";
	    String content = "";
	    String redirect = "";
	    ArrayList<String> nome=new ArrayList<String>();
	    ArrayList<String> cognome= new ArrayList<String>();
	   	if(Integer.parseInt(request.getParameter("flag"))==1) {
	   		
	   		RequestOU r= new RequestOU();
			RequestOUDAO rd= new RequestOUDAO();
			ArrayList<RequestOU> list= new ArrayList<RequestOU>();
		try{
			list=rd.doRetrieveAll();
			if(!list.isEmpty()) {
				result=1;
				for(RequestOU rq:list) {
					UserBean u= new UserBean();
					UserBeanDAO ud= new UserBeanDAO();
					u=ud.doRetrieveByEmail(rq.getEmail());
					nome.add(u.getNome());
					cognome.add(u.getCognome());
					
					content+= "<tr>";
					content+="<td><button>"+u.getCognome()+" "+ u.getNome()+"</button>";
					content+="<div class='b'> Informazioni <br>"+ u.getEmail()+ " </div>";
					content+="</td>";
					content+="</tr>";
				}
				
			}
			
			
		
			
	}catch(SQLException e) {
		// TODO Auto-generated catch block
		error="errore database";
		e.printStackTrace();
	}

}
	   	JSONObject res= new JSONObject();
	   	res.put("result", result);
	    res.put("error", error);
	    res.put("content", content);
	    res.put("redirect", redirect);
	    res.put("nome", nome);
	    res.put("cognome",cognome);
	    PrintWriter out = response.getWriter();
	    out.println(res);
	    response.setContentType("json");
	    System.err.println(res.toString());
	}
}
