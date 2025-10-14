package edu.xanderson.PatoCritico.service;

import edu.xanderson.PatoCritico.model.dtos.ReqEmailDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.DenunciaEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioConfirmacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioRecuperacaoSenhaEntity;

public interface EmailService {

    void enviarEmail(ReqEmailDTO dto); //Sistema
    void notificarResultadoDenunciaPositivo(DenunciaEntity denuncia); //Sistema
    void notificarResultadoDenunciaNegativo(DenunciaEntity denuncia); //Sistema
    void enviarEmailRecuperarSenha(UsuarioRecuperacaoSenhaEntity recuperacao);  //Sistema
    void notificarSenhaAlterada(UsuarioEntity usuario);     //Sistema
    void enviarCodigoValidacaoEmail(UsuarioConfirmacaoEntity confirmacao, int tempoExpiracao); //Sistema
    void notificarAvaliacaoRemovida(AvaliacaoEntity avaliacao); //Sistema
}