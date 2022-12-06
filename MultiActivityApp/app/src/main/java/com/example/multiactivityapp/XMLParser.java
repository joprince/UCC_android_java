package com.example.multiactivityapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLParser {
    private ArrayList<country> countries= new ArrayList<country>();
    private country country;
    private String text;

    public ArrayList<country> getCountries() {
        return countries;
    }

    public ArrayList<country> parse(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  parser = factory.newPullParser();
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("country")) {
                            // create a new instance of country
                            country = new country();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("country")) {
                            // add employee object to list
                            countries.add(country);
                        }else if (tagName.equalsIgnoreCase("name")) {
                            country.setName(text);
                        }  else if (tagName.equalsIgnoreCase("description")) {
                            country.setDescription(text);
                        } else if (tagName.equalsIgnoreCase("capital")) {
                            country.setCapital(text);
                        } else if (tagName.equalsIgnoreCase("region")) {
                            country.setRegion(text);
                        } else if (tagName.equalsIgnoreCase("emoji")) {
                            country.setEmoji(text);
                        } else if (tagName.equalsIgnoreCase("currency")) {
                            country.setCurrencySymbol(text);
                        } else if (tagName.equalsIgnoreCase("link")) {
                            country.setLink(text);
                        } else if (tagName.equalsIgnoreCase("img")) {
                            country.setImg(text);
                        }
                        else if (tagName.equalsIgnoreCase("longitude")) {
                            country.setLongitude(Float.parseFloat(text));
                        }
                        else if (tagName.equalsIgnoreCase("latitude")) {
                            country.setLatitude(Float.parseFloat(text));
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        return countries;
    }
}
