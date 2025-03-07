package com.ambercff.events_app.security;

import com.ambercff.events_app.infra.exceptions.InvalidTokenException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.security.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        String requestUri = request.getRequestURI();

        try {

            if(requestUri.contains("graphql") ||requestUri.contains("graphiql") ||body.contains("login") || body.contains("register")){
                filterChain.doFilter(requestWrapper, response);
                return;
            }

            String token = getToken(request);

            if(token != null){
                String subject = tokenService.getSubject(token);

                User user = (User) userRepository.findByEmail(subject)
                        .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
                );
            } else {
                throw new InvalidTokenException("Token inválido!");
            }
            filterChain.doFilter(requestWrapper, response);
        } catch(InvalidTokenException e){
            handleException(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido!", "INVALID_TOKEN", e);
        } catch (UserNotFoundException e){
            handleException(response, HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado!", "USER_NOT_FOUND", e);
        }
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.replace("Bearer ", "");
        }
        return null;
    }

    private void handleException(HttpServletResponse response, int status, String message, String code, Exception exception) throws IOException{
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonError = """
                    {
                        "errors": [
                            {
                                "message": "%s",
                                "extensions": {
                                    "code": "%s"
                                }
                            }
                        ]
                    }
                """.formatted(message,code);
        response.getWriter().write(jsonError);
    }
}
