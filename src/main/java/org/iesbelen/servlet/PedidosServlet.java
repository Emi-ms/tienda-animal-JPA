package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Carrito;
import org.iesbelen.model.Pedido;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "pedidosServlet", value = "/tienda_animales/pedidos/*")
public class PedidosServlet extends HttpServlet {
    List<Carrito> listaCarrito = new ArrayList<>();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            ProductoDAO productoDAO = new ProductoDAOImpl();
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

            String categoriaFiltro = req.getParameter("categoria-filtro");
            String filtroBusqueda = req.getParameter("buscar");

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
            ;

            if (filtroBusqueda != null && !filtroBusqueda.isEmpty()) {
                productosDTO = productosDTO.stream()
                        .filter(productoDTO -> productoDTO.getNombre().toLowerCase().contains(filtroBusqueda.toLowerCase()))
                        .collect(Collectors.toList());
            }

            req.setAttribute("contadorItems", listaCarrito.size());
            req.setAttribute("listaProductos", productosDTO);
            req.setAttribute("listaCategorias", categoriaDAO.getAll());

            if (categoriaFiltro == null || "todos".equals(categoriaFiltro)) {
                req.setAttribute("listaProductos", productosDTO);
            } else {
                List<ProductoDTO> listaFiltrada = productosDTO.stream()
                        .filter(productoDTO -> productoDTO.getNombreCategoria().equals(categoriaFiltro))
                        .toList();
                req.setAttribute("listaProductos", listaFiltrada);
            }

            dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/tienda.jsp");
        } else {

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "carrito".equals(pathParts[1])) {
                totalPagar = 0.0;
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }

                req.setAttribute("carrito", listaCarrito);
                req.setAttribute("totalPagar", totalPagar);


                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/carrito.jsp");
            } else if (pathParts.length == 2 && "venta-generada".equals(pathParts[1])) {

                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/venta-generada.jsp");
            } else if (pathParts.length == 2) {
                ProductoDAO productoDAO = new ProductoDAOImpl();

                try {
                    Optional<Producto> producto = productoDAO.find(Integer.parseInt(pathParts[1]));
                    if (producto.isPresent()) {

                        req.setAttribute("producto", producto.get());
                        dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/ver-detalle.jsp");
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (pathParts.length == 3 && "anyadir-al-carrito".equals(pathParts[1])) {
                System.out.println("estoy anyadiendo al carrito");
                int pos = 0;
                cantidad = 1;
                int idProducto = Integer.parseInt(pathParts[2]);

                ProductoDAO productoDAO = new ProductoDAOImpl();
                Producto productoNuevo = new Producto();
                productoNuevo = productoDAO.find(idProducto).get();

                if (!listaCarrito.isEmpty()) {
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (idProducto == listaCarrito.get(i).getIdProducto()) {
                            pos = i;
                        }
                    }
                    if (idProducto == listaCarrito.get(pos).getIdProducto()) {
                        cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                        double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subtotal);
                    } else {
                        item = item + 1;
                        Carrito carrito = new Carrito();
                        carrito.setItem(item);
                        carrito.setIdProducto(productoNuevo.getIdProducto());
                        carrito.setNombre(productoNuevo.getNombre());
                        carrito.setFoto(productoNuevo.getFoto());
                        carrito.setPrecioCompra(productoNuevo.getPrecio());
                        carrito.setCantidad(cantidad);
                        carrito.setSubTotal(cantidad * productoNuevo.getPrecio());

                        listaCarrito.add(carrito);
                    }

                } else {
                    item = item + 1;
                    Carrito carrito = new Carrito();
                    carrito.setItem(item);
                    carrito.setIdProducto(productoNuevo.getIdProducto());
                    carrito.setNombre(productoNuevo.getNombre());
                    carrito.setFoto(productoNuevo.getFoto());
                    carrito.setPrecioCompra(productoNuevo.getPrecio());
                    carrito.setCantidad(cantidad);
                    carrito.setSubTotal(cantidad * productoNuevo.getPrecio());

                    listaCarrito.add(carrito);
                }


                req.setAttribute("contadorItems", listaCarrito.size());


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

                if (categoriaFiltro == null || "todos".equals(categoriaFiltro)) {
                    req.setAttribute("listaProductos", productosDTO);
                } else {
                    List<ProductoDTO> listaFiltrada = productosDTO.stream()
                            .filter(productoDTO -> productoDTO.getNombreCategoria().equals(categoriaFiltro))
                            .toList();
                    req.setAttribute("listaProductos", listaFiltrada);
                }

                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/tienda.jsp");

            } else if (pathParts.length == 3 && "historial".equals(pathParts[1])) {

                PedidoDAO pedidoDAO = new PedidoDAOImpl();
                LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAOImpl();
                Map<Pedido, List<LineaPedidoDTO>> mapaPedidosConDetalle = new TreeMap<>();
                List<Pedido> listaPedidos = pedidoDAO.getByIdUser(Integer.parseInt(pathParts[2]));


                for (Pedido pedido : listaPedidos) {

                    List<LineaPedidoDTO> lineasPedidosDTO = lineaPedidoDAO.getLineasPorPedido(pedido.getIdPedido()).stream()
                            .map(lineaPedido -> {
                                Optional<String> nombreProducto = lineaPedidoDAO.getNombreProducto(lineaPedido.getIdProducto());
                                LineaPedidoDTO lineaPedidoDTO = new LineaPedidoDTO();
                                lineaPedidoDTO.setIdLineaPedido(lineaPedido.getIdLineaPedido());
                                lineaPedidoDTO.setIdPedido(lineaPedido.getIdPedido());
                                lineaPedidoDTO.setNombreProducto(nombreProducto.orElse(""));
                                lineaPedidoDTO.setCantidad(lineaPedido.getCantidad());
                                return lineaPedidoDTO;
                            }).collect(Collectors.toList());

                    mapaPedidosConDetalle.put(pedido, lineasPedidosDTO);
                }

                req.setAttribute("mapaPedidosConDetalles", mapaPedidosConDetalle);
                dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/historial.jsp");
            }

            if (dispatcher == null) {
                System.out.println("Opcion GET no soportada");
                dispatcher = req.getRequestDispatcher("index.jsp");
            }
        }

        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String __method__ = req.getParameter("__method__");

        if (__method__.equals("crear-pedido")) {
            boolean stockSuficiente = true;
            ProductoDAO productoDAO = new ProductoDAOImpl();

            for (Carrito carrito : listaCarrito) {
                int idProducto = carrito.getIdProducto();
                int cantidadVendida = carrito.getCantidad();

                if (!productoDAO.verificarStockSuficiente(idProducto, cantidadVendida)) {
                    stockSuficiente = false;
                }
            }

            if (stockSuficiente) {

                PedidoDAO pedidoDAO = new PedidoDAOImpl();
                Pedido pedido = new Pedido();
                String idUsuario = req.getParameter("idUsuario");

                double totalPedido = 0;
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPedido += listaCarrito.get(i).getSubTotal();
                }
                pedido.setPrecioTotal(totalPedido);
                pedido.setIdUsuario(Integer.parseInt(idUsuario));
                pedidoDAO.crate(pedido, listaCarrito);

                for (Carrito carrito : listaCarrito) {
                    int idProducto = carrito.getIdProducto();
                    int cantidadVendida = carrito.getCantidad();

                    productoDAO.actualizarStock(idProducto, cantidadVendida);
                }

                listaCarrito.clear();
                resp.sendRedirect(req.getContextPath() + "/tienda_animales/pedidos/venta-generada");
            } else {

                req.setAttribute("errorStock", stockSuficiente);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/pedidos/carrito.jsp");
                dispatcher.forward(req, resp);
            }


        } else if (__method__.equals("delete")) {
            int idProducto = Integer.parseInt(req.getParameter("idProducto"));
            System.out.println(idProducto);
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (listaCarrito.get(i).getIdProducto() == idProducto) {
                    listaCarrito.remove(i);
                    item = item - 1;
                }
            }
            resp.sendRedirect(req.getContextPath() + "/tienda_animales/pedidos/carrito");
        }
    }


}
