package helpers;

import java.io.*;
import java.util.*;

import representations.*;

public class LinkHelper {

  static String LINKS_FILE = "../database/links.txt";
  private static final String DELIMITER = "#";

  @SuppressWarnings("unchecked")
  public static LinksRepresentation getLinksForProject(String project) {
    Collection<LinkRepresentation> links = new ArrayList<LinkRepresentation>();

    try {
      ensureFileExistence();
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

  private static void ensureFileExistence() throws Exception {
    File f = new File(LINKS_FILE);
    if (!f.exists()) {
      System.out.println("creating file: " + f.getAbsolutePath());
      f.createNewFile();
    }
  }

  public static void createLink(String project, String url, String description) {
    try{ 
      FileWriter fstream = new FileWriter(LINKS_FILE,true);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(project + DELIMITER + description + DELIMITER + url);
      out.close();
      }catch (Exception e){
        System.err.println("Error: " + e.getMessage());
      }
  }
}
