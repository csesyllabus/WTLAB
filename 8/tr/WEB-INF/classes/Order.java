import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Order extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body bgcolor=\"pink\">");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String title = req.getParameter("title");
        String count1 = req.getParameter("no");
        String date = req.getParameter("date");
        String cno = req.getParameter("cno");
        int count = Integer.parseInt(count1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");

            // Check login credentials
            Statement stmt = con.createStatement();
            String loginQuery = "SELECT id, password FROM login WHERE id='" + id + "' AND password='" + pwd + "'";
            ResultSet rs = stmt.executeQuery(loginQuery);

            int flag = 0, amount, x;
            while (rs.next()) {
                flag = 1;
            }
            if (flag == 0) {
                pw.println("SORRY INVALID ID OR PASSWORD. TRY AGAIN<br><br>");
                pw.println("<a href=\"/tr/order.html\">Press HERE to RETRY</a>");
            } else {
                // Fetch cost of the book
                Statement stmt2 = con.createStatement();
                String bookQuery = "SELECT cost FROM book WHERE title='" + title + "'";
                ResultSet rs1 = stmt2.executeQuery(bookQuery);
                int flag1 = 0;
                while (rs1.next()) {
                    flag1 = 1;
                    x = Integer.parseInt(rs1.getString("cost"));
                    amount = count * x;
                    pw.println("AMOUNT: " + amount + "<br><br><br><br>");
                    // Insert into details table
                    Statement stmt1 = con.createStatement();
                    String insertQuery = "INSERT INTO details VALUES('" + id + "','" + title + "'," + amount + ",'"
                            + cno + "')";
                    stmt1.executeUpdate(insertQuery);
                    pw.println("YOUR ORDER has been taken<br>");
                }
                if (flag1 == 0) {
                    pw.println("SORRY INVALID BOOK TITLE. TRY AGAIN<br><br>");
                    pw.println("<a href=\"/tr/order.html\">Press HERE to RETRY</a>");
                }
            }
            pw.println("</body></html>");
            con.close();
        } catch (Exception e) {
            resp.sendError(500, e.toString());
        }
    }
}
