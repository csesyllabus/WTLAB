<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%
out.println("<html><body bgcolor=\"pink\">");
String title = request.getParameter("title");
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
    Statement stmt = con.createStatement();
    String sqlstmt = "SELECT * FROM book WHERE title='" + title + "'";
    ResultSet rs = stmt.executeQuery(sqlstmt);
    int flag = 0;
    while (rs.next()) {
        out.println("<div align=\"center\">");
        out.println("TITLE: " + rs.getString(1) + "<br>");
        out.println("AUTHOR: " + rs.getString(2) + "<br>");
        out.println("VERSION: " + rs.getString(3) + "<br>");
        out.println("PUBLISHER: " + rs.getString(4) + "<br>");
        out.println("COST: " + rs.getString(5) + "<br>");
        out.println("</div>");
        flag = 1;
    }
    if (flag == 0) {
        out.println("SORRY INVALID TITLE. TRY AGAIN <br><br>");
        out.println("<a href=\"/tr1/catalog.html\">Press HERE to RETRY</a>");
    }
    out.println("</body></html>");
    con.close();
} catch (Exception e) {
    response.sendError(500, e.toString());
}
%>
