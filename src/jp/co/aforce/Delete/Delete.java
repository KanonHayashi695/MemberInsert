package jp.co.aforce.Delete;
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
@WebServlet(urlPatterns= {"/Delete/Delete"})

public class Delete extends HttpServlet{
public void doPost(
			
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		
		String number=request.getParameter("member_no");
//		String name=request.getParameter("name");
//		int age=Integer.parseInt(request.getParameter("age"));
//		int birthYear=Integer.parseInt(request.getParameter("birthYear"));
//		int birthMonth=Integer.parseInt(request.getParameter("birthMonth"));
//		int birthDay=Integer.parseInt(request.getParameter("birthDay"));
		
		Page.header(out);
		
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/database");
					Connection con=ds.getConnection();
					
					PreparedStatement st=con.prepareStatement(
							"delete from members where member_no=?"
							);
					st.setString(1,number);
					int line=st.executeUpdate();
					
					if(line>0) {
						out.println("削除に成功しました");
					}else {
						out.println("削除に失敗しました");
					}
					
					st.close();
					con.close();
		}catch(Exception e) {
			e.printStackTrace(out);
		}
		
		Page.footer(out);
}

}
