<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
out.println("<html><body bgcolor=\"pink\">");
String name = request.getParameter("name");
String addr = request.getParameter("addr");
String no = request.getParameter("phno");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
//int no = Integer.parseInt(phno);
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wt", "root", "");
    Statement stmt = con.createStatement();
    String checkIdQuery = "SELECT id FROM login WHERE id='" + id + "'";
    ResultSet rs = stmt.executeQuery(checkIdQuery);
    int flag = 0;
    while (rs.next()) {
        flag = 1;
    }
    if (flag == 1) {
        out.println("SORRY LOGIN ID ALREADY EXISTS. TRY AGAIN WITH A NEW ID<br><br>");
        out.println("<a href=\"/tr1/reg.html\">Press REGISTER to RETRY</a>");
    } else {
        Statement stmt1 = con.createStatement();
        String insertQuery = "INSERT INTO login VALUES('" + name + "','" + addr + "'," + no + ",'" + id + "','" + pwd + "')";
        stmt1.executeUpdate(insertQuery);
        out.println("YOUR DETAILS ARE ENTERED <br><br>");
        out.println("<a href=\"/tr1/login.html\">Press LOGIN to login</a>");
    }
    out.println("</body></html>");
    con.close();
} catch (Exception e) {
    response.sendError(500, e.toString());
}
%>
