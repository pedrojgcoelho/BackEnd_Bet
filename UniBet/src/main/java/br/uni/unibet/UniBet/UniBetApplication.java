package br.uni.unibet.UniBet;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.uni.unibet.UniBet.model.ETipoResultado;
import br.uni.unibet.UniBet.model.Jogo;
import br.uni.unibet.UniBet.model.Time;
import br.uni.unibet.UniBet.model.Usuario;
import br.uni.unibet.UniBet.model.dao.JogoDAO;
import br.uni.unibet.UniBet.model.dao.TimeDAO;
import br.uni.unibet.UniBet.model.dao.UsuarioDAO;

@SpringBootApplication
public class UniBetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UniBetApplication.class, args);
	}
	@Autowired
	TimeDAO dTimeDAO;
	@Autowired
	UsuarioDAO dUser;
	@Autowired
	JogoDAO dJogo;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("###### Iniciando o carregamento dos dados");
		Time time = new Time(1, "Cruzeiro",null,null);
		Time time1 = new Time(2, "Patético MG",null,null);
		dTimeDAO.save(time);
		dTimeDAO.save(time1);

		Usuario u = new Usuario(1, "Zezin da Silva", "Ze", "123", "ze@hotmail.com", 500, true, null);
		Usuario u1 = new Usuario(2, "Jãozin de Souza", "Jao", "123", "jao@hotmail.com", 200, false, null);
		dUser.save(u);
		dUser.save(u1);
		Jogo j1 = new Jogo(1, LocalDateTime.of(2024,04,05,20,30)
				,0.8,0.2,0.1, time, time1,
				0, 0,
				ETipoResultado.AGUARDANDO);
		Jogo j2 = new Jogo(2, LocalDateTime.now()
				,0.5,0.7,0.3, time, time1,
				0, 0,
				ETipoResultado.VITORIA_A);

		dJogo.save( j1 );
		dJogo.save( j2 );
	}

}
