package jp.co.aforce.Insert;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.co.aforce.Tool.Page;

@WebServlet(urlPatterns= {"/Insert/Insert"})
public class Insert extends HttpServlet{

	
	
	
	public void doPost(
			
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		
	
		Calendar cl=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yymmddhhmmss");
		String str="A"+sdf.format(cl.getTime());
		System.out.println(str);
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		int birthYear=Integer.parseInt(request.getParameter("birthYear"));
		int birthMonth=Integer.parseInt(request.getParameter("birthMonth"));
		int birthDay=Integer.parseInt(request.getParameter("birthDay"));
		

		
		
		
		Page.header(out);
		
		
		
		out.println("<p>"+"会員番号:"+str+"</p>");
		out.println("<p>名前:"+name+"</p>");
		out.println("<p>"+"年齢:"+age+"</p>");
		out.println("<p>"+"生年月日:"+birthYear+"/"+birthMonth+"/"+birthDay+"</p>");
		
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/database");
					Connection con=ds.getConnection();
					
					PreparedStatement st=con.prepareStatement(
							"insert into members values(?,?,?,?,?,?)"
							);
					st.setString(1,str);
					st.setString(2, name);
					st.setInt(3, age);
					st.setInt(4, birthYear);
					st.setInt(5, birthMonth);
					st.setInt(6, birthDay);
					
					int line=st.executeUpdate();
					
					if(line>0) {
						out.println("登録に成功しました");
					}else {
						out.println("登録に失敗しました");
					}
					
					st.close();
					con.close();
		}catch(Exception e) {
			e.printStackTrace(out);
		}
		
		Page.footer(out);
				
	}

}
