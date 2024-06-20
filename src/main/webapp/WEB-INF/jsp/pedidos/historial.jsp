<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.iesbelen.dao.LineaPedidoDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Historial de Pedidos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/historialStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<div class="container">
    <h1>Historial de Pedidos</h1>
    <form action="${pageContext.request.contextPath}/tienda_animales/pedidos">
        <input type="submit" value="Volver" class="button">
    </form>
    <%
        Map<Pedido, List<LineaPedidoDTO>> mapaPedidosConDetalles = (Map<Pedido, List<LineaPedidoDTO>>) request.getAttribute("mapaPedidosConDetalles");
        if (mapaPedidosConDetalles != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Map.Entry<Pedido, List<LineaPedidoDTO>> entry : mapaPedidosConDetalles.entrySet()) {
    %>

    <div class="container-ver-detalle">


        <div class="info-wrapper">
            <h2>Pedido ID: <%= entry.getKey().getIdPedido() %>
            </h2>
            <p>Fecha: <%= sdf.format(entry.getKey().getFechaPedido()) %>
            </p>
            <p>Precio Total: <%= entry.getKey().getPrecioTotal() %>€</p>

            <table class="table">
                <thead>
                <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                </tr>
                </thead>
                <tbody>
                <% for (LineaPedidoDTO detalle : entry.getValue()) { %>
                <tr>
                    <td><%= detalle.getNombreProducto() %>
                    </td>
                    <td><%= detalle.getCantidad() %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <%
            }
        }
    %>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
