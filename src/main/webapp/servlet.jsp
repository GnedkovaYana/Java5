<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    String path = request.getParameter("path");
    if (path == null) {
        path = "C:\\";
    }
    File currentDirectory = new File(path);
    String parentDirectoryPath = currentDirectory.getParent();

    if (parentDirectoryPath == null) {
        parentDirectoryPath = "C:\\";
    }
%>
<html>
<head>
    <title>Files</title>
</head>
<body>

<%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date())%>
<h1><%=path%></h1>
<p>
    <td><a href="?path=<%=java.net.URLEncoder.encode(parentDirectoryPath, "UTF-8")%>">Вверх</a></td>
</p>
<table>
    <tr>
        <th><b>Файл</b></th>
        <th><b>Размер</b></th>
        <th><b>Дата</b></th>
    </tr>
    <%
        File[] directories = (File[]) request.getAttribute("directories");
        for (File directory : directories) {
    %>
    <tr>
        <th><a href="?path=<%=java.net.URLEncoder.encode(directory.getAbsolutePath(), "UTF-8")%>"><%=directory.getName() + "/"%></a></th>
        <th></th>
        <th><%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(directory.lastModified()))%></th>
    </tr>
    <%}%>
    <%
        File[] files = (File[]) request.getAttribute("files");
        for (File file : files) {
    %>
    <tr>
        <th><a href="download?path=<%=java.net.URLEncoder.encode(file.getAbsolutePath(), "UTF-8")%>"><%=file.getName()%></a></th>
        <th><%=file.length() + " B"%></th>
        <th><%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(file.lastModified()))%></th>
    </tr>
    <%}%>
</table>
</body>
</html>