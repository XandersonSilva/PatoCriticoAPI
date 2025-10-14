package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.TagController;
import edu.xanderson.PatoCritico.model.dtos.ReqTagDTO;
import edu.xanderson.PatoCritico.model.dtos.ResTagDTO;
import edu.xanderson.PatoCritico.service.impl.TagServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagControllerImpl implements TagController{
    private final TagServiceImpl tagService;

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResTagDTO> salvaTag(ReqTagDTO dto) {
        return ResponseEntity.ok().body(tagService.salvaTag(dto));
    }

    @Override
    @GetMapping("/todas")
    public ResponseEntity<List<ResTagDTO>> listarTags() {
        return ResponseEntity.ok().body(tagService.listarTags());
    }
}
