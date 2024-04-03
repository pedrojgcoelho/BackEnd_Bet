package br.uni.unibet.UniBet.service;

import br.uni.unibet.UniBet.model.Time;
import br.uni.unibet.UniBet.model.dao.TimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    TimeDAO tDao;

    public Time verificaSalvamento(Time time) throws Exception {

        if (time.getNome().isEmpty() || time.getNome().isBlank()){
            throw new Exception("nome do time não pode ser vazio");
        }
        Time t = tDao.findByNome(time.getNome());
        if (t != null){
            throw new Exception("time "+t.getNome()+" já está cadastrado");
        } else {
            return tDao.save(time);
        }
    }

    public List<Time> buscarTodos() {
        return  tDao.findAll();
    }

    public void apagaTime(int id) throws Exception {
        Optional<Time> t = tDao.findById( id );
        if ( t.isPresent() ){
           tDao.delete( t.get() );
        } else {
            throw new Exception("time "+id+" não existe");
        }
    }

    public Time buscar(int id) {
        Optional<Time> t = tDao.findById( id );
        if ( t.isPresent() ){
            return  t.get();
        }else{
            return null;
        }
    }

    public Time alteraTime(int id, Time time) throws Exception {
        Optional<Time> t = tDao.findById( id );
        if ( ! t.isPresent() ){
            throw new Exception("time "+id+" não existe");
        }
        if (time.getNome().isEmpty() || time.getNome().isBlank()){
            throw new Exception("nome do time não pode ser vazio");
        }
        Time timeExiste = tDao.findByNome(time.getNome());
        if (timeExiste != null){
            throw new Exception("time "+timeExiste.getNome()+" já cadastrado");
        } 
        Time timeAlterado = t.get();
        timeAlterado.setNome( time.getNome() );
         return tDao.save( timeAlterado );
    }
}
