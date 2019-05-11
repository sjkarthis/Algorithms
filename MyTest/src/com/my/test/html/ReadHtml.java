package com.my.test.html;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReadHtml {
	public static void main(String[] args) {
		String all = "";
		File index = new File("C:/Users/karthsub/OneDrive - Capgemini/Desktop/Geeks/Algorithms - GeeksforGeeks.html");
		Set<String> files = new HashSet<String>();
		List<String> fList = new ArrayList<String>();
		try {
			boolean add = false;
			Document idoc = Jsoup.parse(new String(Files.readAllBytes(Paths.get(index.getAbsolutePath()))));
			all = idoc.html();
			Elements links = idoc.select("a[href]");
			for (Element a : links) {
				String s[] = a.attr("href").split("/");
				if (add && s.length > 3) {
					if(files.add(s[3]))
						fList.add(s[3]);
				}
				if(a.attr("href").equals("#Misc"))
					add = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(files.size()+"--"+fList.size());
		List<File> listOfFiles = new ArrayList<File>(fList.size());
		for(String fs : fList) {
			//System.out.println(fs);
			listOfFiles.add(new File("C:/Users/karthsub/OneDrive - Capgemini/Desktop/Geeks/downloads/"+fs+".htm"));
		}
		/*
		 * int sh = 0; for (File file : listOfFiles) { if (file.isFile()) {
		 * System.out.println(file.getAbsolutePath()); sh++; } } System.out.println(sh);
		 */

		/*
		 * File folder = new
		 * File("C:/Users/karthsub/OneDrive - Capgemini/Desktop/Geeks/downloads");
		 * File[] listOfFiles = folder.listFiles();
		 */
		int fflag = 1;
		int count = 0;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				try {
					Document document = Jsoup.parse(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));

					removeElem(document.getElementsByClass("leftSideBarParent"));
					removeElem(document.select("nav"));
					removeElem(document.select("footer"));
					removeElem(document.getElementById("secondary"));
					removeElem(document.select("footer"));
					removeElem(document.getElementById("MasterHead"));
					removeElem(document.getElementById("ide_link"));
					removeElem(document.getElementById("share"));
					removeElem(document.getElementsByClass("recommendedPostsDiv"));
					removeElem(document.getElementById("improvedBy"));
					removeElem(document.getElementById("comment"));
					removeElem(document.getElementsByClass("code-gutter"));

					// syntaxhighlighter nogutter
					int i = 0;
					for (Element e : document.select("div.tabcontent:not(#tablist1-panel3)")) {
						if (i != 2)
							e.remove();
						i++;
					}

					for (Element e : document.getElementsByClass("syntaxhighlighter")) {
						e.removeClass("syntaxhighlighter");
						e.removeClass("nogutter");
					}

					if (null != document.select("div[id=video]")) {
						for (Element e : document.select("div[id=video]")) {
							e.remove();
						}
					}

					all = all + document.html();
					
					Files.write(Paths.get(file.getAbsolutePath()), document.html().getBytes("UTF-8"),
							StandardOpenOption.TRUNCATE_EXISTING);
					
					if(count == 100) {
						String path = "C:/Users/karthsub/OneDrive - Capgemini/Desktop/Geeks/All-content"+fflag+".html";
						try {
							Files.write(Paths.get(path), all.getBytes(), StandardOpenOption.CREATE);
						} catch (IOException e) { // TODO Auto-generated catch block
							e.printStackTrace();
						}
						count = 0;
						fflag++;
						all="";
					}
					count++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String path = "C:/Users/karthsub/OneDrive - Capgemini/Desktop/Geeks/All-content"+fflag+".html";
		try {
			Files.write(Paths.get(path), all.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void removeElem(Elements element) {
		if (null != element)
			element.remove();
	}

	private static void removeElem(Element element) {
		if (null != element)
			element.remove();
	}
}
