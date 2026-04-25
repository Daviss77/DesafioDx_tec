package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeDto;
import br.com.duxusdesafio.dto.TimeResponseDto;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping
    public TimeResponseDto salvar(@RequestBody TimeDto dto){

        Time time = new Time();
        time.setNomeDoClube(dto.getNomeDoClube());
        time.setData(dto.getData());

        Time salvo = timeService.salvar(time, dto.getIdsIntegrantes());

        //Conversão dados
        TimeResponseDto response = new TimeResponseDto();
        response.setNomeDoClube(salvo.getNomeDoClube());
        response.setData(salvo.getData());

        List<String> nomes = new ArrayList<>();
        for(ComposicaoTime ct : salvo.getComposicaoTime()){
            nomes.add(ct.getIntegrante().getNome());
        }
        response.setIntegrantes(nomes);

        return response;
    }

    @GetMapping
    public List<TimeResponseDto> listar(){
        return timeService.listarTodos()
                .stream()
                .map(timeService::converterParaDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TimeResponseDto buscar(@PathVariable Long id){
        return timeService.converterParaDto(
                timeService.buscarPorId(id)
        );
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        timeService.deletar(id);
    }
}
