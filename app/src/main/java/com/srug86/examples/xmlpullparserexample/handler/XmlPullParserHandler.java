package com.srug86.examples.xmlpullparserexample.handler;

import com.srug86.examples.xmlpullparserexample.domain.Category;
import com.srug86.examples.xmlpullparserexample.domain.CategoryType;
import com.srug86.examples.xmlpullparserexample.domain.Field;
import com.srug86.examples.xmlpullparserexample.domain.FieldType;
import com.srug86.examples.xmlpullparserexample.domain.Template;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sergio on 20/07/2015.
 */
public class XmlPullParserHandler {

    private final String KEY_TEMPLATE = "template";
    private final String KEY_TEMPLATE_NAME = "name";
    private final String KEY_CATEGORY = "category";
    private final String KEY_CATEGORY_NAME = "name";
    private final String KEY_CATEGORY_TYPE = "type";
    private final String KEY_FIELD = "field";
    private final String KEY_FIELD_NAME = "name";
    private final String KEY_FIELD_TYPE = "type";

    private Template template;

    public XmlPullParserHandler() {}

    public Template getTemplate() {
        return template;
    }

    public Template parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            Category category = null;
            int categoryNumber = 1;
            Field field = null;
            int fieldNumber = 1;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(KEY_TEMPLATE)) {
                            template = new Template();
                            String templateName = parser.getAttributeValue(null, KEY_TEMPLATE_NAME);
                            template.setName(templateName);
                        } else if (tagName.equalsIgnoreCase(KEY_CATEGORY)) {
                            category = new Category();
                            String categoryName = parser.getAttributeValue(null, KEY_CATEGORY_NAME);
                            String categoryType = parser.getAttributeValue(null, KEY_CATEGORY_TYPE);
                            category.setName(categoryName);
                            category.setType(CategoryType.valueOf(categoryType));
                            categoryNumber++;
                        } else if (tagName.equalsIgnoreCase(KEY_FIELD)) {
                            field = new Field();
                            String fieldName = parser.getAttributeValue(null, KEY_FIELD_NAME);
                            String fieldType = parser.getAttributeValue(null, KEY_FIELD_TYPE);
                            field.setName(fieldName);
                            field.setType(FieldType.valueOf(fieldType));
                            fieldNumber++;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(KEY_CATEGORY)) {
                            // add employee object to list
                            template.getCategoryList().add(category);
                        } else if (tagName.equalsIgnoreCase(KEY_FIELD)) {
                            category.getFieldList().add(field);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return template;
    }
}
