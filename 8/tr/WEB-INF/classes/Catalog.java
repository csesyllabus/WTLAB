import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Catalog extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String title = req.getParameter("title");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
            String sqlstmt = "select title, author, publisher, cost from books where title = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlstmt);
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            int flag = 0;
            while (rs.next()) {
                pw.println("<div align=\"center\">");
                pw.println("TITLE: " + rs.getString("title") + "<br>");
                pw.println("AUTHOR: " + rs.getString("author") + "<br>");
                pw.println("PUBLISHER: " + rs.getString("publisher") + "<br>");
                pw.println("COST: " + rs.getString("cost") + "<br>");
                pw.println("</div>");
                flag = 1;
            }
            if (flag == 0) {
                pw.println("SORRY INVALID TITLE. TRY AGAIN <br><br>");
                pw.println("<a href=\"/tr/catalog.html\">Press HERE to RETRY</a>");
            }
            pw.println("</body></html>");
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
