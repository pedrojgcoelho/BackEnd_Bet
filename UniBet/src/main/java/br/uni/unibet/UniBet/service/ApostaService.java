package br.uni.unibet.UniBet.service;

import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.DTO.ApostaInputDTO;
import br.uni.unibet.UniBet.model.DTO.ApostaViewDTO;
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

    public ApostaViewDTO getAposta(Integer id) throws Exception {

        Optional<Aposta> aposta = adao.findById(id);
        if (! aposta.isPresent()){
            throw new Exception("Aposta "+id+" não encontrada");
        }
        ApostaViewDTO ap = new ApostaViewDTO();
        ap.setId( aposta.get().getId() );
        ap.setDataJogo( aposta.get().getJogo().getDataJogo() );
        ap.setValorAposta( aposta.get().getValorAposta() );
        ap.setIdJogador( aposta.get().getJogador().getId() );
        ap.setIdTime1( aposta.get().getJogo().getTimeA().getId() );
        ap.setIdTime2( aposta.get().getJogo().getTimeB().getId() );
        ap.setNomeJogador( aposta.get().getJogador().getNome() );
        ap.setTime1( aposta.get().getJogo().getTimeA().getNome() );
        ap.setTime2( aposta.get().getJogo().getTimeB().getNome() );
        ap.setResultadoApostado( aposta.get().getAposta() );
        ap.setResultadoJogo( aposta.get().getJogo().getResultado() );
        ap.setAcertou( aposta.get().getAposta() == aposta.get().getJogo().getResultado() );

        return ap;
    }
    public List<ApostaViewDTO> getApostaUsuario(Integer id) {
        
        ta = adao.findbyJogadorIdAndJogoResultado(id, ETipoResultado.AGAUARDANDO);
}
