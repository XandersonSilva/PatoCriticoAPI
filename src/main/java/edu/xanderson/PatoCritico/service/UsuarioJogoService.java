package edu.xanderson.PatoCritico.service;


import java.util.List;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;

public interface UsuarioJogoService {

    ResJogoDTO tornarJogoDesejo(UUID jogoId);                   //Usuário
    ResJogoDTO tornarJogoJogando(UUID jogoId);                  //Usuário
    ResJogoDTO tornarJogoFinalizado(UUID jogoId);               //Usuário
    ResJogoDTO    removerJogoBibliotecaPessoal(UUID jogoId);    //Usuário
    List<ResJogoDTO> visualizarBibliotecaPessoal();             //Usuário

}