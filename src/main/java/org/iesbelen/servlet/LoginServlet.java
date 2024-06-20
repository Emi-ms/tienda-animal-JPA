package org.iesbelen.servlet;

import com.mysql.cj.exceptions.CJPacketTooBigException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@WebServlet(name = "loginServlet", value = "/tienda_animales/login/*")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String pathInfo = req.getPathInfo();

        if (pathInfo == null) {
            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        pathInfo = pathInfo.replaceAll("/$", "");

        if (pathInfo.isEmpty()) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            Optional<Usuario> usuarioBusqueda = usuarioDAO.findByEmail(email);

            if (usuarioBusqueda.isPresent() && verificarPassword(usuarioBusqueda.get(), password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("usuario-logeado", usuarioBusqueda.get());
                resp.sendRedirect(req.getContextPath());

            } else {

                req.setAttribute("errorLogin", "Usuario o contraseña incorrectos");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
                dispatcher.forward(req, resp);
            }

        } else if ("/logout".equals(pathInfo)) {

            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath());

        }
    }

    private boolean verificarPassword(Usuario usuario, String password) {
        try {
            return usuario.getPassword().equals(Usuario.hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
