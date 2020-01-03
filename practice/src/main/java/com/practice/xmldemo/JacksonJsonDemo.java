package com.practice.xmldemo;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON是轻量级的数据表示方式，常用于Web应用；
 * Jackson可以实现JavaBean和JSON之间的转换；
 * 可以通过Module扩展Jackson能处理的数据类型；
 * 可以自定义JsonSerializer和JsonDeserializer来定制序列化和反序列化。
 */
public class JacksonJsonDemo {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        InputStream input= JacksonDemo.class.getResourceAsStream("book.json");
        ObjectMapper mapper = new ObjectMapper();
        // 反序列化时忽略不存在的JavaBean属性:
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);
        String json = mapper.writeValueAsString(book);
        System.out.println(book.id);
        System.out.println(book.name);
        System.out.println(book.author);
        System.out.println(book.isbn);
        System.out.println(book.tags);
        System.out.println(book.pubDate);
        System.out.println(json);
    }
}