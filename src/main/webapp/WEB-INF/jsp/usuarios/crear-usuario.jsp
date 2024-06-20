<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/usuarioStyles.css">
</head>
<body>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/usuarios">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
<div class="container-registro">
    <h1>Crear Usuario</h1>
    <form method="post" action="${pageContext.request.contextPath}/tienda_animales/usuarios/crear" class="formulario">
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
        <div class="wrapper-formulario">
            <label>Rol</label>
            <select name="rol" class="input-text">
                <option value="cliente" class="input-text">Cliente</option>
                <option value="administrador" class="input-text">Administrador</option>
            </select>
        </div>
        <input type="submit" value="Crear" class="button">
    </form>
</div>
</body>
</html>