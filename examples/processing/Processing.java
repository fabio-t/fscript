import murlen.util.fscript.*;
import java.io.*;
import java.util.*;

//Subclass FScript - we don't need BasicIO.
class DocExtension extends BasicExtension{

    //callFunction to define our own functions
    public Object callFunction(String name,ArrayList params)
    throws FSException {

        //the write function
        if (name.equals("write")) {
            String s;
            int n;
            s="";
            for(n=0;n<params.size();n++) {
                s=s+params.get(n);
            }
            System.out.println(s);
            return null;
        }
        else throw new FSUnsupportedException(name); 
    }
}

//main class
public class Processing{

    public static final void main(String args[])
    throws FSException,IOException{

        //create fscript parser
        FScript fs=new FScript();

	//create our extension
	fs.registerExtension(new DocExtension());

        BufferedReader in=new BufferedReader(new FileReader(args[0]));

        String line;
        boolean loading=false;

        line=in.readLine();
        while (line != null){
            //lines starting with % are treated as script lines
            if (line.startsWith("%")) {
                if (!loading) loading=true;
                fs.loadLine(line.substring(1,line.length()));
            }
            else if (loading) {
                //run the parser
                fs.cont();
                System.out.println(line);
                loading=false;
            }
            else {
                //just output normal lines
                System.out.println(line);
            }
            line=in.readLine();
        }

    }
}

