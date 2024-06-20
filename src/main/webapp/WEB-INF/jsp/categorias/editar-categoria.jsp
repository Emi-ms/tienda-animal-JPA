<%@ page import="org.iesbelen.model.Categoria" %><%--
  Created by IntelliJ IDEA.
  User: EMI
  Date: 26/11/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Categoria</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/categoriaStyles.css">
</head>
<body>

<div class="container-crear">
    <h1>Editar Categoría</h1>
    <% Categoria categoria = (Categoria) request.getAttribute("categoria");%>

    <form action="${pageContext.request.contextPath}/tienda_animales/categorias/editar" method="post">
        <input type="hidden" name="__method__" value="edit">
        <input type="hidden" name="idCategoria" value="<%=categoria.getIdCategoria()%>">
        <h3>Id: <%=categoria.getIdCategoria()%></h3>

        <label>Nuevo nombre:
            <input name="nombre" type="text" value="<%=categoria.getNombre()%>">
        </label>
        <input type="submit" value="Editar" class="button">
    </form>
</div>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/categorias">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
</body>
</html>
