<%
session.setAttribute("userid", null);
session.invalidate();
response.sendRedirect("index_test.jsp");
%>