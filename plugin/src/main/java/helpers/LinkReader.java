package helpers;

import java.io.*;
import java.util.*;

import representations.*;

public class LinkReader {
  
  static String LINKS_FILE = "../database/links.txt";
  private static final String DELIMITER = "#";

  @SuppressWarnings("unchecked")
  public static LinksRepresentation getLinksForProject(String project){
    Collection<LinkRepresentation> links = new ArrayList<LinkRepresentation>();
    
    try{
      ensureFileExistence();
      FileInputStream fstream = new FileInputStream(LINKS_FILE);
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        links.add(createLinkFromLine(line));
      in.close();
    }catch (Exception e){
      return new LinksRepresentation(Collections.EMPTY_LIST);
    }
    
    return new LinksRepresentation(links);
  }
  private static LinkRepresentation createLinkFromLine(String line){
    StringTokenizer st = new StringTokenizer(line,DELIMITER);
    return new LinkRepresentation(st.nextToken(),st.nextToken(),st.nextToken());
  }
  
  private static void ensureFileExistence(){
    try{
      File f = new File(LINKS_FILE);
      if(!f.exists()){
        System.out.println("creating file: " + f.getAbsolutePath());
        f.createNewFile();
      }
    }catch(Exception e){
      throw new RuntimeException("Error While Creating the file");
    }
  }
}
