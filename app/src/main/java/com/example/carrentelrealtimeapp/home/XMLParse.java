package com.example.carrentelrealtimeapp.home;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParse {
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        }catch (Exception e){
            Log.e("XML Parse: " , e.toString());
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xml));
        inputSource.setEncoding("UTF-8");
        document = builder.parse(inputSource);
        return document;
    }

    public String getValue(Element item, String name){
        NodeList nodeList = item.getElementsByTagName(name);
        return this.getTextNodeValue(nodeList.item(0));
    }

    public final String getTextNodeValue(Node node){
        Node child;
        if(node != null){
            if (node.hasChildNodes()){
                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()){
                    if(child.getNodeType() == Node.TEXT_NODE){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }


}

