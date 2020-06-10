package kz.eserzhanov.testtask.testtask.controller;

import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.dto.RequestDto;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.mapper.RequestMapper;
import kz.eserzhanov.testtask.testtask.service.RequestService;
import kz.eserzhanov.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/request")
public class RequestRestController {
    private final UserService userService;
    private final RequestService requestService;
    private final MapException mapException;
    private final RequestMapper requestMapper;

    @Autowired
    public RequestRestController(UserService userService, RequestService requestService, MapException mapException, RequestMapper requestMapper) {
        this.userService = userService;
        this.requestService = requestService;
        this.mapException = mapException;
        this.requestMapper = requestMapper;
    }

    @GetMapping
    public ResponseEntity<?> show(@RequestParam int page, Authentication authentication){
        try {
            List<RequestEntity> clientList = requestService.getWithPageable(page-1, userService.getByAuth(authentication)).getContent();
            List<RequestDto> result = new ArrayList<>();
            clientList.forEach(e -> {
                result.add(requestMapper.fromRequest(e));
            });

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }
}
