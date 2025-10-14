package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.PlataformaController;
import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResPlataformaDTO;
import edu.xanderson.PatoCritico.service.impl.PlataformaServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plataforma")
@RequiredArgsConstructor
public class PlataformaControllerImpl implements PlataformaController{
    private final PlataformaServiceImpl plataformaService;

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResPlataformaDTO> salvaPlataforma(@RequestBody @Validated ReqPlataformaDTO dto) {
        return ResponseEntity.ok().body(plataformaService.salvaPlataforma(dto));
    }

    @Override
    @GetMapping("todas")
    public ResponseEntity<List<ResPlataformaDTO>> listarPlataformas() {
        return ResponseEntity.ok().body(plataformaService.listarPlataformas());
    }
    
    
}
