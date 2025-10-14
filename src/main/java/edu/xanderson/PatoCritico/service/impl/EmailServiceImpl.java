package edu.xanderson.PatoCritico.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import edu.xanderson.PatoCritico.model.dtos.ReqEmailDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.DenunciaEntity;
import edu.xanderson.PatoCritico.model.entitys.EmailEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioConfirmacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioRecuperacaoSenhaEntity;
import edu.xanderson.PatoCritico.model.mappers.EmailMapper;
import edu.xanderson.PatoCritico.model.repository.EmailRepository;
import edu.xanderson.PatoCritico.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${prefixo.recuperacao.senha.link}")
    private String prefixoRecuperacao;

    @Override
    @Transactional
    public void enviarEmail(ReqEmailDTO dto) {
        EmailEntity email_db_log = emailMapper.fromReqDto(dto);
        email_db_log.setRemetente(sender);
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "utf-8");

            email.setFrom(sender);
            email.setTo(dto.getDestinatario().getEmail());
            email.setSubject(dto.getAsunto());
            email.setText(dto.getMensagem(), true);

            
            javaMailSender.send(mimeMessage);

            emailRepository.save(email_db_log);
        } catch (Exception e) {
            System.out.println("Erro ao enviar email \n" + e);
        }
    }

    
    @Override
    public void notificarResultadoDenunciaPositivo(DenunciaEntity denuncia) {
        final Context context = new Context();

        context.setVariable("nomeUsuario", denuncia.getDenunciante().getNome());
        context.setVariable("tituloAvaliação", denuncia.getAvaliacao().getTitulo());
        context.setVariable("motivoDenuncia", denuncia.getMotivo());

        String mensagem = templateEngine.process("email/denuncia_positiva.html", context);

        mensagem = mensagem.formatted(denuncia.getAvaliacao().getTitulo());

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Atualização sobre sua denúncia");
        email.setDestinatario(denuncia.getDenunciante());
        email.setMensagem(mensagem);

        enviarEmail(email);
    }

    @Override
    public void notificarResultadoDenunciaNegativo(DenunciaEntity denuncia) {
        
        final Context context = new Context();

        context.setVariable("nomeUsuario", denuncia.getDenunciante().getNome());
        context.setVariable("tituloAvaliacao", denuncia.getAvaliacao().getTitulo());
        context.setVariable("motivoDenuncia", denuncia.getMotivo());
        
        String mensagem = templateEngine.process("email/denuncia_negativa.html", context);

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Atualização sobre sua denúncia");
        email.setDestinatario(denuncia.getDenunciante());
        email.setMensagem(mensagem);

        enviarEmail(email);
    }


    @Override
    public void enviarCodigoValidacaoEmail(UsuarioConfirmacaoEntity confirmacao, int tempoExpiracao) {
        final Context context = new Context();

        context.setVariable("nomeUsuario", confirmacao.getUsuario().getNome());
        context.setVariable("codigoValidacao", confirmacao.getCode());
        context.setVariable("tempoExpiracao", tempoExpiracao);
        
        String mensagem = templateEngine.process("email/codigo_ativacao_usuario.html", context);

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Código de validação");
        email.setDestinatario(confirmacao.getUsuario());
        email.setMensagem(mensagem);

        enviarEmail(email);
    }


    @Override
    public void enviarEmailRecuperarSenha(UsuarioRecuperacaoSenhaEntity recuperacao) {
        final Context context = new Context();

        String linkRecuperacao = prefixoRecuperacao + recuperacao.getToken();

        context.setVariable("nomeUsuario", recuperacao.getUsuario().getNome());
        context.setVariable("linkRecuperacao", linkRecuperacao);


        String mensagem = templateEngine.process("email/alterar_senha.html", context);

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Recuperar Senha");
        email.setDestinatario(recuperacao.getUsuario());
        email.setMensagem(mensagem);

        enviarEmail(email);
    }


    @Override
    public void notificarSenhaAlterada(UsuarioEntity usuario) {
        final Context context = new Context();
        
        context.setVariable("nomeUsuario", usuario.getNome());
        context.setVariable("dataHora", LocalDateTime.now());
        
        String mensagem = templateEngine.process("email/notificacao_alteracao_senha.html", context);

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Senha alterada com sucesso");
        email.setDestinatario(usuario);
        email.setMensagem(mensagem);

        enviarEmail(email);
    }


    @Override
    public void notificarAvaliacaoRemovida(AvaliacaoEntity avaliacao) {
        final Context context = new Context();
        
        context.setVariable("nomeUsuario", avaliacao.getAutor().getNome());
        context.setVariable("tituloAvaliacao", avaliacao.getTitulo());
        
        String mensagem = templateEngine.process("email/notificacao_avaliacao_impropria_removida.html", context);

        ReqEmailDTO email = new ReqEmailDTO();
        email.setAsunto("Avaliação removida");
        email.setDestinatario(avaliacao.getAutor());
        email.setMensagem(mensagem);

        enviarEmail(email);
    }

}
