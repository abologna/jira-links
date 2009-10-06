package helpers;

import java.io.*;
import java.util.*;

import representations.*;

public class LinkHelper {

  static String LINKS_FILE = "../database/links.txt";
  static String TEMP_FILE = "../database/temp.txt";
  private static final String DELIMITER = "#";

  @SuppressWarnings("unchecked")
  public static LinksRepresentation getLinksForProject(String project) {
    Collection<LinkRepresentation> links = new ArrayList<LinkRepresentation>();

    try {
      ensureFileExistence(LINKS_FILE);
      DataInputStream in = new DataInputStream(new FileInputStream(LINKS_FILE));
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null){
        LinkRepresentation link = createLinkFromLine(line,project); 
        if(link != null)
          links.add(link);
      }
      in.close();
    } catch (Exception e) {
      return new LinksRepresentation(Collections.EMPTY_LIST);
    }

    return new LinksRepresentation(links);
  }

  private static LinkRepresentation createLinkFromLine(String line, String project) {
    StringTokenizer st = new StringTokenizer(line, DELIMITER);
    String projectId = st.nextToken();
    String description = st.nextToken();
    String url = st.nextToken();
    return projectId.equals(project)?
        new LinkRepresentation(projectId,description, url):
        null;
  }

  private static void ensureFileExistence(String file) throws Exception {
    File f = new File(file);
    if (!f.exists()) {
      System.out.println("creating file: " + f.getAbsolutePath());
      f.createNewFile();
    }
  }

  public static void createLink(String project, String url, String description) {
    try{ 
      FileWriter fstream = new FileWriter(LINKS_FILE,true);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(project + DELIMITER + description + DELIMITER + url + "\n");
      out.close();
      }catch (Exception e){
        System.err.println("Error: " + e.getMessage());
      }
  }
  
  public static void removeLink(String projectToRemove, String urlToRemove){
    // Here be dragons
    try {
      ensureFileExistence(TEMP_FILE);
      ensureFileExistence(LINKS_FILE);
      List<String> linesToKeep = new ArrayList<String>();
      
      DataInputStream in = new DataInputStream(new FileInputStream(LINKS_FILE));
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null){
          StringTokenizer st = new StringTokenizer(line,DELIMITER);
          String projectId = st.nextToken();
          st.nextToken(); //need to skip a token before the URL (ugly code ^ 2)
          String url = st.nextToken();
          if(projectId.equals(projectToRemove) && url.equals(urlToRemove)) continue;
          linesToKeep.add(line);
      }
      in.close();
      
      FileWriter fstream = new FileWriter(TEMP_FILE);
      BufferedWriter out = new BufferedWriter(fstream);
      for(String l : linesToKeep){
        out.write(l + "\n");
      }
      out.close();
      
      File old = new File(LINKS_FILE);
      old.delete();
      
      File newLinks = new File(TEMP_FILE);
      newLinks.renameTo(new File(LINKS_FILE));
      
    } catch (Exception e) {
      System.err.println("Error while deleting link. " + e);
    }
  }
}
