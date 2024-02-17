<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%
out.println("<html><body bgcolor=\"pink\">");
String id = request.getParameter("id");
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
    Statement stmt = con.createStatement();
    String sqlstmt = "SELECT * FROM login WHERE id='" + id + "'";
    ResultSet rs = stmt.executeQuery(sqlstmt);
    int flag = 0;
    while (rs.next()) {
        out.println("<div align=\"center\">");
        out.println("NAME: " + rs.getString(1) + "<br>");
        out.println("ADDRESS: " + rs.getString(2) + "<br>");
        out.println("PHONE NO: " + rs.getString(3) + "<br>");
        out.println("</div>");
        flag = 1;
    }
    if (flag == 0) {
        out.println("SORRY INVALID ID. TRY AGAIN ID <br><br>");
        out.println("<a href=\"/tr1/profile.html\">Press HERE to RETRY</a>");
    }
    out.println("</body></html>");
    con.close();
} catch (Exception e) {
    response.sendError(500, e.toString());
}
%>
