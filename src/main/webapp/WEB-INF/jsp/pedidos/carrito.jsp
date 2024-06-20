<%@ page import="org.iesbelen.model.Carrito" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Carrito</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/carritoStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<%List<Carrito> listaCarrito = (List<Carrito>) request.getAttribute("carrito");%>
<% double totalPagar = (request.getAttribute("totalPagar") != null) ? ((Double) request.getAttribute("totalPagar")) : 0.0; %>
<% Usuario usuario = (Usuario) session.getAttribute("usuario-logeado");%>

<div class="container">
    <%if (listaCarrito == null) {%>
    <div id="mensaje-error" style="color: red; display: block;">
        Lo siento!! No hay suficiente stock para completar la venta 😓
    </div>
    <%} else if (listaCarrito.isEmpty()) {%>
    <h1 style="padding-top: 100px; margin-bottom: 100px; font-size: 70px">Tu carrito está vacío... Ohh.. 😥</h1>
    <%} else {%>
    <table class="table">
        <thead>
        <tr>
            <th>ITEM</th>
            <th>NOMBRE</th>
            <th>DESCRIPCION</th>
            <th>PRECIO</th>
            <th>CANTIDAD</th>
            <th>SUBTOTAL</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            if (listaCarrito != null) {
                for (Carrito carrito : listaCarrito) {%>
        <tr>
            <td><%=carrito.getItem()%>
            </td>
            <td><%=carrito.getNombre()%>
            </td>
            <td><img src="${pageContext.request.contextPath}/img/producto/<%=carrito.getFoto()%>" alt="imágen producto"
                     style="width: 100px;height: 100px"></td>
            <td><%=carrito.getPrecioCompra()%>€
            </td>
            <td><%=carrito.getCantidad()%>
            </td>
            <td><%=carrito.getSubTotal()%>€
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/borrar" method="post">
                    <button type="submit"  class="vibrate-1 button" id="botonBorrar"><svg xmlns="http://www.w3.org/2000/svg" height="16" width="14" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg></button>
                    <input type="hidden" name="__method__" value="delete">
                    <input type="hidden" name="idProducto" value="<%=carrito.getIdProducto()%>">
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <div class="card">
        <h3>Resumen del pedido</h3>
        <label>Total a pagar</label>
        <div style="padding-left: 50px; padding-right: 50px" class="input-text"><%=String.format("%.2f",totalPagar)%>€</div>
        <form id="generarCompraForm" action="${pageContext.request.contextPath}/tienda_animales/pedidos/crear-pedido"
              method="post">
            <input type="hidden" name="__method__" value="crear-pedido">
            <button type="button" class="button-comprar" id="botonTramitarPedido">
                <%if (usuario != null) {%>
                <input type="hidden" name="idUsuario" value="<%=usuario.getIdUsuario()%>">
                <%}%>
                <svg class="svg" xmlns="http://www.w3.org/2000/svg" height="20" width="18" style="color: white"
                     viewBox="0 0 448 512">
                    <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.-->
                    <path d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z"></path>
                </svg>
                TRAMITAR PEDIDO
            </button>
        </form>
    </div>
    <%}%>
</div>
<%@include file="../../componentes/footer.jspf" %>
<script>
    document.getElementById("generarCompraForm").addEventListener("click", (ev) => {
        let usuarioLogeado = <%= request.getSession().getAttribute("usuario-logeado") != null %>;
        if (usuarioLogeado) {
            document.getElementById("generarCompraForm").submit();
        } else {
            window.location.href = "${pageContext.request.contextPath}/tienda_animales/login";
        }
    });
</script>
</body>
</html>
