package org.example.web;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/clients")
@Api( tags = "Clients")
public class ClientsRestController {

    @ApiOperation(value = "This method is used to get the clients.")
    @GetMapping
    public List<String> getClients() {
        return Arrays.asList("First Client", "Second Client");
    }

}
