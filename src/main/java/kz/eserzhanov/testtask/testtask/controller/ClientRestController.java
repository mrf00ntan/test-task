package kz.eserzhanov.testtask.testtask.controller;

import kz.eserzhanov.testtask.testtask.database.entity.Client;
import kz.eserzhanov.testtask.testtask.dto.ClientDto;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.mapper.ClientMapper;
import kz.eserzhanov.testtask.testtask.service.ClientService;
import kz.eserzhanov.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/client")
public class ClientRestController {
    private final ClientService clientService;
    private final UserService userService;
    private final MapException mapException;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientRestController(ClientService clientService, UserService userService, MapException mapException, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.userService = userService;
        this.mapException = mapException;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClientDto client, Authentication authentication) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client, userService.getByAuth(authentication)));
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientService.delete(clientService.getById(id), userService.getByAuth(authentication)));
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }

    @GetMapping
    public ResponseEntity<?> show(@RequestParam int page, Authentication authentication){
        try {
            List<Client> clientList = clientService.getWithPageable(page-1, userService.getByAuth(authentication)).getContent();
            List<ClientDto> result = new ArrayList<>();
            clientList.forEach(e -> {
                result.add(clientMapper.fromClient(e));
            });

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> search(@RequestParam String value, Authentication authentication){
        try {
            List<ClientDto> result = new ArrayList<>();
            clientService.search(value, userService.getByAuth(authentication)).forEach(e -> {
                result.add(clientMapper.fromClient(e));
            });
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }
}
