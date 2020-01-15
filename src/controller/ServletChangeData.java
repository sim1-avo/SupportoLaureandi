package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RequestCSDAO;
import org.json.simple.JSONObject;

/**
 * Consente alla segreteria e all'admin di modificare i dati inseriti in caso di errore.
 */
@WebServlet("/ServletChangeData")
public class ServletChangeData extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletChangeData() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    Integer result = 0;
    String error = "";
    String content = "";
    String redirect = "";

    RequestCSDAO rdao = new RequestCSDAO();

    if (Integer.parseInt(request.getParameter("flag")) == 1) {
      String nome = request.getParameter("newName");
      if (nome.length() == 0 || nome.length() > 20 || nome.matches(".*\\d+.*")) {
        error = "Formato nome non corretto";
        throw new IllegalArgumentException("Formato non corretto");
      }
      Integer id = Integer.parseInt(request.getParameter("id"));
      try {
        rdao.doChangeName(nome, id);
        content = "Modifica avvenuta con successo!";
        result = 1;
      } catch (SQLException e) {
        error = "Errore nel salvataggio della modifica";
        e.printStackTrace();
      }
    } else if (Integer.parseInt(request.getParameter("flag")) == 2) {
      String cognome = request.getParameter("newSurname");
      if (cognome.length() == 0 || cognome.length() > 20  || cognome.matches(".*\\d+.*")) { 
        error = "Formato cognome non corretto";
        throw new IllegalArgumentException("Formato non corretto");
      }

      Integer id = Integer.parseInt(request.getParameter("id"));
      try {
        rdao.doChangeSurname(cognome, id);
        content = "Modifica avvenuta con successo!";
        result = 1;
      } catch (Exception e) {
        error = "Errore nel salvataggio della modifica";
        e.printStackTrace();
      }
    } else {
      result = 0;
    }

    JSONObject res = new JSONObject();
    res.put("redirect", redirect);
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    PrintWriter out = response.getWriter();
    out.println(res);
    response.setContentType("json");
  }
}
