/*
 * FastExtension.java
 *
 * Created on September 14, 2002, 12:53 PM
 */

/**
 *
 * @author murlen
 */

import murlen.util.fscript.*;
import java.io.*;
import java.util.ArrayList;

//this class implements function calls
class VExt implements FSVarExtension{
    /*this class only implements one 'variable' it would be possible
     *through mutltiple calls to addVariableExtension to add more than
     *one 'variable' per class - but then we would need to test the
     *'name' parameters to see which variable was being accessed*/
    
    
    public Object getVar(String name) throws FSException{
       /*we don't test name as we know we are only implement pi*/
       return new Double(Math.PI);  
    }
    
    //we don't have a set method for PI (as it is a constant)
    //so we throw an exception
    public void setVar(String name,Object value) throws FSException{
        throw new FSException("pi is constant");
    }
}


//this class implements variable access
class FExt implements FSFunctionExtension{
    
    /*we implement rand(max) in the VExt we don't test the name parameter as
     *we know (in this case) we are only added to the FSFastExtension class with
     *one name.  However if we were added with more than one name we would have
     *to test the name param.*/
    public Object callFunction(String name, ArrayList params) 
                                throws FSException
    {
        try{
            return new Double(Math.random()*((Double)params.get(0)).doubleValue());
        } catch (Exception e){
            throw new FSException(e.getMessage());
        }     
                                    
    }
}


public class FastExtension {
    
    /** Creates a new instance of FastExtension */
    public FastExtension() {
    }
    
    public static void main(String argv[]) throws Exception{
        
        //use a BasicIO object - could use an FScript, but I wanted println
        BasicIO fs=new BasicIO();
        
        //register a FastExtension
        FSFastExtension fastEx=new FSFastExtension();
        fs.registerExtension(fastEx);
        
        //build our extension
        FExt fe=new FExt();
        VExt ve=new VExt();
        
        //add our two extensions
        fastEx.addFunctionExtension("rand",fe);
        fastEx.addVarExtension("pi",ve);
        
        //open and run the script file
        fs.load(new FileReader("fastextension.script"));
        //note that the sample script is _supposed_ to cause an error
        fs.run();
        
    }
    
}
