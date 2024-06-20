package org.iesbelen.dao;

import org.iesbelen.model.LineaPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LineaPedidoDAOImpl extends AbstractDAOImpl implements LineaPedidoDAO {
    @Override
    public void crate(LineaPedido lineaPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO  lineaPedido (idPedido, idProducto, cantidad) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lineaPedido.getIdPedido());
            ps.setInt(2, lineaPedido.getIdProducto());
            ps.setInt(3, lineaPedido.getCantidad());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lineaPedido.setIdLineaPedido(rs.getInt(1));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }


    }

    @Override
    public List<LineaPedido> getLineasPorPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LineaPedido> lineasPedido = new ArrayList<>();

        try {
            conn = connectDB();
            ps = conn.prepareStatement("select * from lineaPedido where idPedido = ?");
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setIdLineaPedido(rs.getInt(1));
                lineaPedido.setIdPedido(rs.getInt(2));
                lineaPedido.setIdProducto(rs.getInt(3));
                lineaPedido.setCantidad(rs.getInt(4));

                lineasPedido.add(lineaPedido);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }

        return lineasPedido;
    }

    @Override
    public Optional<String> getNombreProducto(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT nombre from productos where idProducto = ?");

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
}
