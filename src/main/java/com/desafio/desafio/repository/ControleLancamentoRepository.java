package com.desafio.desafio.repository;

import com.desafio.desafio.exception.DesafioException;
import com.desafio.desafio.model.ControleLancamento;
import com.desafio.desafio.model.DadosDomicilioBancario;
import com.desafio.desafio.model.LancamentoContaCorrenteCliente;
import com.desafio.desafio.model.ResultadoBusca;
import com.desafio.desafio.parser.FileParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ControleLancamentoRepository {

    public ResultadoBusca list(){

        JsonObject json = FileParser.getData();
        JsonObject totalControleLancamento = json.getAsJsonObject("totalControleLancamento");

        ResultadoBusca resultadoBusca = new ResultadoBusca();
        resultadoBusca.setLancamentoList(createList());
        resultadoBusca.setQuantidadeLancamentos(totalControleLancamento.get("quantidadeLancamentos").getAsLong());
        resultadoBusca.setQuantidadeRemessas(totalControleLancamento.get("quantidadeRemessas").getAsLong());
        resultadoBusca.setValorLancamentos(totalControleLancamento.get("valorLancamentos").getAsDouble());

        return resultadoBusca;
    }

    public ControleLancamento getByNumeroEvento(Long numeroEvento){
        return createList()
                .stream()
                .filter(c -> c.getNumeroEvento().compareTo(numeroEvento) == 0)
                .findAny()
                .orElse(null);
    }

    private List<ControleLancamento> createList() {

        JsonObject json = FileParser.getData();
        JsonArray array = json.getAsJsonArray("listaControleLancamento");

        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");

        List<ControleLancamento> lancamentoList = new ArrayList<>();

        try{
            for(JsonElement jsonElement : array){

                JsonObject item = jsonElement.getAsJsonObject();

                ControleLancamento cl = new ControleLancamento();
                cl.setDataEfetivaLancamento(sdf.parse(item.get("dataEfetivaLancamento").getAsString()));
                cl.setDataLancamentoContaCorrenteCliente(sdf.parse(item.get("dataLancamentoContaCorrenteCliente").getAsString()));
                cl.setNumeroEvento(item.get("numeroEvento").getAsLong());
                cl.setDescricaoGrupoPagamento(item.get("descricaoGrupoPagamento").toString());
                cl.setCodigoIdentificadorUnico(item.get("codigoIdentificadorUnico").getAsString());
                cl.setNomeBanco(item.get("nomeBanco").getAsString());
                cl.setQuantidadeLancamentoRemessa(item.get("quantidadeLancamentoRemessa").getAsLong());
                cl.setNumeroRaizCNPJ(item.get("numeroRaizCNPJ").getAsString());
                cl.setNumeroSufixoCNPJ(item.get("numeroSufixoCNPJ").getAsString());
                cl.setValorLancamentoRemessa(item.get("valorLancamentoRemessa").getAsDouble());
                cl.setDateLancamentoContaCorrenteCliente(item.get("dateLancamentoContaCorrenteCliente").getAsLong());
                cl.setDateEfetivaLancamento(item.get("dateEfetivaLancamento").getAsLong());

                JsonObject lancamento = item.getAsJsonObject("lancamentoContaCorrenteCliente");
                JsonObject domicilio = lancamento.getAsJsonObject("dadosDomicilioBancario");

                DadosDomicilioBancario ddc = new DadosDomicilioBancario();
                ddc.setCodigoBanco(domicilio.get("codigoBanco").getAsLong());
                ddc.setNumeroAgencia(domicilio.get("numeroAgencia").getAsLong());
                ddc.setNumeroContaCorrente(domicilio.get("numeroContaCorrente").getAsString());

                LancamentoContaCorrenteCliente lcc = new LancamentoContaCorrenteCliente();
                lcc.setNumeroRemessaBanco(lancamento.get("numeroRemessaBanco").getAsLong());
                lcc.setNomeSituacaoRemessa(lancamento.get("nomeSituacaoRemessa").getAsString());
                lcc.setNomeTipoOperacao(lancamento.get("nomeTipoOperacao").getAsString());

                lcc.setDadosDomicilioBancario(ddc);
                cl.setLancamentoContaCorrenteCliente(lcc);

                lancamentoList.add(cl);
            }
        }catch (Exception e){
            throw new DesafioException(e);
        }

        return lancamentoList;
    }
}
