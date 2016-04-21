import java.util.ArrayList;

public class Methods {
  public ArrayList<Word> readDictionary(String lesDictionaryFilePath, String chdDictionaryFilePath, boolean debug) {
    String tempLine = null;
    BufferedReader lesReader = null;
    BufferedReader chdReader = null;
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<String> strokes = new ArrayList<String>();
    ArrayList<Word> dictionary = new ArrayList<Word>();

    
    try {
      Reader reader = new FileReader(lesDictionaryFilePath);
      lesReader = new BufferedReader(reader);
      while ((tempLine = lesReader.readLine()) != null) {
        if (tempLine.length() != 0 && tempLine.charAt(0) == '<' || tempLine.trim().length() == 0) continue;
        String[] newWords = tempLine.split(" ");
        for (String word : newWords) {
          words.add(word);
        }
      }
    }
    catch (Exception e) {
      println("Error : " + e.getMessage());
    }
    if (lesReader != null) {
      try {
        lesReader.close();
      } catch (Exception e) {

      }
    }
    
    try {
      Reader reader = new FileReader(chdDictionaryFilePath);
      chdReader = new BufferedReader(reader);
      while ((tempLine = chdReader.readLine()) != null) {
        if (tempLine.length() != 0 && tempLine.charAt(0) == '<' || tempLine.trim().length() == 0) continue;
        String[] newStrokes = tempLine.split(" ");
        for (String stroke : newStrokes) {
          strokes.add(stroke);
        }
      }
    }
    catch (Exception e) {
      println("Error Reading: " + e.getMessage());
    }
    if (chdReader != null) {
      try {
        chdReader.close();
      } catch (Exception e) {

      }
    }
    
    if (words != null && strokes != null) for (int i = 0; i < words.size(); i++) {
      Word word = new Word();
      word.word = words.get(i);
      word.stroke = strokes.get(i);
      dictionary.add(word);
    }

    
    if (debug) {
      println("Current lesson contains " + words.size() + " words and " + strokes.size() + " chords.");
    }

    return dictionary;
  }
  
  ArrayList<String> readBlacklist(String blkDictionaryFilePath) {
    ArrayList<String> wordsBlacklist = new ArrayList<String>();
    String tempLine = null;
    BufferedReader blkReader = null;
    try {
      Reader reader = new FileReader(blkDictionaryFilePath);
      blkReader = new BufferedReader(reader);
      while ((tempLine = blkReader.readLine()) != null) {
        if (tempLine.trim().length() == 0) continue;
        String[] words = tempLine.split(" ");
        for (String word : words) {
          wordsBlacklist.add(word);
        }
      }
    }
    catch (Exception e) {
      println("Be careful : " + e.getMessage());
    }
    if (blkReader != null) {
      try {
        blkReader.close();
      } catch (Exception e) {

      }
    }

    return wordsBlacklist;
  }

  
  void writeBlacklist(ArrayList<String> wordsBlacklist, String blkDictionaryFilePath) {
    BufferedWriter blkWriter = null;
    StringBuilder blacklist = new StringBuilder();
    for (String word : wordsBlacklist) {
      blacklist.append(word + " ");
    }
    String fileContent = blacklist.toString();
    fileContent = fileContent.substring(0, fileContent.length() - 1);
    try {
      Writer writer = new FileWriter(blkDictionaryFilePath);
      blkWriter = new BufferedWriter(writer);
      blkWriter.write(fileContent);
    }
    catch (Exception e) {
      println("Error of writing :" + e.getMessage());
    }
    if (blkWriter != null) {
      try {
        blkWriter.close();
      } catch (Exception e) {

      }
    }
  }
  
  //go to end of file
  BufferedReader readEndOfFile(String logFilePath) {
    BufferedReader logReader = null;
    String line = null;
    String tempLine = null;
    try {
      Reader reader = new FileReader(logFilePath);
      logReader = new BufferedReader(reader);
      while ((tempLine = logReader.readLine()) != null) {
        line = tempLine;
      }
    }
    catch (Exception e) {
      println("Error while reading Plover log file: " + e.getMessage());
    }
    return logReader;
  }

  // get next word
  Stroke getNextStroke(BufferedReader logReader) {
    Stroke stroke = new Stroke();
    String line = null;
    try {
      line = logReader.readLine();
      int indexOfTransl = -1;
      if(line != null) indexOfTransl = line.indexOf("Translation");
      if(line != null && indexOfTransl > -1) {
        boolean isMultipleWorld = false;
        int indexOfLast = 1 + line.indexOf(",) : ");
        if (indexOfLast < 1) {
          isMultipleWorld = true;
          indexOfLast = line.indexOf(" : ");
        }
        if (indexOfTransl == 24) {
          stroke.isDelete = false;
        }
        else {
          stroke.isDelete = true;
        }
        stroke.stroke = getStroke(line, indexOfTransl + 14, indexOfLast - 2);
        stroke.word = line.substring(indexOfLast + (isMultipleWorld ? 2 : 3), line.length() - 1);
        return stroke;
      } else {
        return null;
      }
    } catch (Exception e) {
      println("Error while reading stroke from Plover log file: " + e.getMessage());
    }
    return null;
  }

  // multiple strokes for a single word.
  String getStroke(String line, int start, int end) {
    String result = "";
    String strokeLine = line.substring(start, end);
    String[] strokes = strokeLine.split("', '");
    for (String stroke: strokes) result += stroke + "/";
    return result.substring(0, result.length() - 1);
  }
}
