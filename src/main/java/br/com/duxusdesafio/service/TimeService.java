package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    public Time salvar(Time time, List<Long> idsIntegrantes) {
        List<ComposicaoTime> composicao = new ArrayList<>();
        for (Long id : idsIntegrantes) {
            Integrante  integrante = integranteRepository.findById(id).
                    orElseThrow(() -> new RuntimeException("Integrante não encontrado! ID: " + id));

            ComposicaoTime ct = new ComposicaoTime();
            ct.setTime(time);
            ct.setIntegrante(integrante);

            composicao.add(ct);
        }
        time.setComposicaoTime(composicao);

        return timeRepository.save(time);
    }

    public List<Time> listarTodos(){
        return timeRepository.findAll();
    }

    public Time buscarPorId(Long id){
        return timeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado! ID: " + id));
    }

    public void deletar(Long id){
        timeRepository.deleteById(id);
    }
}
