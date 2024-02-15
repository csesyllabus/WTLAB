import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Profile extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String id = req.getParameter("id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
            String sqlstmt = "select * from login where id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlstmt);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            int flag = 0;
            pw.println("<br><br><br>");
            while (rs.next()) {
                pw.println("<div align=\"center\">");
                pw.println("NAME: " + rs.getString(1) + "<br>");
                pw.println("ADDRESS: " + rs.getString(2) + "<br>");
                pw.println("PHONENO: " + rs.getString(3) + "<br>");
                pw.println("</div>");
                flag = 1;
            }
            if (flag == 0) {
                pw.println("SORRY INVALID ID. TRY AGAIN ID<br><br>");
                pw.println("<a href=\"/tr/profile.html\">Press HERE to RETRY</a>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
