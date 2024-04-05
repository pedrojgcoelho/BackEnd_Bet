package br.uni.unibet.UniBet.service;

import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.DTO.ApostaInputDTO;
import br.uni.unibet.UniBet.model.Jogo;
import br.uni.unibet.UniBet.model.Usuario;
import br.uni.unibet.UniBet.model.dao.ApostaDAO;
import br.uni.unibet.UniBet.model.dao.JogoDAO;
import br.uni.unibet.UniBet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApostaService {

    @Autowired
    UsuarioDAO udao;
    @Autowired
    JogoDAO jdao;
    @Autowired
    ApostaDAO adao;

    public void criaAposta(ApostaInputDTO aposta) throws Exception {

        //Verificar se o apostador existe
        Optional<Usuario> uExiste = udao.findById( aposta.getIdApostador() );
        if( !uExiste.isPresent() ){
            throw new Exception("Usuário não encontrado!!!");
        }
        if (uExiste.get().getSaldo() < aposta.getValorAposta()){
            throw new Exception("Saldo insuficiente!!!");            
        }
        Optional<Jogo> jExiste = jdao.findById( aposta.getIdJogo() );
        if( !jExiste.isPresent() ){
            throw new Exception("Jogo não encontrado!!!");
        }
        if (jExiste.get().getDataJogo().isBefore(LocalDateTime.now().minusMinutes(30)) ){
            throw new Exception("Apostas Encerradas!!!");            
        }

        Aposta apo = new Aposta(1, aposta.getValorAposta(),
                uExiste.get(), jExiste.get(), aposta.getResultado());
         adao.save(apo);
        uExiste.get().sacar( aposta.getValorAposta() );        
        udao.save( uExiste.get() );

    }
}
