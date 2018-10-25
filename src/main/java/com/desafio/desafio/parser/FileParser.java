package com.desafio.desafio.parser;

import com.desafio.desafio.exception.DesafioException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileParser {

    private static Logger logger = LoggerFactory.getLogger(FileParser.class);

    private static JsonObject data;

    public static JsonObject getData(){

        logger.info("JSON data requested...");

        if(data != null){
            logger.info("Data already loaded in memory, returning...");
            return data;
        }

        logger.info("Data not loaded, loading from file...");

        try{
            String content = new String(Files.readAllBytes(Paths.get("data.json")));

            data = new JsonParser().parse(content).getAsJsonObject();

            logger.info("Data loaded with success, returning");

            return data;
        }catch(Exception e){
            logger.error("There was an error when loading the file {}", e.getMessage());

            throw new DesafioException(e);
        }

    }

}
