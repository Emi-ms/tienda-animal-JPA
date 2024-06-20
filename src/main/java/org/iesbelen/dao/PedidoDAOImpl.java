package org.iesbelen.dao;

import org.iesbelen.model.Carrito;
import org.iesbelen.model.LineaPedido;
import org.iesbelen.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl extends AbstractDAOImpl implements PedidoDAO {
    @Override
    public synchronized void crate(Pedido pedido, List<Carrito> lineasPedido) {
        Connection conn = null;
        PreparedStatement statementPedido = null;
        PreparedStatement statementLineaPedido = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            statementPedido = conn.prepareStatement("INSERT INTO pedidos (idUsuario, fechaPedido,precioTotal) values (?, now(),?)", Statement.RETURN_GENERATED_KEYS);
            statementPedido.setInt(1, pedido.getIdUsuario());
            statementPedido.setDouble(2,pedido.getPrecioTotal());
            statementPedido.executeUpdate();

            rs = statementPedido.getGeneratedKeys();
            if (rs.next()) {
                pedido.setIdPedido(rs.getInt(1));
                statementLineaPedido = conn.prepareStatement("INSERT INTO lineaPedido(idPedido, idProducto, cantidad) values (?,?,?)");
                for (Carrito lineaPedido : lineasPedido) {
                    statementLineaPedido.setInt(1, pedido.getIdPedido());
                    statementLineaPedido.setInt(2, lineaPedido.getIdProducto());
                    statementLineaPedido.setInt(3, lineaPedido.getCantidad());
                    statementLineaPedido.executeUpdate();
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeDb(conn,statementPedido,rs);
            closeDb(conn,statementLineaPedido,rs);
        }

    }

    @Override
    public List<Pedido> getByIdUser(int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM pedidos where idUsuario = ?");
            ps.setInt(1,idUsuario);

            rs = ps.executeQuery();
            while (rs.next()){
               Pedido pedido = new Pedido();
               pedido.setIdPedido(rs.getInt(1));
               pedido.setIdUsuario(rs.getInt(2));
               pedido.setFechaPedido(rs.getDate(3));
               pedido.setPrecioTotal(rs.getDouble(4));

               pedidos.add(pedido);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeDb(conn,ps,rs);
        }
        return pedidos;
    }

    @Override
    public Pedido getByIdPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pedido pedido = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * from pedidos where idPedido = ?");
            ps.setInt(1,idPedido);
            rs = ps.executeQuery();
            if (rs.next()){
                pedido = new Pedido();
                pedido.setIdPedido(rs.getInt(1));
                pedido.setIdUsuario(rs.getInt(2));
                pedido.setFechaPedido(rs.getDate(3));
                pedido.setPrecioTotal(rs.getDouble(4));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeDb(conn,ps,rs);
        }
        return pedido;
    }
}
