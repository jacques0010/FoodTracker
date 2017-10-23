package com.FoodTracker.main.FileUtils;

import java.io.*;
import java.util.HashMap;

public class LoadFile {

    private static LoadFile ourInstance = new LoadFile();

    public static LoadFile getInstance() {
        return ourInstance;
    }

    private LoadFile() {
    }

    public HashMap<?, ?> load(String fileName) {
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HashMap<?, ?>) OIS.readObject();
        }catch (FileNotFoundException FNFE){
            System.err.println("File not found! Creating a new one");
            save(new HashMap(), fileName);
            return new HashMap();
        }catch (IOException IOE) {
            IOE.printStackTrace();
            return new HashMap();
        } catch (ClassNotFoundException CNFE) {
            CNFE.printStackTrace();
            return new HashMap();
        }
    }
    public void save(HashMap hashMap, String fileName){
        try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(fileName))){
            OOS.writeObject(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
            //todo handle exception
        }
    }
}
