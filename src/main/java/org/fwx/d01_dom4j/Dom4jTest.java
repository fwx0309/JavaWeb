package org.fwx.d01_dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName Dom4jTest
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/1 8:13
 * @Version 1.0
 */
public class Dom4jTest {

    @Test
    public void SAXReaderTest() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/main/resources/books.xml");
        System.out.println("document = " + document);
    }

    @Test
    public void Dom4jTest() throws Exception {
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read("src/main/resources/books.xml");
//        System.out.println("document = " + document);

        Element rootElement = document.getRootElement();
//        System.out.println("rootElement = " + rootElement.asXML());

        List<Element> bookList = rootElement.elements("book");
        for (Element book : bookList) {
//            System.out.println("book = " + book.asXML());
            // 获取标签内容
            Element name = book.element("name");
            System.out.println("name = " + name.getText());

            // 获取标签属性上的值
            Attribute sn = book.attribute("sn");
            System.out.println("sn = " + sn.getText());
        }
    }
}
