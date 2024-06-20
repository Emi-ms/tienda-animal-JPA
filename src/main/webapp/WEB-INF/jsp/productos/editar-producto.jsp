<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Producto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/productoStyles.css">
</head>
<body>
<%
    Producto producto = (Producto) request.getAttribute("producto");
    List<Categoria> listCat = (List<Categoria>) request.getAttribute("categoria");

%>
<div class="container-crear">
    <h1>Editar Producto</h1>
    <form method="post" class="formulario">
        <div class="wrapper-formulario">
            <label>Nombre</label>
            <input class="input-text" name="nombre" type="text" value="<%=producto.getNombre()%>">
        </div>
        <div class="wrapper-formulario">
            <label>Precio </label>
            <input class="input-text" name="precio" type="number" step="0.01" value="<%=producto.getPrecio()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Stock</label>
            <input class="input-text" name="stock" type="number" value="<%=producto.getStock()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Foto </label>
            <input class="input-text" name="foto" type="text" value="<%=producto.getFoto()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Categoría</label>
            <select class="input-text" name="categoria">
                <%
                    for (Categoria cat : listCat) {
                %>
                <option class="input-text" value="<%=cat.getIdCategoria()%>"><%=cat.getNombre()%>
                </option>
                <%}%>
            </select>
        </div>
        <div class="wrapper-formulario">
            <label>Detalle</label>
            <textarea class="text-area" name="detalle"><%=producto.getDetalle()%></textarea>
        </div>

        <input type="submit" value="Editar" class="button">
        <input type="hidden" name="__method__" value="edit">
        <input type="hidden" name="idProducto" value="<%=producto.getIdProducto()%>">
    </form>
</div>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/productos">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
</body>
</html>
