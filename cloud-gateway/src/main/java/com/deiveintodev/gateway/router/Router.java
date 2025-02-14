package com.deiveintodev.gateway.router;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Router {
    public static final List<String> apiPaths = List.of("/auth/register-user","/auth/generate-token","/eureka");

    public Predicate<ServerHttpRequest> isSecured =
            req -> apiPaths.stream()
                    .noneMatch(uri -> req.getURI().getPath().contains(uri));

}
