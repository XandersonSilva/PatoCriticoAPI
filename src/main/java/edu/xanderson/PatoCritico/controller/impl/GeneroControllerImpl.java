package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.GeneroController;
import edu.xanderson.PatoCritico.model.dtos.ReqGeneroDTO;
import edu.xanderson.PatoCritico.model.dtos.ResGeneroDTO;
import edu.xanderson.PatoCritico.service.impl.GeneroServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/genero")
@RequiredArgsConstructor
public class GeneroControllerImpl implements GeneroController{
    private final GeneroServiceImpl generoService;

    @Override
    @PostMapping("salvar")
    public ResponseEntity<ResGeneroDTO> salvaGenero(@RequestBody @Validated ReqGeneroDTO dto) {
        ResGeneroDTO resDto = generoService.salvaGenero(dto);
        return ResponseEntity.ok().body(resDto);
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<ResGeneroDTO>> listarGeneros() {
        List<ResGeneroDTO> resDto = generoService.listarGeneros();
        return ResponseEntity.ok().body(resDto);
    }


}
