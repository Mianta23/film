<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,com.example.film.model.*"%>
<%List<Tournage> tournage = (List<Tournage>)request.getAttribute("tournage");%>
<html>
  <head>
      <title>Title</title>
  </head>
  <body>
    <form action="<%= request.getContextPath()%>/newTournage" method="post">
      <input type="hidden" name="idfilm" value="<%=tournage.get(0).getFilm().getId()%>">
      <input type="text" name="tournage">
      <input type="datetime-local" name="datetournage">
      <input type="submit">
    </form>

    <table border="1">
      <tr>
        <td>Film</td>
        <td>Tournage</td>
        <td>Date</td>
        <td>Voir les scenes</td>
        <td>Planning</td>
      </tr>
      <%for(Tournage t: tournage){%>
        <tr>
          <td><%=t.getFilm().getFilm()%></td>
          <td><%=t.getTournage()%></td>
          <td><%=t.getDate()%></td>
          <td><a href="<%=request.getContextPath()%>/liste-scene?id=<%=t.getId()%>">voir details</a></td>
          <td><a href="<%=request.getContextPath()%>/planning?id=<%=t.getId()%>">Planning</a></td>
        </tr>
      <%}%>
    </table>
  </body>
</html>
