package jp.co.aforce.Delete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns= {"/Delete/Search"})
public class Search extends HttpServlet{
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/database"
					);
			Connection con=ds.getConnection();
			
			String number=request.getParameter("member_no"); //入力されたIDを取得
			
			
			PreparedStatement st=con.prepareStatement(
					"select * from members where member_no = ? ");
		
			st.setString(1,number);

			ResultSet rs=st.executeQuery();
			
			

			
			while(rs.next()) {
				System.out.println(rs.getString("member_no"));
				System.out.println(rs.getString("name"));
				request.setAttribute("number", rs.getString("member_no"));
				request.setAttribute("name", rs.getString("name"));
				request.setAttribute("age", String.valueOf(rs.getInt("age")));
				request.setAttribute("birthYear", String.valueOf(rs.getInt("birth_year")));
				request.setAttribute("birthMonth", String.valueOf(rs.getInt("birth_month")));
				request.setAttribute("birthDay", String.valueOf(rs.getInt("birth_day")));
			}
			
			request.getRequestDispatcher("../jsp/Delete.jsp").forward(request, response);
			st.close();
			con.close();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
