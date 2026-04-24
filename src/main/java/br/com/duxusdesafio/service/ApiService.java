package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */

    /*Fazendo pequenas verificações com exceções, como forma de cleanCode e no final fazendo foreach com a inserção do time na data correspondente*/
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        if (data == null){
            throw new IllegalArgumentException("Data não pode  ser nula!");
        }
        if (todosOsTimes == null){
            throw new IllegalArgumentException("Lista de times não pode ser nula!");
        }
            for(Time time : todosOsTimes){
                if (time.getData().equals(data)){
                    return time;
                }
            }

        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){

        if(dataInicial == null || dataFinal == null || todosOsTimes == null ){
                throw new IllegalArgumentException("Lista de times não pode ser nula");
            }

            Map<Integrante, Integer> contagem = new HashMap<>();

            //Filtrando times por data e conta quantos integrantes aparece
            for (Time time : todosOsTimes) {
                LocalDate data = time.getData();

                if (!isTimeValido(time, dataInicial, dataFinal)) continue;

                for(ComposicaoTime composicao : time.getComposicaoTime()){
                    Integrante integrante = composicao.getIntegrante();
                    contagem.put(integrante, contagem.getOrDefault(integrante, 0) + 1);
                }
            }

            return obterMaisRecorrente(contagem);
        }
    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente dentro do período.
     * OBS: Time é o clube + composição em determinada data
     */
    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if (todosOsTimes == null){
            throw new IllegalArgumentException("Lista de times não pode ser nula");
        }
        Map<String, Integer> contagemClubes = new HashMap<>();

        //clubes no períodos
        for(Time time : todosOsTimes){
            LocalDate data = time.getData();
            if(!isTimeValido(time, dataInicial, dataFinal)) continue;

            String clube = time.getNomeDoClube();
            contagemClubes.put(clube, contagemClubes.getOrDefault(clube, 0) + 1);
        }

        if (contagemClubes.isEmpty()) return null;

        //Descobre clube mais recorrente
        String clubeMaisRecorrente = null;
        int maiorContagem = 0;

        for (Map.Entry<String, Integer> entry : contagemClubes.entrySet()) {
            if(entry.getValue() > maiorContagem){
                maiorContagem = entry.getValue();
                clubeMaisRecorrente = entry.getKey();
            }
        }

        //Pega time mais recente
        Time timeMaisRecente = null;

        for(Time time : todosOsTimes){
            if(!isTimeValido(time, dataInicial, dataFinal)) continue;

            if(clubeMaisRecorrente.equals(time.getNomeDoClube())){
                if(timeMaisRecente == null || time.getData().isAfter(timeMaisRecente.getData())){
                    timeMaisRecente = time;
                }
            }
        }

        if (timeMaisRecente == null) return null;

        List<String> nomes = new ArrayList<>();

            for(ComposicaoTime composicao : timeMaisRecente.getComposicaoTime()){
                nomes.add(composicao.getIntegrante().getNome());
            }
        return nomes;
    }




    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
            if (todosOsTimes == null) {
                throw new IllegalArgumentException("Lista de times não pode ser nula");
            }

            Map<String, Integer> contagemFuncoes = new HashMap<>();

            for(Time time : todosOsTimes){
               if (time == null || time.getData() == null) continue;

               LocalDate data = time.getData();
               if(dataInicial != null && data.isBefore(dataInicial)) continue;
               if (dataFinal != null && data.isAfter(dataFinal)) continue;

                List<ComposicaoTime> composicoes = time.getComposicaoTime();
                if (composicoes == null) continue;

                for (ComposicaoTime composicao : composicoes){
                    if(composicao == null || composicao.getIntegrante() == null) continue;

                    String funcao = composicao.getIntegrante().getFuncao();
                    if (funcao == null) continue;

                    contagemFuncoes.put(funcao, contagemFuncoes.getOrDefault(funcao, 0) + 1);
                }
            }

            return obterMaisRecorrente(contagemFuncoes);
    }

    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        if (todosOsTimes == null) {
            throw new IllegalArgumentException("Lista não pode ser nula");
        }
        Map<String, Integer> contagemClubes = new HashMap<>();

        for(Time time : todosOsTimes){
            if (!isTimeValido(time, dataInicial, dataFinal)) continue;

            String clube = time.getNomeDoClube();
            if(clube == null) continue;

            contagemClubes.put(clube, contagemClubes.getOrDefault(clube, 0) + 1);
        }
        return obterMaisRecorrente(contagemClubes);
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }



    private boolean isTimeValido(Time time, LocalDate dataInicial, LocalDate dataFinal){
        if (time == null || time.getData() == null) return false;

        LocalDate data = time.getData();

        if(dataInicial != null && data.isBefore(dataInicial)) return false;
        if(dataFinal != null && data.isAfter(dataFinal)) return false;

        return true;
    }

    private <T> T obterMaisRecorrente(Map<T, Integer> contagem){
        if(contagem == null || contagem.isEmpty()) return null;

        T maisRecorrente = null;
        int maiorContagem = 0;

        for(Map.Entry<T, Integer> entry : contagem.entrySet()){
            if(entry.getValue() > maiorContagem){
                maiorContagem = entry.getValue();
                maisRecorrente = entry.getKey();
            }
        }
        return maisRecorrente;
    }


}
