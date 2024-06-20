<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<div class="container-registro">

    <h1>Login Usuario</h1>
    <form action="${pageContext.request.contextPath}/tienda_animales/login/" method="post" class="formulario">
        <div class="wrapper-formulario">
            <label>Email</label>
            <input class="input-text" type="email" name="email" required>

            <label>Password</label>
            <input class="input-text" type="password" name="password" required>
            <input type="submit" value="Login" class="button">
            <%
                String errorLogin = (String) request.getAttribute("errorLogin");
                if (errorLogin != null) {%>
            <div style="color: red"><%=errorLogin%>
            </div>
            <%}%>
        </div>
    </form>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>