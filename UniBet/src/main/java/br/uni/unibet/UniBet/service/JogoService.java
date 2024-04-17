package br.uni.unibet.UniBet.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.uni.unibet.UniBet.model.ETipoResultado;
import br.uni.unibet.UniBet.model.Jogo;
import br.uni.unibet.UniBet.model.Time;
import br.uni.unibet.UniBet.model.DTO.JogoInputDTO;
import br.uni.unibet.UniBet.model.DTO.JogoUpdateDTO;
import br.uni.unibet.UniBet.model.DTO.JogoViewDTO;
import br.uni.unibet.UniBet.model.dao.JogoDAO;
import br.uni.unibet.UniBet.model.dao.TimeDAO;

@Service
public class JogoService {
    @Autowired
    JogoDAO jdao;

    @Autowired
    TimeDAO tDao;

    public void criarJogo(JogoInputDTO jogo) throws Exception {
        Optional<Time> tAExiste = tDao.findById(jogo.getIdTimeA());
        Optional<Time> tBExiste = tDao.findById(jogo.getIdTimeB());

        if (!tAExiste.isPresent() || !tBExiste.isPresent()) {
            throw new Exception("Não existe esse time!");
        }

        if (jogo.getOddsVitoriaTimeA() <= 0 || jogo.getOddsVitoriaTimeB() <= 0 || jogo.getOddsEmpate() <= 0) {
            throw new Exception("Odd não aceita. Valor aceito >0");
        }

        Jogo novoJogo = new Jogo(
                0,
                jogo.getDataJogo(),
                jogo.getOddsVitoriaTimeA(),
                jogo.getOddsVitoriaTimeB(),
                jogo.getOddsEmpate(),
                tAExiste.get(),
                tBExiste.get(),
                0,
                0,
                ETipoResultado.AGUARDANDO);

        jdao.save(novoJogo);
    }

    public JogoViewDTO getJogo(Integer id) throws Exception {
        Optional<Jogo> jogo = jdao.findById(id);
        if (!jogo.isPresent()) {
            throw new Exception("Não existe esse jogo");
        }

        JogoViewDTO jg = new JogoViewDTO();
        jg.setDataJogo(jogo.get().getDataJogo());
        jg.setOddsVitoriaTimeA(jogo.get().getOddsVitoriaTimeA());
        jg.setOddsVitoriaTimeB(jogo.get().getOddsVitoriaTimeB());
        jg.setOddsEmpate(jogo.get().getOddsEmpate());
        jg.setTimeA(jogo.get().getTimeA().getNome());
        jg.setTimeB(jogo.get().getTimeB().getNome());
        jg.setPontuacaoTimeA(jogo.get().getPontuacaoTimeA());
        jg.setPontuacaoTimeB(jogo.get().getPontuacaoTimeB());
        jg.setResultado(jogo.get().getResultado());

        return jg;
    }

    public void atualizarJogo(Integer id, JogoUpdateDTO jogo) throws Exception {
        Optional<Jogo> jg = jdao.findById(id);
        if (!jg.isPresent()) {
            throw new Exception("Não existe esse jogo");
        }

        jg.get().setDataJogo(jogo.getDataJogo());
        jg.get().setOddsVitoriaTimeA(jogo.getOddsVitoriaTimeA());
        jg.get().setOddsVitoriaTimeB(jogo.getOddsVitoriaTimeB());
        jg.get().setOddsEmpate(jogo.getOddsEmpate());
        jg.get().setPontuacaoTimeA(jogo.getPontuacaoTimeA());
        jg.get().setPontuacaoTimeB(jogo.getPontuacaoTimeB());
        jg.get().setResultado(jogo.getResultado());

        jdao.save(jg.get());
    }
}