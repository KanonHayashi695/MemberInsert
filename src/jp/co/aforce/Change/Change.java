package jp.co.aforce.Change;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.co.aforce.Tool.Page;

@WebServlet(urlPatterns={"/Change/Change"})

public class Change extends HttpServlet{
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		Page.header(out);
		
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/database"
					);
			Connection con=ds.getConnection();
			
			String member_no=request.getParameter("member_no");
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age"));
			int birthYear=Integer.parseInt(request.getParameter("birthYear"));
			int birthMonth=Integer.parseInt(request.getParameter("birthMonth"));
			int birthDay=Integer.parseInt(request.getParameter("birthDay"));
			
			PreparedStatement st=con.prepareStatement(
					"update members set name=? ,age=?, birth_year=?, birth_month=?, birth_day=? where member_no=?"
					);
			st.setString(1, name);
			st.setInt(2, age);
			st.setInt(3, birthYear);
			st.setInt(4, birthMonth);
			st.setInt(5,birthDay);
			st.setString(6, member_no);
			
			int line=st.executeUpdate();
			
			if(line>0) {
				out.println("変更に成功しました");
			}else {
				out.println("変更に失敗しました");
			}
			st.close();
			con.close();
		}catch(Exception e) {
				e.printStackTrace(out);
		}

			Page.footer(out);
		
	}

}
