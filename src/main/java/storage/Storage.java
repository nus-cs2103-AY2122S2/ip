package storage;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	private String filePath;
	private TaskList tasks = new TaskList();

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public TaskList load() throws IOException {
		//load files
		File directory = new File("data");
		if(!directory.exists()){
			directory.mkdir();
		}

		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		} else {
			Scanner s = new Scanner(file);
			String type;
			String mark;
			String detail;
			String date;
			Task toAdd = null;
			while(s.hasNext()) {
				String input = s.nextLine();
				String[] inputSplit = input.split(" \\| ", 3);
				type = inputSplit[0];
				mark = inputSplit[1];
				switch (type){
				case "T":
					detail = inputSplit[2];
					toAdd = new ToDo(detail);
					break;
				case "E":
					inputSplit = inputSplit[2].split(" \\| ");
					detail = inputSplit[0];
					date = inputSplit[1];
					toAdd = new Event(detail, date);
					break;
				case "D":
					inputSplit = inputSplit[2].split(" \\| ");
					detail = inputSplit[0];
					date = inputSplit[1];
					toAdd = new Deadline(detail, date);
					break;
				}
				System.out.println(mark);
				if(mark.equals("1")){
					toAdd.mark();
				}
				tasks.add(toAdd);
			}
		}
		return tasks;
	}

	public void saveFile(TaskList tasks) throws IOException {
		FileWriter fw = new FileWriter("data/tasks.txt");
		for (int i =0; i < tasks.size(); i++){
			Task t = tasks.get(i);
			String type = t.getType();
			String mark = t.getMark();
			String detail = t.getDetail();
			switch (type){
			case"T":
				fw.write(type + " | " +  mark + " | " + detail + "\n");
				break;
			case"E":
			case"D":
				String date = t.getDate();
				fw.write(type + " | "  + mark + " | " + detail + " | " + date + "\n");
				break;
			}
		}
		fw.close();
	}
}
