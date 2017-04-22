package JavaToUML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UMLparser {

	private static ArrayList<File> JavaFiles = new ArrayList<File>();

	private static ArrayList<String> PlantUMLsource = new ArrayList<String>();	

	public static void main(String[] args) {
		String inputpath = "C:\\Users\\Pavana\\Desktop\\git\\202\\temp";

		String outputpath = "C:\\Users\\Pavana\\Desktop\\git\\202\\PersonalProject\\Outputs\\output.java";

		File inputFile = new File(inputpath);

		if(inputFile.isDirectory()){
			DirExplorer dir_explorer = new DirExplorer(inputFile);

			JavaFiles = dir_explorer.listFiles();
		}
		else 
		{
			JavaFiles.add(inputFile);
		}

		Parser javaparser = new Parser(JavaFiles);

		try {

			PlantUMLsource = javaparser.parser();
		} 
		catch (IOException e) {

			e.printStackTrace();
		}

		FileWriter.writer(outputpath,PlantUMLsource);

		PlantUML uml = new PlantUML(outputpath);
		uml.GenerateUML();


	}
}