package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Categoria;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "productosServlet", value = "/tienda_animales/productos/*")
public class ProductosServlet extends HttpServlet {

    //GET
    //productos
    //productos/{id}
    //productos/editar/{id}
    //productos/crear
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        String pathInfo = req.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {

            ProductoDAO productoDAO = new ProductoDAOImpl();
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

            String categoriaFiltro = req.getParameter("categoria-filtro");

            List<ProductoDTO> productosDTO = productoDAO.getAll().stream()
                    .map(producto -> {
                        Optional<String> nombreCategoria = productoDAO.getNombreCategoria(producto.getIdCategoria());
                        ProductoDTO productoDTO = new ProductoDTO();
                        productoDTO.setIdProducto(producto.getIdProducto());
                        productoDTO.setNombre(producto.getNombre());
                        productoDTO.setPrecio(producto.getPrecio());
                        productoDTO.setStock(producto.getStock());
                        productoDTO.setFoto(producto.getFoto());
                        productoDTO.setNombreCategoria(nombreCategoria.orElse(""));
                        productoDTO.setDetalle(producto.getDetalle());
                        return productoDTO;
                    }).collect(Collectors.toList());


            req.setAttribute("listaProductos", productosDTO);
            req.setAttribute("listaCategorias", categoriaDAO.getAll());
//            req.setAttribute("listaProductos", productoDAO.getAll());

            if (categoriaFiltro == null || "todos".equals(categoriaFiltro)) {
                req.setAttribute("listaProductos", productosDTO);
            } else {
                List<ProductoDTO> listaFiltrada = productosDTO.stream()
                        .filter(productoDTO -> productoDTO.getNombreCategoria().equals(categoriaFiltro))
                        .toList();
                req.setAttribute("listaProductos", listaFiltrada);
            }

            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");
        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                CategoriaDAO categoria = new CategoriaDAOImpl();
                List<Categoria> categoriaList = categoria.getAll();
                req.setAttribute("categoriaList", categoriaList);


                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/productos/crear-producto.jsp");
            } else if (pathParts.length == 2) {

                ProductoDAO productoDAO = new ProductoDAOImpl();

                try {
                    Optional<Producto> producto = productoDAO.find(Integer.parseInt(pathParts[1]));
                    if (producto.isPresent()) {

                        req.setAttribute("producto", producto.get());
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/productos/ver-detalle.jsp");
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (pathParts.length == 3) {
                ProductoDAO productoDAO = new ProductoDAOImpl();
                CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

                try {
                    Optional<Producto> producto = productoDAO.find(Integer.parseInt(pathParts[2]));
                    if (producto.isPresent()) {
                        req.setAttribute("producto", producto.get());
                        req.setAttribute("categoria", categoriaDAO.getAll());
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/productos/editar-producto.jsp");

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

//        String pathInfo = req.getPathInfo().replaceAll("/$","");
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts.length);
//        System.out.println(pathParts[1]);

        String __method__ = req.getParameter("__method__");

        if (__method__ == null) {

            String nombre = req.getParameter("nombre");
            String precio = req.getParameter("precio");
            String foto = req.getParameter("foto");
            String categoria = req.getParameter("categoria");
            String stock = req.getParameter("stock");
            String detalle = req.getParameter("detalle");
            if (!nombre.isBlank() && !precio.isBlank() && !foto.isBlank() && !categoria.isBlank()) {
                Producto producto = new Producto();
                ProductoDAO productoDAO = new ProductoDAOImpl();
                producto.setNombre(nombre);
                producto.setPrecio(Double.parseDouble(precio));
                producto.setFoto(foto);
                producto.setIdCategoria(Integer.parseInt(categoria));
                producto.setStock(Integer.parseInt(stock));
                producto.setDetalle(detalle);

                productoDAO.create(producto);
            }

        }
        if (__method__ != null && "edit".equals(__method__)) {
            doPut(req, resp);
            System.out.println("stoy aqi");
        } else if (__method__ != null && "delete".equals(__method__)) {
            doDelete(req, resp);
        } else {
            System.out.println("Opcion POST de productos no soportada");
        }

        resp.sendRedirect(req.getContextPath() + "/tienda_animales/productos");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Producto producto = new Producto();
        ProductoDAO productoDAO = new ProductoDAOImpl();
        String idProducto = req.getParameter("idProducto");
        String nombre = req.getParameter("nombre");
        String precio = req.getParameter("precio");
        String foto = req.getParameter("foto");
        String categoria = req.getParameter("categoria");
        String stock = req.getParameter("stock");
        String detalle = req.getParameter("detalle");

        if (!idProducto.isBlank()
                && !nombre.isBlank()
                && !precio.isBlank()
                && !foto.isBlank()
                && !categoria.isBlank()
                && !stock.isBlank()
                && !detalle.isBlank()) {
            try {
                producto.setIdProducto(Integer.parseInt(idProducto));
                producto.setNombre(nombre);
                producto.setPrecio(Double.parseDouble(precio));
                producto.setFoto(foto);
                producto.setIdCategoria(Integer.parseInt(categoria));
                producto.setStock(Integer.parseInt(stock));
                producto.setDetalle(detalle);
                productoDAO.update(producto);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProducto = req.getParameter("idProducto");
        if (idProducto != null) {
            try {
                ProductoDAO productoDAO = new ProductoDAOImpl();
                productoDAO.delete(Integer.parseInt(idProducto));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
