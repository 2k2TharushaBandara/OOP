package filehandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void main(String[] args){
        
         //reading from the original file(Before reading, go and set a text. Then run the program).
        try{
            BufferedReader reader = new BufferedReader(new FileReader("OriginalText.txt"));
            //System.out.println(reader.readLine());
            //System.out.println(reader.readLine());
            
            String line;
            while((line = reader.readLine()) != null)
                System.out.println(line);
            
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //overwriting the original text
        String [] names = {"Tharusha","Jane"};
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("OriginalText.txt"));
            writer.write("Writing to a file...");
            writer.write("\nThis is a new line");
            
            for(String name:names){
                writer.write("\n"+name);
            }
            
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //Reading from the current file and writing it in another file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("OriginalText.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("NewText.txt"));
            //System.out.println(reader.readLine());
            //System.out.println(reader.readLine());
            
            String line;
            while((line = reader.readLine()) != null)
                writer.write(line+"\n");
            
            reader.close();
            writer.close();
        }catch(java.io.FileNotFoundException ex){
            System.err.println(ex.getMessage());    
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //Another method of writing to a file
        java.io.PrintWriter output = null;
        try{
            output =  new java.io.PrintWriter("Text.txt");
            output.println("Welcome to Java");
        }catch(java.io.IOException e){
            e.printStackTrace();
        }
        finally{
            if(output != null)
                output.close();
        }
        
    }
    
}
