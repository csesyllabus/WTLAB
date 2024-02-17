<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%
out.println("<html><body bgcolor=\"pink\">");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
    Statement stmt = con.createStatement();
    String sqlstmt = "SELECT id, password FROM login WHERE id='" + id + "' AND password='" + pwd + "'";
    ResultSet rs = stmt.executeQuery(sqlstmt);
    int flag = 0;
    while (rs.next()) {
        flag = 1;
    }
    if (flag == 0) {
        out.println("SORRY INVALID ID OR PASSWORD. TRY AGAIN ID<br><br>");
        out.println("<a href=\"/tr1/login.html\">press LOGIN to RETRY</a>");
    } else {
        out.println("VALID LOGIN ID<br><br>");
        out.println("<h3><ul>");
        out.println("<li><a href=\"profile.html\"><font color=\"black\">USER PROFILE</font></a></li><br><br>");
        out.println("<li><a href=\"catalog.html\"><font color=\"black\">BOOKS CATALOG</font></a></li><br><br>");
        out.println("<li><a href=\"order.html\"><font color=\"black\">ORDER CONFIRMATION</font></a></li><br><br>");
        out.println("</ul>");
    }
    out.println("</body></html>");
    con.close();
} catch (Exception e) {
    response.sendError(500, e.toString());
}
%> 
