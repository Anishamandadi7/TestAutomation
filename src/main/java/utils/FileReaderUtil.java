package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {

	public static String readFile(String filename) {
		String output = "";
		File file = new File("./inputjsons/"+filename+".json");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String st;
		try {
			while ((st = br.readLine()) != null)

				output += st;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

}
