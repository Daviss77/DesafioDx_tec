package br.com.duxusdesafio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cadastro-integrante")
    public String telaIntegrante() {
        return "integrante";
    }

    @GetMapping("/montar-time")
    public String telaTime() {
        return "time";
    }
}