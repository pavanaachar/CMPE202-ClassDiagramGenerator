package JavaToUML;

import java.util.ArrayList;
import java.util.EnumSet;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class MethodVisitor extends VoidVisitorAdapter<Object> {
	public ArrayList<String> Methods = new ArrayList<String>(); 

	@Override
	public void visit(MethodDeclaration n, Object obj) {

		System.out.print("\n" +n.getNameAsString()+"\n");
		EnumSet<Modifier> mod = n.getModifiers();
		
		String name = "";
		switch(mod.toString()){
		case "[PRIVATE]": name+="-";
		break;						
		case "[PROTECTED]": name+="#";
		break;
		case "[PACKAGE]": name+="~";
		break;
		default: name += "+";
		}
		name += n.getNameAsString();
		String methodname = name + "()";
		System.out.println(methodname);

		Methods.add(methodname);

	}

	public ArrayList<String> getMethods(){
		return Methods;
	}

}
