package JavaToUML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Parser {

	private static ArrayList<File> JavaFiles = new ArrayList<File>();

	private static ArrayList<String> ClassNames = new ArrayList<String>();

	private static ArrayList<String> InterfaceNames = new ArrayList<String>();

	private static ArrayList<String> UMLsource = new ArrayList<String>();
	
	HashMap<String, ArrayList<String>> ClassFieldsMap = new HashMap<String,ArrayList<String>>();


	public Parser(ArrayList<File> files){
		JavaFiles = files;
	}

	public ArrayList<String> parser() throws IOException {		

		UMLsource.add("@startuml");

		for(int i=0; i<JavaFiles.size();i++){
			File file = new File(JavaFiles.get(i).getAbsolutePath());
			FileInputStream file_in = null;
			CompilationUnit compile_unit = null;
			try{
				file_in = new FileInputStream(file);
				compile_unit = JavaParser.parse(file_in);				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				file_in.close();
			}

			ClassOrInterfaceVisitor class_interface_visitor = new ClassOrInterfaceVisitor();

			class_interface_visitor.visit(compile_unit,null);

			if(class_interface_visitor.IsClass()){
				String classname = class_interface_visitor.getClassName();
				ClassNames.add(classname);
				UMLsource.add("class "+classname+"{");
				
				FieldVisitor fieldvisitor = new FieldVisitor();
				fieldvisitor.visit(compile_unit,null);
				ArrayList<String> fields = fieldvisitor.getFieldName();
				if(fields!=null){
					UMLsource.addAll(fieldvisitor.getFieldName());
				}
				ArrayList<String> Fieldtypes = fieldvisitor.getFieldTypes();
				ClassFieldsMap.put(classname, Fieldtypes);
				
			}

			else if(class_interface_visitor.IsInterface()){
				String interfacename = class_interface_visitor.getInterfaceName();
				InterfaceNames.add(interfacename);
				UMLsource.add("interface "+interfacename+"{");

			}

			MethodVisitor methodvisitor = new MethodVisitor();
			methodvisitor.visit(compile_unit,null);
			UMLsource.addAll(methodvisitor.getMethods());



			UMLsource.add("}");
		}
		
		for (Entry<String, ArrayList<String>> entry : ClassFieldsMap.entrySet()) {
		    //System.out.println(entry.getValue());
			for(String s: entry.getValue()){
				if(ClassNames.contains(s) ){
					UMLsource.add("class "+entry.getKey()+"--"+"class "+s);
					//System.out.println("class "+entry.getKey()+"--"+"class "+s);
				}
			}
		}

		UMLsource.add("@enduml");


		return UMLsource;

	}

}








