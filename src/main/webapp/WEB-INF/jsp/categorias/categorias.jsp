<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>Categorías</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/categoriaStyles.css">
</head>
<body>
<% List<Categoria> listCat = (List<Categoria>) request.getAttribute("listaCat");

%>
<%@include file="../../componentes/nav.jspf" %>
<div class="container">
    <form action="${pageContext.request.contextPath}/tienda_animales/categorias/crear">
        <input type="submit" value="Crear Categoría" class="button">
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <%
            if (listCat != null) {
                for (Categoria categoria : listCat) {


        %>
        <tbody>
        <tr>
            <td><%=categoria.getIdCategoria()%>
            </td>
            <td><%=categoria.getNombre()%>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/categorias/<%=categoria.getIdCategoria()%>">
                    <input type="submit" value="Editar" class="button">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/categorias/borrar" method="post">
                    <input type="submit" value="Borrar" class="button">
                    <input type="hidden" name="__method__" value="delete">
                    <input type="hidden" name="idCategoria" value="<%=categoria.getIdCategoria()%>">
                </form>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <%} else {%>
    <h2>No hay categorias registradas</h2>
    <%}%>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
