package com.practice.xmldemo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Java提供的DOM API可以将XML解析为DOM结构，以Document对象表示；
 * DOM可在内存中完整表示XML数据结构；
 * DOM解析速度慢，内存占用大
 */
public class DocumentDemo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        InputStream input=DocumentDemo.class.getResourceAsStream("book.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc=db.parse(input);
        printNode(doc, 0);
    }
    static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
        case Node.DOCUMENT_NODE: // Document节点
            System.out.println("Document: " + n.getNodeName());
            break;
        case Node.ELEMENT_NODE: // 元素节点
            System.out.println("Element: " + n.getNodeName());
            break;
        case Node.TEXT_NODE: // 文本
            System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
            break;
        case Node.ATTRIBUTE_NODE: // 属性
            System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
            break;
        default: // 其他
            System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent + 1);
        }
    }
}