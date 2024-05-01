package br.uni.unibet.UniBet.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/desenv")
public class DesenvolvimentoController {

    static ArrayList<String> desenvolvedores;
    static {
        desenvolvedores = new ArrayList<>();
        desenvolvedores.add("Zezin da Silva");
        desenvolvedores.add("PeDrin Morreira");
        desenvolvedores.add("Gustin Marmelo");
    }

    @GetMapping("/time")
    public String getDesenvTime(){
        String nome = "";
        for (String n: desenvolvedores) {
            nome += n+"; ";
        }
        return nome;
    }

    @GetMapping("/time/{posicao}")
    public String getDesenvTimeOne(@PathVariable int posicao){
        if (posicao >=1 && posicao <= desenvolvedores.size()) {
            return desenvolvedores.get(posicao - 1);
        }else{
            return "indice incorreto";
        }
    }

    @GetMapping("/time/search")
    public ArrayList<String> buscaDesenvolvedor(@RequestParam(required = true) String nome,
                                                @RequestHeader(required = true) String token){

        ArrayList<String> busca = new ArrayList<>();
        if ( token.equals("123")){
            for (String str: desenvolvedores ) {
                if (str.contains(nome)){
                    busca.add(str);
                }
            }
            return busca;            
        }else{
            busca.add("Token incorreto!!!");
            return busca;
        }
    }

    @PostMapping("/time")
    public String saveDesenvolvedores( @RequestBody String nome){
            if (nome != null && !nome.isBlank() && !nome.isEmpty()){
                desenvolvedores.add( nome );
                return "Salvo com sucesso";
            }
            return "Erro ao Salvar";
    }

    @DeleteMapping("/time/{idx}")
    public String deleteDeenvolvedor( @PathVariable int idx ){
        if (idx >= 1 && idx <= desenvolvedores.size()){
            desenvolvedores.remove(idx -1);
            return "desenvolvedor apagado com sucesso";
        }
        return "Erro ao Apagar";
    }

    @PutMapping("/time/{idx}")
    public String editDesenv( @PathVariable int idx,
                              @RequestBody String novoNome  ){
        if (idx >= 1 && idx <= desenvolvedores.size()){
            desenvolvedores.set(idx -1, novoNome);
            return "desenvolvedor alterado com sucesso";
        }
        return "Erro ao Alterar";
    }

}
