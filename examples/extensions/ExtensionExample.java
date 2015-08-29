import murlen.util.fscript.*;
import java.io.*;
import java.util.ArrayList;

class MyExtension extends BasicExtension{
	public Object callFunction(String name, ArrayList params)
		throws FSException{
	
		if (name.equals("HelloWorld")){
			System.out.println("Hello World - from an extension");
		} else {
			throw new FSUnsupportedException(name);
	
		}
		return null;
	}
	
	public Object getVar(String name){
		System.out.println("Getting  " + name);
		return new Integer(0);
	}

	public Object getVar(String name,Object index){
		System.out.println("Getting " + name + "[" + index + "]");
		return new Integer(0);
	}
	
	public void setVar(String name,Object value) {
		System.out.println("Setting " + name + " to " + value);
	}	
	
	public void setVar(String name,Object index,Object value)
		{
		System.out.println("Setting " + name + "[" + index + "] to " + value);
	}
}

public class ExtensionExample{

	public static void main(String args[]) throws Exception{
	
		FScript fs =new FScript();

		fs.registerExtension(new MyExtension());

		FileReader f=new FileReader(args[0]);
		fs.load(f);
		fs.run();
	}	
}
