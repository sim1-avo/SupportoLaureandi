package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Statement;

import controller.DbConnection;
public class RequestlmDAO {

	
	static final String TABLE_NAME = "requestlm";
	
	public synchronized int doSave(RequestLM r) throws SQLException {

		Connection connection = new DbConnection().getInstance().getConn();
		PreparedStatement preparedStatement = null;
		Integer idRequest = 0;
		String insertSQL = "insert into " + RequestlmDAO.TABLE_NAME
				+ " (curriculum, anno, fk_user) values (?, ?, ?)";

		try {
			/*
			 *                 Integer idRequest = 0;

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                  idRequest = rs.getInt(1);
                }
                result = 1;
                rs.close();
              } else {
                error = "Impossibile presentare la richiesta.";
              }*/
			preparedStatement = connection.prepareStatement(insertSQL,preparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, r.getCurr());
			preparedStatement.setInt(2, r.getYear());
			preparedStatement.setString(3, r.getEmail());
			
			
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
              idRequest = rs.getInt(1);
            }
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.commit();;
			}
		}
		return idRequest;
	}
	
	
	  public void doSaveSelection(RequestLM r) {
	  }
	
	  
	  // commento da antonio non l'ho modificata quindi non funziona ma non serve :: da eliminare
	  public synchronized ArrayList<RequestLM> doRetrieveAll() throws SQLException {
		  Connection connection = new DbConnection().getInstance().getConn();
			PreparedStatement preparedStatement = null;

			ArrayList<RequestLM> listaReq = new ArrayList<RequestLM>();

			String selectSQL = "SELECT * FROM requestlm";
			
			

			try {
				//connection = DbConnection.getInstance().getConn();
				preparedStatement = connection.prepareStatement(selectSQL,preparedStatement.RETURN_GENERATED_KEYS);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RequestLM bean = new RequestLM();
					
					bean.setId(rs.getInt("id_req"));
					bean.setCurr(rs.getString("curriculum"));
					bean.setYear(rs.getInt("anno"));
					bean.setEmail(rs.getString("fk_user"));
					
					listaReq.add(bean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					//if (connection != null)
				//		connection.close();
				}
			}
			return listaReq;
		}

	  
	  public synchronized ArrayList<RequestLM> doCountByYear(int anno) throws SQLException {
		ArrayList<RequestLM> listbean = new ArrayList<RequestLM>();
	  	Connection conn = new DbConnection().getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "select curriculum, COUNT(*) as count FROM " ;
		selectSQL+= RequestlmDAO.TABLE_NAME + " where anno = ? group by curriculum";
		

		try {
			//connection = DbConnection.getInstance().getConn();
			preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, anno);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RequestLM bean = new RequestLM();
				bean.setCurr(rs.getString("curriculum"));
				bean.setCount(rs.getInt("count"));
				
				listbean.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (conn != null)
					conn.commit();
			}
		}
		
		return listbean;  
	  }
	  
	  
	  public synchronized ArrayList<RequestLM> doRetrieveByYear(int anno) throws SQLException {
		  	Connection conn = new DbConnection().getInstance().getConn();
			PreparedStatement preparedStatement = null;

			
			ArrayList<RequestLM> listbean = new ArrayList<RequestLM>();

			String selectSQL = "select * from " + RequestlmDAO.TABLE_NAME + " where anno = ?";
			

			try {
				//connection = DbConnection.getInstance().getConn();
				preparedStatement = conn.prepareStatement(selectSQL,preparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, anno);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RequestLM bean = new RequestLM();
					bean.setId(rs.getInt("id_req"));
					bean.setCurr(rs.getString("curriculum"));
					bean.setYear(rs.getInt("anno"));
					bean.setEmail(rs.getString("fk_user"));
					
					listbean.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (conn != null)
						conn.commit();
				}
			}
			return listbean;
		}
	
	
}