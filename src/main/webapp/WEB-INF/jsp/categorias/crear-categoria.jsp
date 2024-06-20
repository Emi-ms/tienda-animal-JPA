<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Categoría</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/categoriaStyles.css">
</head>
<body>
<div class="container-crear">
    <h1>Crear Categoría</h1>
    <form method="post" action="${pageContext.request.contextPath}/tienda_animales/categorias/crear">
        <label>Ingrese el nombre de la categoría nueva:
            <input name="nombre" type="text"/>
        </label>
        <input type="submit" value="Crear" class="button">
    </form>
</div>
<div class="button-wrapper">
    <form action="${pageContext.request.contextPath}/tienda_animales/categorias">
        <input type="submit" value="Volver" class="button">
    </form>
</div>
</body>
</html>
