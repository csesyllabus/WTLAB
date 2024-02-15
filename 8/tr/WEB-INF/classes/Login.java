import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
            Statement stmt = con.createStatement();
            String sqlStmt = "select id, password from login";
            ResultSet rs = stmt.executeQuery(sqlStmt);

            int flag = 0;

            while (rs.next()) {
                if (id.equals(rs.getString(1)) && pwd.equals(rs.getString(2))) {
                    flag = 1;
                }
            }

            if (flag == 0) {
                // College Information
                pw.println("Sai Spurthi Institute of Technolugy");
                pw.println("(Approved by AICTE, New Delhi, Affiliated to JNTUH, Hyderabad)");
                pw.println("Gangaram (Village), Sathupally (Mandal), Khammam Dist, TS-507303");
                pw.println("Accredited by NAAC");
            } else {
                pw.println("SORRY INVALID ID. TRY AGAIN ID<br><br>");
                pw.println("<a href=\"/tr/login.html\">Press LOGIN to RETRY</a><br><br>");
                pw.println("VALID LOGIN ID<br><br>");
                pw.println("<h3><ul>");
                pw.println("<li><a href=\"profile.html\"><font color=\"black\">USER PROFILE</font></a></li><br><br>");
                pw.println("<li><a href=\"catalog.html\"><font color=\"black\">BOOKS CATALOG</font></a></li><br><br>");
                pw.println(
                        "<li><a href=\"order.html\"><font color=\"black\">ORDER CONFIRMATION</font></a></li><br><br>");
            }

            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
