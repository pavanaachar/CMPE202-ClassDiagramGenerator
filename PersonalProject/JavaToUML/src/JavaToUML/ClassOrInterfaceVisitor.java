package JavaToUML;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassOrInterfaceVisitor extends VoidVisitorAdapter<Object> {
	
	private String ClassName ;
	private String InterfaceName;
	private boolean IsClass;
	private boolean IsInterface;
	
	public ClassOrInterfaceVisitor(){
		this.IsClass= false;
		this.IsInterface = false;
		this.ClassName = null;
		this.InterfaceName = null;
		
	}
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object obj) {
		
		if(!n.isInterface()){
			this.ClassName = n.getName().toString();
			this.IsClass = true;
		}
		
		
		if(n.isInterface())
		{
			this.InterfaceName = n.getName().toString();
			this.IsInterface = true;
		}
		

	}
	
	public String getClassName(){
		return this.ClassName;
	}
	
	public String getInterfaceName(){
		return this.InterfaceName;
	}
	
	public boolean IsClass(){
		return this.IsClass;
	}
	
	public boolean IsInterface(){
		return this.IsInterface;
	}
	

}

