<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Producto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/productoStyles.css">
</head>
<body>
<% List<Categoria> listCat = (List<Categoria>) request.getAttribute("categoriaList");%>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/productos">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
<div class="container-crear">
    <h1>Crear Producto</h1>
    <form method="post" action="${pageContext.request.contextPath}/tienda_animales/productos/" class="formulario">
        <div class="wrapper-formulario">
            <label>Nombre </label>
            <input class="input-text" name="nombre" type="text"/>
        </div>
        <div class="wrapper-formulario">
            <label>Precio</label>
            <input class="input-text" name="precio" type="number" step="0.01"/>
        </div>
        <div class="wrapper-formulario">
            <label>Stock</label>
            <input class="input-text" name="stock" type="number"/>
        </div>
        <div class="wrapper-formulario">
            <label>Foto</label>
            <input class="input-text" name="foto" type="text"/>
        </div>
        <div class="wrapper-formulario">
            <label>Categoría</label>
            <select class="input-text" name="categoria">
                <%
                    for (Categoria cat : listCat) {
                %>
                <option  class="input-text"  value="<%=cat.getIdCategoria()%>"><%=cat.getNombre()%>
                </option>
                <%}%>
            </select>
        </div>
        <div class="wrapper-formulario">
            <label>Detalle</label>
            <textarea class="text-area" name="detalle"></textarea>
        </div>
        <input type="submit" value="Crear" class="button">
    </form>
</div>

</body>
</html>
