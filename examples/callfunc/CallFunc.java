import murlen.util.fscript.*; 
import java.io.*;
import java.util.ArrayList;

public class CallFunc {

public static void main(String argv[]) throws Exception{

	BasicIO fs=new BasicIO();
	ArrayList params=new ArrayList();

	fs.load(new FileReader("callfunc.script"));
	fs.run();

	fs.setScriptVar("text","Hello World");

	params.add(new Integer(10));
	fs.callScriptFunction("sayHello",params);
	System.out.println((String)fs.getScriptVar("response"));
}


}
