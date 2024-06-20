package org.iesbelen.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaDAOImpl;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Categoria;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "categoriasServlet", value = "/tienda_animales/categorias/*")
public class CategoriasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
            req.setAttribute("listaCat", categoriaDAO.getAll());
            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/categorias/categorias.jsp");
        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/categorias/crear-categoria.jsp");
            } else if (pathParts.length == 2) {
                CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
                try {
                    Optional<Categoria> categoria = categoriaDAO.find(Integer.parseInt(pathParts[1]));
                    if (categoria.isPresent()) {
                        req.setAttribute("categoria", categoria.get());
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/categorias/editar-categoria.jsp");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        }
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __method__ = req.getParameter("__method__");
        if (__method__ == null) {
            String nombre = req.getParameter("nombre");

            if (!nombre.isBlank()) {
                Categoria categoria = new Categoria();
                CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
                categoria.setNombre(nombre);
                categoriaDAO.create(categoria);
            }

        } else if (__method__ != null && "delete".equals(__method__)) {
            doDelete(req, resp);
        } else if (__method__ != null && "edit".equals(__method__)) {
            doPut(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/tienda_animales/categorias");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        String idCategoria = req.getParameter("idCategoria");
        String nombre = req.getParameter("nombre");
        if (!idCategoria.isBlank() && !nombre.isBlank()) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(Integer.parseInt(idCategoria));
            categoria.setNombre(nombre);
            categoriaDAO.update(categoria);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

        String idCategoria = req.getParameter("idCategoria");
        try {
            categoriaDAO.delete(Integer.parseInt(idCategoria));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
