package com.mcamelo.simiosHuman.resources;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =
@Info(title = "simiosHuman Api"
        , description = "This project was made by challenge of MercadoLivre company."
        , version = "1.3"),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Enviromment Local"),
                @Server(url = "http://10.10.10.10:8080/", description = "Enviromment Remote")
        })
public interface InfoAPI {
}
