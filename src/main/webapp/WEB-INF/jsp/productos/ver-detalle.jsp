<%@ page import="org.iesbelen.model.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detalle Producto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/productoStyles.css">
</head>
<body>
<% Producto producto = (Producto) request.getAttribute("producto");%>
<div class="container-ver-detalle">
    <h1>Detalle Producto</h1>
    <div class="detalle-container">
        <div class="info-wrapper">
            <h3>Id Producto: <%=producto.getIdProducto()%>
            </h3>
            <h3>Nombre: <%=producto.getNombre()%>
            </h3>
            <h3>Precio: <%=producto.getPrecio()%>€</h3>
            <h3>Stock: <%=producto.getStock()%> unidades</h3>
        </div>
        <div class="img-wrapper">
            <img src="${pageContext.request.contextPath}/img/producto/<%=producto.getFoto()%>" alt="imagen"
                 style="max-height: 400px;max-width: 400px">
            <p><%=producto.getDetalle()%>
            </p>
        </div>
    </div>
</div>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/productos">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
</body>
</html>
