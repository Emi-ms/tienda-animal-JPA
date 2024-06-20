<%@ page import="org.iesbelen.model.Producto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>Detalle Producto</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/tiendaStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<% Producto producto = (Producto) request.getAttribute("producto");%>
<div class="container-ver-detalle">
  <h1>Detalle Producto</h1>
  <div class="detalle-container">
    <div class="info-wrapper">
      <h3>Nombre: <%=producto.getNombre()%>
      </h3>
      <h3>Precio: <%=producto.getPrecio()%>€</h3>
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
  <form action="${pageContext.request.contextPath}/tienda_animales/pedidos">
    <input type="submit" value="Volver" class="button">
  </form>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>