import java.io.*;
import java.util.*;
import  java.util.Collection;


public class DfDemo {

    public static void main(String[] args) throws IOException {

      //getting the df command data via a function
      String data=getDfData();
      String[] total =data.split("[\\r\\n]+");
     // System.out.println(data);

      // formating the string data into array of strings
      ArrayList<String[]> listOfStringArrays = new ArrayList<>();
      for (int i = 0; i < total.length; i++) {
        listOfStringArrays.add(total[i].split("\\s+"));
      }

      // creating array of objects
      FileSystem files[]=new FileSystem[total.length-2];
      for (int i = 0; i < total.length-2; i++) {
       files[i]=new FileSystem();
      }
      // initializing the values
      int n=2;
      for (int i = 0; i < files.length; i++) {
       files[i].set(listOfStringArrays.get(n));
       n++;
      }
      String calculateUsed="getUsed";
      String calculateAvailable="getAvailable";

      // creating the file and adding the information in the files
      try {
        File obj = new File("myfile.txt");

        if (obj.createNewFile()) {
          System.out.println("File Created! "+ obj.getName());
        } else {
          System.out.println("File Exists");
        }

      }
      catch (Exception e)
      {
        System.out.println(e);
      }


      FileWriter writer=new FileWriter("myfile.txt");
      try{
        writer.write(" The Available file System are  :\n ");

        for (String s : calculate(files,calculateAvailable))
        {
       writer.write(s);
       writer.write("\n");
        }

        writer.write("Used file systems are :\n ");
        for (String s : calculate(files,calculateUsed))
        {
          writer.write(s);
          writer.write("\n ");
        }

        writer.close();
      }
      catch (Exception e){
        System.out.println(e);
      }


    }
  public static String getDfData() throws IOException {
    String command="df";
    Process process=Runtime.getRuntime().exec(command);

    BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));

    String line;
    String data="";
    while (true) {
      try {
        if (!((line=br.readLine())!=null)) break;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      data=data+"\n"+line;
    }
    br.close();
    return data;
  }

    public  static ArrayList<String> calculate(FileSystem[] files ,String fun)
    {
      Map<Integer, Integer> mp = new HashMap<>();
      if(fun=="getUsed") {
        for (int i = 0; i < files.length; i++) {
          mp.put(i, files[i].getUsed());
        }
      }
      if(fun=="getAvailable") {
        for (int i = 0; i < files.length; i++) {
          mp.put(i, files[i].getAvailable());
        }
      }

      //finding the Maximum values with the help of collection framework
      ArrayList<Integer> list=new ArrayList<Integer>();
      list.addAll(mp.values());
      int max1=Collections.max(list);
      list.remove(list.indexOf(max1));
      int max2=Collections.max(list);

      // finding their values in the map by using a function
      ArrayList<String> fileSystem=new ArrayList<>();
      Set<Integer> m1 = getKeysByValue(mp, max1);
      for( int i :m1)
      {
        fileSystem.add(files[i].getFileSystem());
      }
      Set<Integer> m2 = getKeysByValue(mp, max2);
      for( int i :m2)
      {
        fileSystem.add(files[i].getFileSystem());
      }

      return fileSystem;

    }

  public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
    Set<T> keys = new HashSet<T>();
    for (Map.Entry<T, E> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        keys.add(entry.getKey());
      }
    }
    return keys;
  }
}

