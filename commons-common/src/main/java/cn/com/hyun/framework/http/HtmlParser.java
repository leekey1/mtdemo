package cn.com.hyun.framework.http;

import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    public static List<TagNode> parseNode(String content, String xpath) {
        Object[] result = getByXPath(content, xpath);

        List<TagNode> tagNodeList = new ArrayList<>();
        for (Object obj : result) {
            tagNodeList.add((TagNode) obj);
        }

        return tagNodeList;
    }

    private static Object[] getByXPath(String content, String xpath) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode html = htmlCleaner.clean(content);
        return evaluateXPath(xpath, html);
    }

    public static String parseText(String content, String xpath) {
        Object[] children = getByXPath(content, xpath);

        if (null == children || children.length <= 0) {
            return StringUtils.EMPTY;
        }

        TagNode tagNode = (TagNode) children[0];
        return tagNode.getText().toString().trim();
    }

    public static String parseText(TagNode tagNode, String xpath) {
        Object[] children = evaluateXPath(xpath, tagNode);
        if (null == children || children.length <= 0) {
            return StringUtils.EMPTY;
        }

        Object tag = children[0];
        return ((TagNode) tag).getText().toString().trim();
    }

    public static String parseAttribute(String content, String xpath, String attribute) {
        Object[] children = getByXPath(content, xpath);

        if (null == children || children.length <= 0) {
            return StringUtils.EMPTY;
        }

        TagNode tagNode = (TagNode) children[0];
        return tagNode.getAttributeByName(attribute).trim();
    }

    public static String parseAttribute(TagNode tagNode, String xpath, String attribute) {
        Object[] children = evaluateXPath(xpath, tagNode);
        if (null == children || children.length <= 0) {
            return StringUtils.EMPTY;
        }

        Object tag = children[0];
        return ((TagNode) tag).getAttributeByName(attribute);
    }

    public static String parseAttribute(TagNode tagNode, String attribute) {
        return tagNode.getAttributeByName(attribute).trim();
    }

    private static Object[] evaluateXPath(String xpath, TagNode html) {
        try {
            return html.evaluateXPath(xpath);
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        return new Object[0];
    }
}
