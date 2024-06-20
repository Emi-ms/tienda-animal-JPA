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

@WebServlet(name = "usuariosServlet", value = "/tienda_animales/usuarios/*")
public class UsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        String pathInfo = req.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

            req.setAttribute("listaUsuarios", usuarioDAO.getAll());
            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
        } else {

            pathInfo = req.getPathInfo().replaceAll("/$", "");
            String[] pathPart = pathInfo.split("/");

            if (pathPart.length == 2 && "alta-usuario".equals(pathPart[1])) {
                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/alta-usuario.jsp");
            } else if (pathPart.length == 2 && "crear".equals(pathPart[1])) {
                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");
            } else if (pathPart.length == 2 && "menu-admin".equals(pathPart[1])) {
                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/menu-admin.jsp");
            } else if (pathPart.length == 3 && "editar".equals(pathPart[1])) {
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

                try {
                    Optional<Usuario> usuario = usuarioDAO.find(Integer.parseInt(pathPart[2]));
                    if (usuario.isPresent()) {
                        req.setAttribute("usuario", usuario.get());
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");
                    } else {
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }
            } else {
                System.out.println("Opcion GET no soportada");
                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
            }
        }
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String __method__ = req.getParameter("__method__");

        boolean redirectToUsuarios = false;

        if (__method__ == null) {
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            Usuario usuarioNuevo = new Usuario();
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            String dni = req.getParameter("dni");
            String domicilio = req.getParameter("domicilio");
            String fechaDeNacimiento = req.getParameter("fechaDeNacimiento");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rol = req.getParameter("rol");

            if (!nombre.isBlank()
                    && !apellido.isBlank()
                    && !dni.isBlank()
                    && !domicilio.isBlank()
                    && !fechaDeNacimiento.isBlank()
                    && !email.isBlank()
                    && !password.isBlank()
                    && !rol.isBlank()) {

                usuarioNuevo.setNombre(nombre);
                usuarioNuevo.setApellido(apellido);
                usuarioNuevo.setDni(dni);
                usuarioNuevo.setDomicilio(domicilio);
                usuarioNuevo.setFechaDeNacimiento(Date.valueOf(fechaDeNacimiento));
                usuarioNuevo.setEmail(email);
                try {
                    usuarioNuevo.setPassword(Usuario.hashPassword(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                usuarioNuevo.setRol(rol);

                Optional<Usuario> usuarioBusqueda = usuarioDAO.findByEmail(email);
                if (usuarioBusqueda.isEmpty()) {
                    usuarioDAO.create(usuarioNuevo);
                } else {
                    System.out.println("Ya existe un usuario con ese email");
                }

                redirectToUsuarios = true;
            }

        }  else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            //dirijo a actualizar usuario en el metodo doPut
            doPut(req, resp);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            //dirijo a borrar usuario en metodo doDelete
            doDelete(req, resp);

        } else {
            System.out.println("Opcion POST no soportada");

        }


            resp.sendRedirect(req.getContextPath() + "/tienda_animales/usuarios");


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Usuario usuarioActualizado = new Usuario();

        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dni = req.getParameter("dni");
        String domicilio = req.getParameter("domicilio");
        String fechaDeNacimiento = req.getParameter("fechaDeNacimiento");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rol = req.getParameter("rol");

        if (!nombre.isBlank()
                && !apellido.isBlank()
                && !dni.isBlank()
                && !domicilio.isBlank()
                && !fechaDeNacimiento.isBlank()
                && !email.isBlank()
                && !password.isBlank()) {

            usuarioActualizado.setIdUsuario(idUsuario);
            usuarioActualizado.setNombre(nombre);
            usuarioActualizado.setApellido(apellido);
            usuarioActualizado.setDni(dni);
            usuarioActualizado.setDomicilio(domicilio);
            usuarioActualizado.setFechaDeNacimiento(Date.valueOf(fechaDeNacimiento));
            usuarioActualizado.setEmail(email);
            try {
                usuarioActualizado.setPassword(Usuario.hashPassword(password));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            usuarioActualizado.setRol(rol);
            usuarioDAO.update(usuarioActualizado);
        } else {
            System.out.println("ERROR: NO HA SIDO POSIBLE ACTUALIZAR EL USUARIO");
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));

        try {
            usuarioDAO.delete(idUsuario);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
