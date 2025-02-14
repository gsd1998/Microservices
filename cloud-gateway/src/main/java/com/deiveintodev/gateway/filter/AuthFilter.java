package com.deiveintodev.gateway.filter;

import com.deiveintodev.gateway.jwtService.JwtUtilService;
import com.deiveintodev.gateway.router.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private Router router;

    @Autowired
    private JwtUtilService jwtUtilService;

    public AuthFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String token ="";
            ServerHttpRequest request=null;
            if(router.isSecured.test(exchange.getRequest())){
                if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    String authHeader  = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if(authHeader != null && authHeader.startsWith("Bearer ")){
                        token = authHeader.substring(7);
                    }
                    try {
                        jwtUtilService.validateToken(token);
                        String username = jwtUtilService.extractUserNameFromToken(token);

                        request = exchange.getRequest().mutate().header("username", username).build();
                    }catch (Exception e){
                        throw new RuntimeException("Error validating the token");
                    }
                }else{
                    throw new RuntimeException("auth header missing : unauthorized access");
                }
            }
        return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config{

    }
}
