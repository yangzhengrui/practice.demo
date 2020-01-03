package com.practice.xmldemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX是一种流式解析XML的API； 
 * SAX通过事件触发，读取速度快，消耗内存少； 
 * 调用方必须通过回调方法获得解析过程中的数据。
 */
public class SAXDemo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        InputStream input = SAXDemo.class.getResourceAsStream("book.xml");
        SAXParserFactory spf =SAXParserFactory.newInstance();
        SAXParser saxParser =spf.newSAXParser();
        saxParser.parse(input, new MyHandler());   
    }
}
class MyHandler extends DefaultHandler{
    public void startDocument() throws SAXException {
        print("start document");
    }

    public void endDocument() throws SAXException {
        print("end document");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        print("start element:",localName,qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException{
        print("end element:",localName,qName);
    }

    public void characters(char[] ch,int start, int length) throws SAXException {
        print("characters:",new String(ch,start,length));
    }

    public void error(SAXException e) throws SAXException {
        print("error:",e);
    }

    void print(Object... objs){
        for (Object obj : objs) {
            System.out.println(obj);
            System.out.println(" ");
        }
        System.out.println();
    }
}