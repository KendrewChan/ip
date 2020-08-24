package Main;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    private void resetFile() throws IOException {
        FileWriter fw = new FileWriter(filepath, false);
        fw.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filepath, true); // create a FileWriter in append mode
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAppend);
        bw.close();
    }

    public List<Task> getFileContents() throws IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        List<Task> lst = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            String[] arr = line.split(",");
            String type = arr[0];
            String checked = arr[1];
            String desc = arr[2];
            switch (type) {
                case "T":
                    Task todo = new Todo(desc, checked);
                    lst.add(todo);
                    break;
                case "E":
                    Task event = new Event(desc, checked, Parser.strToDate(arr[3]));
                    lst.add(event);
                    break;
                case "D":
                    Task deadline = new Deadline(desc, checked, Parser.strToDate(arr[3]));
                    lst.add(deadline);
                    break;
                default:
                    break;
            }
            line = br.readLine();
        }
        br.close();
        return lst;
    }

    public void rewriteFileContents(List<Task> lst) {
        try {
            resetFile();
            for (int i = 0; i < lst.size(); i++) {
                String[] arr = lst.get(i).getStringArr();
                String s = Task.stringFormat(arr);
                appendToFile(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}