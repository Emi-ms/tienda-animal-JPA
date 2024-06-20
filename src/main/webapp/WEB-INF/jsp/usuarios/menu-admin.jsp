<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Menu Administrador</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<div class="container-menu-admin">
    <h1>Menú Administrador</h1>

        <div class="wrapper-formulario" style="padding-top: 50px">
            <form action="${pageContext.request.contextPath}/tienda_animales/productos/">
                <button type="submit" class="button-admin">Menú Productos</button>
            </form>
            <form action="${pageContext.request.contextPath}/tienda_animales/categorias/">
                <button type="submit" class="button-admin">Menú Categorías</button>
            </form>
            <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/">
                <button type="submit" class="button-admin">Menú Usuarios</button>
            </form>
        </div>

</div>
<%@include file="../../componentes/footer.jspf" %>

</body>
</html>
