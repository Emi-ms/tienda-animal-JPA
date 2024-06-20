<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<div class="container-crear">
    <h1>Editar Usuario</h1>
    <form method="post" class="formulario">
        <div class="wrapper-formulario">
            <label>Nombre</label>
            <input class="input-text" name="nombre" type="text" value="<%=usuario.getNombre()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Apellido </label>
            <input class="input-text" name="apellido" type="text" value="<%=usuario.getApellido()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Dni</label>
            <input class="input-text" name="dni" type="text" value="<%=usuario.getDni()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Domicilio </label>
            <input class="input-text" name="domicilio" type="text" value="<%=usuario.getDomicilio()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Fecha de Nacimiento</label>
            <input class="input-text" type="date" name="fechaDeNacimiento" value="<%=usuario.getFechaDeNacimiento()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Email</label>
            <input class="input-text" type="email" name="email" value="<%=usuario.getEmail()%>"/>
        </div>
        <div class="wrapper-formulario">
            <label>Password</label>
            <input class="input-text" type="password" name="password" required/>
        </div>
        <div class="wrapper-formulario">
            <label>Rol</label>
            <select name="rol" class="input-text">
                <option value="cliente" class="input-text">Cliente</option>
                <option value="administrador" class="input-text">Administrador</option>
            </select>
        </div>
        <input type="submit" value="Editar" class="button">
        <input type="hidden" name="__method__" value="put">
        <input type="hidden" name="idUsuario" value="<%=usuario.getIdUsuario()%>">
    </form>
</div>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/usuarios/">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
</body>
</html>
