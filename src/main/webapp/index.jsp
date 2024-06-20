<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="styles/generalStyles.css">
</head>
<body>

<div class="contenedor-principal">

    <%@include file="WEB-INF/componentes/nav.jspf" %>
    <div id="contenedor-1">
        <div id="caja1-contenedor1">
            <h3>DESCUBRE EL PARAÍSO DE LAS MASCOTAS </h3>
            <h1>Tu Refugio Para Mascotas Felices</h1>
            <p>Ofrecemos productos de alta calidad para consentir a tus amigos peludos. ¡Bienvenido al hogar de la
                felicidad animal!</p>
            <a id="button" href="${pageContext.request.contextPath}/tienda_animales/registro">Regístrate</a>
        </div>
        <img src="${pageContext.request.contextPath}/img/animales/perro-pug-aislado-fondo-blanco.jpg" alt="img-perro">
    </div>
    <div id="contenedor-2">
        <div id="contenedor-2-wrapper">
            <div class="iconos">
                <img src="${pageContext.request.contextPath}/img/iconos/cuidado-animal.png" alt="icono-cuidado-animal">
                <div class="wrapper">
                    <h2>Compromiso con el Bienestar Animal</h2>
                    <p>Nos dedicamos a asegurar que cada mascota viva una vida feliz y saludable,
                        proporcionando productos de alta calidad y servicios especializados.</p>
                </div>
            </div>
            <div class="iconos">
                <img src="${pageContext.request.contextPath}/img/iconos/casa-de-perro.png" alt="icono-caseta">
                <div class="wrapper">
                    <h2>Amor y Compasión</h2>
                    <p>Fomentamos el amor incondicional hacia los animales y promovemos la adopción responsable,
                        creando un ambiente lleno de cariño y compasión.</p>
                </div>
            </div>
            <div class="iconos">
                <img src="${pageContext.request.contextPath}/img/iconos/mascota.png" alt="icono-mascota">
                <div class="wrapper">
                    <h2>Experiencia Única</h2>
                    <p>Buscamos ofrecer a nuestros clientes y sus mascotas una experiencia única
                        y memorable cada vez que nos visitan, destacando la diversión
                        y la alegría en cada rincón de nuestra tienda.</p>
                </div>
            </div>
        </div>
    </div>
    <div id="contenedor-3">
        <h1>Las Marcas Que Ellos Elegirían</h1>
        <div id="caja1-contenedor3">
            <img class="img-portfolio" src="${pageContext.request.contextPath}/img/marcas/brand__advance.svg"
                 alt="img1">
            <img class="img-portfolio" src="${pageContext.request.contextPath}/img/marcas/brand__criadores.svg"
                 alt="img2">
            <img class="img-portfolio" src="${pageContext.request.contextPath}/img/marcas/brand__natures-variety.svg"
                 alt="img3">
            <img class="img-portfolio" src="${pageContext.request.contextPath}/img/marcas/brand__royal-canin.svg"
                 alt="img4">
        </div>
    </div>
    <div id="contenedor-4">
        <h1>Sobre Nosotros</h1>
        <div id="caja1-contenedor4">
            <img id="img-animales"
                 src="${pageContext.request.contextPath}/img/animales/grupo-animales-lindos-sobre-fondo-blanco.jpg"
                 alt="img-animale">
            <div id="parrafos">
                <p>En Mundo Peludo, creemos en la magia de la compañía animal.
                    Nuestra tienda es más que un lugar para comprar productos para tus mascotas;
                    es un refugio lleno de amor y alegría, donde cada pata, pluma y escama es bienvenida.
                    Desde encantadores compañeros peludos hasta adorables
                    amigos emplumados, aquí encontrarás todo lo que necesitas
                    para que tu mascota se sienta amada y mimada.</p>
                <p>Únete a nosotros en esta aventura llena de amor por los animales.
                    ¡Explora, descubre y crea recuerdos duraderos con tus fieles amigos!
                    ¡Bienvenido a Mundo Peludo - Donde el Amor por los Animales
                    es la Inspiración detrás de Todo lo que Hacemos!</p>
            </div>
        </div>
    </div>
    <%--    <div id="contenedor-5">--%>
    <%--        <h1>Contact</h1>--%>
    <%--        <div>--%>
    <%--            <form id="formulario" action="">--%>
    <%--                <div class="wrapper-formulario">--%>
    <%--                    <label for="">Name</label>--%>
    <%--                    <input class="input-text" type="text">--%>

    <%--                </div>--%>
    <%--                <div class="wrapper-formulario">--%>
    <%--                    <label for="">Email</label>--%>
    <%--                    <input class="input-text" type="text">--%>

    <%--                </div>--%>
    <%--                <div class="wrapper-formulario">--%>
    <%--                    <label for="">Message</label>--%>
    <%--                    <textarea class="text-area"></textarea>--%>

    <%--                </div>--%>
    <%--                <button>Send Message</button>--%>
    <%--            </form>--%>
    <%--        </div>--%>
    <%--    </div>--%>

</div>
<%@include file="WEB-INF/componentes/footer.jspf" %>
<%--<form action="${pageContext.request.contextPath}/tienda_animales/usuarios/alta-usuario">--%>
<%--    <input type="submit" value="Sign in">--%>
<%--</form>--%>
</body>
</html>