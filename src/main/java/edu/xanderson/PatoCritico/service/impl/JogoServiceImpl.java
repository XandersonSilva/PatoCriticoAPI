package edu.xanderson.PatoCritico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqTagJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.GeneroEntity;
import edu.xanderson.PatoCritico.model.entitys.JogoEntity;
import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;
import edu.xanderson.PatoCritico.model.entitys.TagEntity;
import edu.xanderson.PatoCritico.model.enums.PlataformaRepositiory;
import edu.xanderson.PatoCritico.model.mappers.AvaliacaoMapper;
import edu.xanderson.PatoCritico.model.mappers.JogoMapper;
import edu.xanderson.PatoCritico.model.repository.GeneroRepository;
import edu.xanderson.PatoCritico.model.repository.JogoRepository;
import edu.xanderson.PatoCritico.model.repository.TagRepository;
import edu.xanderson.PatoCritico.service.JogoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JogoServiceImpl implements JogoService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final JogoRepository jogoRepository;
    private final JogoMapper jogoMapper;
    private final AvaliacaoMapper avaliacaoMapper;
    private final GeneroRepository generoRepository;
    private final TagRepository tagRepository;
    private final PlataformaRepositiory plataformaRepositiory;


    @Override
    public ResJogoDTO cadastrarJogo(ReqJogoDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        var jogo = jogoMapper.toEntity(dto);
        jogoRepository.save(jogo);

        ResJogoDTO jogoDTO = jogoMapper.toResDTO(jogo);
        jogoDTO.setNotaMedia(notaMediaJogo(jogo));

        return jogoDTO;
    }


    @Override
    public ResJogoDTO editarJogo(ReqJogoDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();


        JogoEntity jogo = validarJogoId(dto);
        
        jogoMapper.updateFromDto(dto, jogo);
        jogoRepository.save(jogo);
        
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    public ResJogoDTO adicionarTagJogo(ReqTagJogoDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();
        
        Optional<TagEntity> tag = tagRepository.findById(dto.getTag());

        if (!tag.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id informado não corresponde a nem uma tag no sistema!"
        );        

        Optional<JogoEntity> jogo = jogoRepository.findById(dto.getJogo());
        if (!jogo.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id informado não corresponde a nem um jogo no sistema!"
        );
        JogoEntity jogo_edt = jogo.get();

        jogo_edt.getTags().add(tag.get());
        jogoRepository.save(jogo_edt);

        return jogoMapper.toResDTO(jogo_edt);
    }


    @Override
    public ResJogoDTO informarGeneroJogo(ReqGeneroJogoDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        Optional<GeneroEntity> genero = generoRepository.findById(dto.getGenero());
        if (!genero.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id informado não corresponde a nem um genero no sistema!"
        );


        Optional<JogoEntity> jogo = jogoRepository.findById(dto.getJogo());
        if (!jogo.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id informado não corresponde a nem um jogo no sistema!"
        );
        JogoEntity jogo_edt = jogo.get();
        jogo_edt.setGenero(genero.get());

        jogoRepository.save(jogo_edt);

        return jogoMapper.toResDTO(jogo_edt);
    }


    @Override
    public ResJogoDTO apagarJogo(UUID jogoId) {
        usuarioAutenticado.verificaPapelAdmin();


        ReqJogoDTO dto = new ReqJogoDTO();
        dto.setId(jogoId);
        JogoEntity jogo = validarJogoId(dto);
        jogoRepository.delete(jogo);

        return jogoMapper.toResDTO(jogo);
    }


    @Override
    public List<ResJogoDTO> visualizarCatalogo() {
        return jogoMapper.toDTOList(jogoRepository.findAll());
    }


    @Override
    public ResJogoDTO buscarJogo(UUID jogoId) {
        ReqJogoDTO dto = new ReqJogoDTO();
        dto.setId(jogoId);
        JogoEntity jogo = validarJogoId(dto);
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    public List<ResAvaliacaoDTO> buscarTodasAvaliacoes(UUID jogoId) {
        ReqJogoDTO dto = new ReqJogoDTO();
        dto.setId(jogoId);
        JogoEntity jogo = validarJogoId(dto);
        
        return avaliacaoMapper.toResDtoList(jogo.getAvaliacoes());
    }

    private JogoEntity validarJogoId(ReqJogoDTO dto){
        if (dto.getId() == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id do jogo deve ser informado para edita-lo!"
        );

        JogoEntity jogo = jogoRepository.findById(dto.getId())
                                        .orElseThrow(() -> new ResponseStatusException(
                                            HttpStatus.BAD_REQUEST,
                                            "O jogo indicado não existe!"
                                        ));

        return jogo;
    }

    protected double notaMediaJogo(JogoEntity jogo){
        int contador = 0;
        double notaMediaJogo = 0;

        List<AvaliacaoEntity> avaliacoes = jogo.getAvaliacoes();
        if (avaliacoes == null) return notaMediaJogo;

        for (AvaliacaoEntity avaliacao : avaliacoes) {
            notaMediaJogo += avaliacao.getNota();
            contador ++;
        }

        if (contador > 0) notaMediaJogo = notaMediaJogo/contador;

        return notaMediaJogo;
    }


    @Override
    public ResJogoDTO adicionarPlataformaJogo(ReqPlataformaDTO dto) {
        PlataformaEntity plataforma = plataformaRepositiory.findByPlataforma(dto.getPlataforma());
        JogoEntity jogo = jogoRepository.getReferenceById(dto.getJogoId());

        if (plataforma == null || jogo == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "plataforma não encontrada!"
        );

        jogo.getPlataformas().add(plataforma);
        jogoRepository.save(jogo);
    
        return jogoMapper.toResDTO(jogo);
    }

}
