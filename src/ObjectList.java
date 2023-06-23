package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class ObjectList<T extends Savable> {
    protected ArrayList<T> objects = new ArrayList<>();

    ObjectList() {

    }

    ObjectList(String s) {
        try(Scanner scanner = new Scanner(new File("src\\"+s))) {
            scanner.useDelimiter("\n");
            String buf;
            while (scanner.hasNext()) {
                buf = scanner.nextLine();
                if (scanner.hasNext()) {
                    T obj = createObj(buf, scanner);
                    objects.add(obj);
                }
            }
            scanner.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected abstract T createObj(String buf, Scanner scanner);

    public T getObj(String name) {
        for(int i = 0; i < objects.size(); i++) {
            T obj = objects.get(i);
            if (obj.getName().equals(name)) return obj;
        }
        return null;
    }

    public T getObj(int i) {
        return objects.get(i);
    }

   public void addObj(T obj) {
        objects.add(obj);
   }

    public int getSize() {
        return objects.size();
    }

    private String toWrite() {
        String s = "";
        for(int i = 0; i < objects.size(); i++) {
            T obj = objects.get(i);
            s += obj.toWrite();
        }
        return s;
    }

    public void write(String s) {
        try (FileWriter fw = new FileWriter("src\\" + s)) {
            fw.write(toWrite());
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < objects.size(); i++) {
            s += i + "\n";
            T obj = objects.get(i);
            s += obj.toString();
            s += "\n";
        }
        return s;
    }
}