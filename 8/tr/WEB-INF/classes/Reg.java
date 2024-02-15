import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Reg extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String name = req.getParameter("name");
        String addr = req.getParameter("addr");
        String phno = req.getParameter("phno");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        int no = Integer.parseInt(phno);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
            Statement stmt = con.createStatement();
            String sqlstmt = "select id, password from login";
            ResultSet rs = stmt.executeQuery(sqlstmt);
            int flag = 0;
            while (rs.next()) {
                if (id.equals(rs.getString(1)) &&
                        pwd.equals(rs.getString(2))) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                pw.println("SORRY INVALID ID ALREADY EXISTS. TRY AGAIN WITH A NEW ID <br> <br> ");
                pw.println("<a href='/tr/reg.html'>Press REGISTER to RETRY </a>");
            } else {
                Statement stmt1 = con.createStatement();
                stmt1.executeUpdate("insert into login values('" + name + "','" + addr + "'," + no + ",'" + id + "','" + pwd + "')");
                pw.println("YOUR DETAILS ARE ENTERED <br><br>");
                pw.println("<a href=\"/tr/login.html\">Press LOGIN to login </a>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}