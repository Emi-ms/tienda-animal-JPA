package org.iesbelen.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter(urlPatterns = {"/tienda_animales/usuarios/*", "/tienda_animales/productos/*", "/tienda_animales/categorias/*"},
        initParams = {
                @WebInitParam(name = "acceso-concedido-a-rol", value = "administrador")
        })
public class AdminFilter extends HttpFilter {

        private String rolAcceso;

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
                this.rolAcceso = filterConfig.getInitParameter("acceso-concedido-a-rol");
        }

        @Override
        protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

                HttpSession session = req.getSession();

                String url = req.getRequestURL().toString();
                Usuario usuario = (Usuario) session.getAttribute("usuario-logeado");

                if(usuario != null && usuario.getRol().equals(rolAcceso)){
                        chain.doFilter(req,res);
                }else if(usuario != null){
                        res.sendRedirect(req.getContextPath());
                }else{
                        res.sendRedirect(req.getContextPath()+"/tienda_animales/login");
                }

        }
}
