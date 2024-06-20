<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Usuarios</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<div class="container">
    <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/crear">
        <input type="submit" value="Crear Usuario" class="button">
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>DNI</th>
            <th>DOMICILIO</th>
            <th>FECHA DE NACIMIENTO</th>
            <th>EMAIL</th>
            <th>PASSWORD</th>
            <th>ROL</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <% List<Usuario> listUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
            if (listUsuarios != null) {
                for (Usuario usuario : listUsuarios) {
        %>
        <tr>
            <td><%=usuario.getIdUsuario()%>
            </td>
            <td><%=usuario.getNombre()%>
            </td>
            <td><%=usuario.getApellido()%>
            </td>
            <td><%=usuario.getDni()%>
            </td>
            <td><%=usuario.getDomicilio()%>
            </td>
            <td><%=usuario.getFechaDeNacimiento()%>
            </td>
            <td><%=usuario.getEmail()%>
            </td>
            <td><%=usuario.getPassword().substring(0, 5)%>
            </td>
            <td><%=usuario.getRol()%>
            </td>

<%--            <td>--%>
<%--                <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/<%=usuario.getIdUsuario()%>">--%>
<%--                    <input type="submit" value="Ver Detalle" class="button">--%>
<%--                </form>--%>
<%--            </td>--%>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/editar/<%=usuario.getIdUsuario()%>">
                    <input type="submit" value="Editar Usuario" class="button">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/borrar" method="post">
                    <input type="submit" value="Borrar Usuario" class="button">
                    <input type="hidden" name="__method__" value="delete">
                    <input type="hidden" name="idUsuario" value="<%=usuario.getIdUsuario()%>">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>

        <h2>No hay usuarios registrados</h2>

        <%}%>
        </tbody>
    </table>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
