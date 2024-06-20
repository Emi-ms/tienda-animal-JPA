package org.iesbelen.dao;

import org.iesbelen.model.Producto;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO {
    public ProductoDAOImpl() {
    }

    @Override
    public synchronized void create(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO productos (nombre, precio, foto, idCategoria, stock, detalle) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setString(idx++, producto.getFoto());
            ps.setInt(idx++, producto.getIdCategoria());
            ps.setInt(idx++, producto.getStock());
            ps.setString(idx, producto.getDetalle());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Producto> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Producto> listProd = new ArrayList<>();

        try {
            conn = connectDB();
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * from productos");

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setFoto(rs.getString(4));
                producto.setIdCategoria(rs.getInt(5));
                producto.setStock(rs.getInt(6));
                producto.setDetalle(rs.getString(7));

                listProd.add(producto);

            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, st, rs);
        }
        return listProd;
    }

    @Override
    public Optional<Producto> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("select * from productos where idProducto = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setFoto(rs.getString(4));
                producto.setIdCategoria(rs.getInt(5));
                producto.setStock(rs.getInt(6));
                producto.setDetalle(rs.getString(7));
                return Optional.of(producto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void update(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("update productos set nombre = ?, precio = ?, foto = ?, idCategoria = ?, stock = ?, detalle = ? where idProducto = ?");

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getFoto());
            ps.setInt(4, producto.getIdCategoria());
            ps.setInt(5, producto.getStock());
            ps.setString(6, producto.getDetalle());
            ps.setInt(7, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("delete from productos where idProducto = ?");
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public Optional<String> getNombreCategoria(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT nombre from categorias where idCategoria = ?");

            int idx = 1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString(1);
                return Optional.of(result);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }


        return Optional.empty();
    }

    @Override
    public void actualizarStock(int id, int cantidadVendida) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("UPDATE productos SET stock = stock - ? WHERE idProducto = ?");

            ps.setInt(1, cantidadVendida);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public boolean verificarStockSuficiente(int id, int cantidadVendida) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT stock FROM productos WHERE idProducto = ? AND stock >= ?");
            ps.setInt(1, id);
            ps.setInt(2, cantidadVendida);

            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }
    }
}



