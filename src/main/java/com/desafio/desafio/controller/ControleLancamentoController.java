package com.desafio.desafio.controller;

import com.desafio.desafio.model.ControleLancamento;
import com.desafio.desafio.model.ResultadoBusca;
import com.desafio.desafio.repository.ControleLancamentoRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controle-lancamento")
public class ControleLancamentoController {

    @Autowired
    private ControleLancamentoRepository controleLancamentoRepository;

    @RequestMapping(path = "/")
    public ResponseEntity list() throws Exception{
        JsonObject result = new JsonObject();

        return new ResponseEntity(controleLancamentoRepository.list(), getHeaders(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}")
    public ControleLancamento get(@PathVariable Long id){
        return controleLancamentoRepository.getByNumeroEvento(id);
    }

    private HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");

        return httpHeaders;
    }

}
