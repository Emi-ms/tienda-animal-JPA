<%@ page import="org.iesbelen.dao.ProductoDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Tienda</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/tiendaStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>
<% List<ProductoDTO> listProd = (List<ProductoDTO>) request.getAttribute("listaProductos");%>
<% List<Categoria> listCat = (List<Categoria>) request.getAttribute("listaCategorias");%>
<% int contadorItems = (int) request.getAttribute("contadorItems");%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario-logeado");%>


<div class="container">
    <div class="buttons-wrapper">
        <form id="historialForm" action="<% if(usuario != null){%>
    ${pageContext.request.contextPath}/tienda_animales/pedidos/historial/<%=usuario.getIdUsuario()%>
<%} else {%>
    ${pageContext.request.contextPath}/tienda_animales/login
<%}%>">
            <button type="submit" class="button-historial">Historial de Pedidos</button>
        </form>

        <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/carrito">
            <button type="submit" class="button-anyadir">
                <svg class="svg" xmlns="http://www.w3.org/2000/svg" height="20" width="18" style="color: white"
                     viewBox="0 0 448 512">
                    <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.-->
                    <path d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z"/>
                </svg>
                CARRITO(<%=contadorItems%>)
            </button>
        </form>
    </div>

    <div class="filtros-wrapper">
        <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/" method="get" class="select">
            <label for="buscar">Buscar</label>
            <input name="buscar" type="text" id="buscar" placeholder="Buscar productos" class="input-text">

            <input type="submit" value="Buscar" class="button">
        </form>


        <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/" class="select">
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

    </div>
    <div class="cards-container">

        <% if (listProd != null && !listProd.isEmpty()) {

            for (ProductoDTO producto : listProd) {

        %>
        <div class="card">
            <h2><%=producto.getNombre()%>
            </h2>

            <div><img src="${pageContext.request.contextPath}/img/producto/<%=producto.getFoto()%>"
                      alt="imágen producto"
                      style="width: 200px;height: 200px"></div>
            <div class="card-wrap">
                <h4><%=producto.getNombreCategoria()%>
                </h4>
                <h3 class="precio"><%=producto.getPrecio()%> €
                </h3>

                <div>
                    <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/<%=producto.getIdProducto()%>">
                        <input type="submit" value="Más información" class="button">
                    </form>
                </div>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/tienda_animales/pedidos/anyadir-al-carrito/<%=producto.getIdProducto()%>">
                    <button type="submit" class="button-anyadir">
                        <svg class="svg" xmlns="http://www.w3.org/2000/svg" height="20" width="18" style="color: white"
                             viewBox="0 0 448 512">
                            <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.-->
                            <path d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z"/>
                        </svg>
                        AÑADIR AL CARRITO
                    </button>
                </form>
            </div>
        </div>
        <%}%>
        <%} else {%>
        <div style="height: 300px;margin-top: 50px;text-align: center">
            <h2 style="color: red; margin-bottom: 40px">Lo siento, no hemos encontrado lo que buscabas 😅</h2>
            <p style="font-weight: bold;margin-bottom: 20px">Pero que no cunda el pánico, sigue las instrucciones:</p>
            <p>1. Revisa que la búsqueda no tenga errores ortográficos</p>
            <p>2. Prueba a volver a buscar con palabras más específicas</p>
        </div>
        <%}%>
    </div>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
