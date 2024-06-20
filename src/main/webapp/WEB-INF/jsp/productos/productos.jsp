<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dao.ProductoDTO" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Productos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/productoStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<% List<ProductoDTO> listProd = (List<ProductoDTO>) request.getAttribute("listaProductos");%>
<% List<Categoria> listCat = (List<Categoria>) request.getAttribute("listaCategorias");%>
<div class="container">
    <form action="${pageContext.request.contextPath}/tienda_animales/productos/crear">
        <input type="submit" value="Crear Producto" class="button">
    </form>
    <form action="${pageContext.request.contextPath}/tienda_animales/productos/" class="select">
        <label>Categoría</label>
        <select name="categoria-filtro" class="input-text">
            <option value="todos" class="input-text"></option>
            <%for (Categoria categoria : listCat) {%>
            <option value="<%=categoria.getNombre()%>" class="input-text"><%=categoria.getNombre()%>
            </option>
            <%}%>
        </select>
        <input type="submit" value="Filtrar" class="button">
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>PRECIO</th>
            <th>STOCK</th>
            <th>FOTO</th>
            <th>CATEGORIA</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <% if (listProd != null) {
            for (ProductoDTO producto : listProd) {

        %>
        <tr>
            <td><%=producto.getIdProducto()%>
            </td>
            <td><%=producto.getNombre()%>
            </td>
            <td><%=producto.getPrecio()%>
            </td>
            <td><%=producto.getStock()%>
            </td>
            <td><img src="${pageContext.request.contextPath}/img/producto/<%=producto.getFoto()%>" alt="imágen producto"
                     style="width: 100px;height: 100px"></td>
            <td><%=producto.getNombreCategoria()%>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/productos/<%=producto.getIdProducto()%>">
                    <input type="submit" value="Ver Detalle" class="button">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/productos/editar/<%=producto.getIdProducto()%>">
                    <input type="submit" value="Editar Producto" class="button">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/productos/borrar" method="post">
                    <input type="submit" value="Borrar Producto" class="button">
                    <input type="hidden" name="__method__" value="delete">
                    <input type="hidden" name="idProducto" value="<%=producto.getIdProducto()%>">
                </form>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <%} else {%>
    <h2>No hay productos registrados</h2>
    <%}%>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
