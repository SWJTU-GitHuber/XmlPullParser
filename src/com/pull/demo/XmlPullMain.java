package com.pull.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlPullMain {

	public static void main(String[] args) {

		try {
			InputStream xmlStream = new FileInputStream(new File("res/res.xml"));
			XmlPullMain parserTool = new XmlPullMain();
			parserTool.parserXmlByPull(xmlStream, "UTF-8");
//			 InputStream xmlStream = new FileInputStream(new
//			 File("res/test.xml"));
//			 XmlPullMain parserTool = new XmlPullMain();
			 //parserTool.testDepth(xmlStream, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void parserXmlByPull(InputStream xmlStream, String xmlEncode) {
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			parser.setInput(xmlStream, xmlEncode);

			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					System.out.println("First we get START_DOCUMENT event");
					break;

				case XmlPullParser.START_TAG:
					String node = parser.getName();
					if (node.equals("manifest")) {
						System.out.println("now we parser manifest Tag");
						System.out.println("\tmainfest attribute: " + parser.getAttributeValue(0));
					} else if (node.equals("uses-sdk")) {
						System.out.println("now we parser uses-sdk Tag");
						int count = parser.getAttributeCount();
						for (int i = 0; i < count; i++)
							System.out.println("\tuses-sdk attribute: " + parser.getAttributeValue(i));
						System.out.println("\tuses-sdk plateform: " + parser.nextText());
					} else if (node.equals("application")) {
						System.out.println("now we parser Application Tag");
						parserApplicationInfo(parser);
					}
					break;

				default:
					break;
				}

				event = parser.next();
			}

		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}

	}

	private void parserApplicationInfo(XmlPullParser parser) throws XmlPullParserException, IOException {
		int count = parser.getAttributeCount();
		for (int i = 0; i < count; i++)
			System.out.println("\twe parser Application attribute: " + parser.getAttributeValue(i));
		int event = 0;
		int depth = parser.getDepth();
		while ((event = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (parser.getDepth() > depth || event != XmlPullParser.END_TAG)) {//只解析当前的标签内部的内容

			if (event == XmlPullParser.TEXT || event == XmlPullParser.END_TAG) {
				continue;
			}

			switch (event) {
			case XmlPullParser.START_TAG:
				String node = parser.getName();
				if (node.equals("activity")) {
					parserActivityInfo(parser);
				}
				break;
			default:
				break;
			}
		}
	}

	private void parserActivityInfo(XmlPullParser parser) throws XmlPullParserException, IOException {
		int count = parser.getAttributeCount();
		for (int i = 0; i < count; i++)
			System.out.println("\t\twe parser Activity -> attribute: " + parser.getAttributeValue(i));

		int event = 0;
		int depth = parser.getDepth();
		while ((event = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (parser.getDepth() > depth || event != XmlPullParser.END_TAG)) {//只解析当前的标签内部的内容
			if (event == XmlPullParser.TEXT || event == XmlPullParser.END_TAG) {
				continue;
			}
			switch (event) {
			case XmlPullParser.START_TAG:
				String node = parser.getName();
				if (node.equals("intent-filter")) {
					parserIntentInfo(parser);
				}
				break;
			default:
				break;
			}
		}
	}

	private void parserIntentInfo(XmlPullParser parser) throws XmlPullParserException, IOException {
		int event = 0;
		int depth = parser.getDepth();
		while ((event = parser.next()) != XmlPullParser.END_DOCUMENT
				&& (parser.getDepth() > depth || event != XmlPullParser.END_TAG)) {//只解析当前的标签内部的内容
			if (event == XmlPullParser.TEXT || event == XmlPullParser.END_TAG) {
				continue;
			}

			switch (event) {
			case XmlPullParser.START_TAG:
				String node = parser.getName();
				if (node.equals("action")) {
					System.out.println("\t\t\twe parser intent-filter action-> " + parser.getAttributeValue(0));
				} else if (node.equals("category")) {
					System.out.println("\t\t\twe parser intent-filter category-> " + parser.getAttributeValue(0));
				}
				break;
			default:
				break;
			}
		}
	}

	public void testDepth(InputStream xmlStream, String xmlEncode) {
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			parser.setInput(xmlStream, xmlEncode);

			int event = parser.getEventType();
			System.out.println("start current depth: " + parser.getDepth());
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					System.out.println("START_DOCUMENT depth: " + parser.getDepth());
					break;
				case XmlPullParser.START_TAG:
					String node = parser.getName();
					if (node.equals("root")) {
						System.out.println("root START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("A")) {
						System.out.println("A START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("B")) {
						System.out.println("B START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("C")) {
						System.out.println("C START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("D")) {
						System.out.println("D START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("E")) {
						System.out.println("E START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("F")) {
						System.out.println("F START_TAG depeth: " + parser.getDepth());
					} else if (node.equals("G")) {
						System.out.println("G START_TAG depeth: " + parser.getDepth());
					}
					break;
				case XmlPullParser.TEXT:
					System.out.println("--TEXT depth: " + parser.getDepth());
					break;
				case XmlPullParser.END_TAG:
					String nodeEnd = parser.getName();
					if (nodeEnd.equals("root")) {
						System.out.println("root END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("A")) {
						System.out.println("A END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("B")) {
						System.out.println("B END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("C")) {
						System.out.println("C END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("D")) {
						System.out.println("D END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("E")) {
						System.out.println("E END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("F")) {
						System.out.println("F END_TAG depeth: " + parser.getDepth());
					} else if (nodeEnd.equals("G")) {
						System.out.println("G END_TAG depeth: " + parser.getDepth());
					}
					break;
				case XmlPullParser.CDSECT:
					break;
				default:
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {

		}
	}

}
