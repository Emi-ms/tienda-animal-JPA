package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO {
    @Override
    public void create(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("insert into usuarios (nombre,apellido,dni,domicilio,fechaDeNacimiento,email,password,rol)" +
                    "values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDni());
            ps.setString(4, usuario.getDomicilio());
            ps.setDate(5, (Date) usuario.getFechaDeNacimiento());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getPassword());
            ps.setString(8,usuario.getRol());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
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
    public List<Usuario> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Usuario> listUsuario = new ArrayList<>();

        try {
            conn = connectDB();
            st = conn.createStatement();
            rs = st.executeQuery("select * from usuarios");

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setDni(rs.getString(4));
                usuario.setDomicilio(rs.getString(5));
                usuario.setFechaDeNacimiento(rs.getDate(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setRol(rs.getString(9));
                listUsuario.add(usuario);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, st, rs);
        }
        return listUsuario;
    }

    @Override
    public Optional<Usuario> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("select * from usuarios where idUsuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setDni(rs.getString(4));
                usuario.setDomicilio(rs.getString(5));
                usuario.setFechaDeNacimiento(rs.getDate(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setRol(rs.getString(9));
                return Optional.of(usuario);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("update usuarios set nombre = ?," +
                    "apellido = ?," +
                    "dni = ?," +
                    "domicilio = ?," +
                    "fechaDeNacimiento = ?," +
                    "email = ?," +
                    "password = ?," +
                    "rol = ?" +
                    "where idUsuario = ?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDni());
            ps.setString(4, usuario.getDomicilio());
            ps.setDate(5, (Date) usuario.getFechaDeNacimiento());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getPassword());
            ps.setString(8,usuario.getRol());
            ps.setInt(9, usuario.getIdUsuario());

            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
            ps = conn.prepareStatement("delete from usuarios where idUsuario = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM usuarios WHERE email = ?");

            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setDni(rs.getString(4));
                usuario.setDomicilio(rs.getString(5));
                usuario.setFechaDeNacimiento(rs.getDate(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setRol(rs.getString(9));

                return Optional.of(usuario);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeDb(conn,ps,rs);
        }

        return Optional.empty();
    }


}
