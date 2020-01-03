package com.practice.xmldemo;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonDemo {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        InputStream input= JacksonDemo.class.getResourceAsStream("book.xml");
        JacksonXmlModule module=new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Book book = mapper.readValue(input,Book.class);
        System.out.println(book.id);
        System.out.println(book.name);
        System.out.println(book.author);
        System.out.println(book.isbn);
        System.out.println(book.tags);
        System.out.println(book.pubDate);
    }
    
}
