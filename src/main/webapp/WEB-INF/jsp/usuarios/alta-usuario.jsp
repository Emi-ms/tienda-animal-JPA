<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Alta Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<%@include file="../../componentes/nav.jspf" %>

<div class="container-registro">
    <h1>Formulario de Registro</h1>
    <div class="error-message" style="color: red;">
        <% String errorEmail = (String) request.getAttribute("errorEmail"); %>
        <% if (errorEmail != null) { %>
        <%= errorEmail %>
        <% } %>
    </div>

    <form action="${pageContext.request.contextPath}/tienda_animales/registro/" method="post" class="formulario">
        <input type="hidden" name="__method__" value="alta-usuario">
        <div class="wrapper-formulario">
            <label>Nombre</label>
            <input class="input-text" type="text" name="nombre" required>
        </div>
        <div class="wrapper-formulario">
            <label>Apellido</label>
            <input class="input-text" type="text" name="apellido" required>
        </div>
        <div class="wrapper-formulario">
            <label>DNI</label>
            <input class="input-text" type="text" name="dni" required>
        </div>
        <div class="wrapper-formulario">
            <label>Domicilio</label>
            <input class="input-text" type="text" name="domicilio" required>
        </div>
        <div class="wrapper-formulario">
            <label>Fecha de Nacimiento</label>
            <input class="input-text" type="date" name="fechaDeNacimiento" required>
        </div>
        <div class="wrapper-formulario">
            <label>Email</label>
            <input class="input-text" type="email" name="email" required>
        </div>
        <div class="wrapper-formulario">
            <label>Password</label>
            <input class="input-text" type="password" name="password" required>
        </div>

        <input type="submit" value="Dar de Alta" class="button">
    </form>
</div>
<%@include file="../../componentes/footer.jspf" %>
</body>
</html>
