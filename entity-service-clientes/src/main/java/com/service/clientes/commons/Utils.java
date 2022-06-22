package com.service.clientes.commons;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class Utils {

    @Value("classpath:clientes.json")
    private Resource resource;

    @Autowired
    ResourceLoader resourceLoader;

    public String readJson() throws IOException {
        return IOUtils.toString(resource.getInputStream(),StandardCharsets.UTF_8.name());
    }

    public <T> void writeJson(T t) throws IOException {
        FileOutputStream outputStream =  new FileOutputStream(resource.getFile().getPath());
        String json = new Gson().toJson(t);
        outputStream.write(json.getBytes());
        outputStream.close();
    }

}
