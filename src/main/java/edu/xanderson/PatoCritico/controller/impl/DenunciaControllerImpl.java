package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.DenunciaController;
import edu.xanderson.PatoCritico.model.dtos.ReqDenunciaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResDenunciaDTO;
import edu.xanderson.PatoCritico.service.impl.DenunciaServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/denuncia")
@RequiredArgsConstructor
public class DenunciaControllerImpl implements DenunciaController{
    private final DenunciaServiceImpl denunciaService;

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResDenunciaDTO> denunciarAvaliacao(@RequestBody @Validated ReqDenunciaDTO dto) {
        ResDenunciaDTO resDto = denunciaService.denunciarAvaliacao(dto);
        return ResponseEntity.ok().body(resDto);
    }

    @Override
    @GetMapping("/todas")
    public ResponseEntity<List<ResDenunciaDTO>> visualizarDenuncias() {
        List<ResDenunciaDTO> resDto = denunciaService.visualizarDenuncias();
        return ResponseEntity.ok().body(resDto);
    }

    @Override
    @DeleteMapping("/{denunciaId}/rejeitarDenuncia")
    public ResponseEntity<ResDenunciaDTO> rejeitarDenuncia(@PathVariable @Validated UUID denunciaId) {
        ResDenunciaDTO resDto = denunciaService.rejeitarDenuncia(denunciaId);
        return ResponseEntity.ok().body(resDto);
    }

    @Override
    @DeleteMapping("/{denunciaId}/aceitarDenuncia")
    public ResponseEntity<ResDenunciaDTO> aceitarDenuncia(@PathVariable @Validated UUID denunciaId) {
        ResDenunciaDTO resDto = denunciaService.aceitarDenuncia(denunciaId);
        return ResponseEntity.ok().body(resDto);
    }
    
}
