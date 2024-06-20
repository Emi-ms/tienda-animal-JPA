package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Optional;

@WebServlet(name = "registroServlet", value = "/tienda_animales/registro/*")

public class RegistrosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {

            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/alta-usuario.jsp");
        } else {
            System.out.println("Opcion GET no soportada");
            dispatcher = req.getRequestDispatcher("index.jsp");
        }

        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __method__ = req.getParameter("__method__");

        if (__method__ != null && "alta-usuario".equals(__method__)) {

            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            String dni = req.getParameter("dni");
            String domicilio = req.getParameter("domicilio");
            String fechaDeNacimiento = req.getParameter("fechaDeNacimiento");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            if (!nombre.isBlank()
                    && !apellido.isBlank()
                    && !dni.isBlank()
                    && !domicilio.isBlank()
                    && !fechaDeNacimiento.isBlank()
                    && !email.isBlank()
                    && !password.isBlank()) {
                Usuario usuario = new Usuario();
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDni(dni);
                usuario.setDomicilio(domicilio);
                usuario.setFechaDeNacimiento(Date.valueOf(fechaDeNacimiento));
                usuario.setEmail(email);
                try {
                    usuario.setPassword(Usuario.hashPassword(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                usuario.setRol("cliente");

                Optional<Usuario> usuarioBusqueda = usuarioDAO.findByEmail(email);
                if (usuarioBusqueda.isPresent()) {

                    req.setAttribute("errorEmail", "El correo electrónico ya está registrado");
                    req.getRequestDispatcher("/WEB-INF/jsp/usuarios/alta-usuario.jsp").forward(req, resp);

                } else {

                    usuarioDAO.create(usuario);
                    resp.sendRedirect(req.getContextPath());
                }


            }
        }
    }
}
