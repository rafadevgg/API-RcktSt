package br.com.rafadevgg.todolist.configuration;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rafadevgg.todolist.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Base64;

@Component
public class FilterAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/api/v1/tasks")) {
            var authorization = request.getHeader("Authorization");
            if (authorization == null || authorization.isBlank()) {
                response.sendError(401, "Header Authorization não informado");
                return;
            }

            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401, "Usuário não encontrado");
                return;
            }

            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (passwordVerify.verified) {
                request.setAttribute("idUser", user.getId());
                filterChain.doFilter(request, response);
            }

            else {
                response.sendError(401, "Senha incorreta");
            }
        }

        else {
            filterChain.doFilter(request, response);
        }
    }
}
