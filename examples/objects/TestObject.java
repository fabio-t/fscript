/*
This is a test object used by the objects.script program
*/

public class TestObject{
	
	private String s;
	public String stringField="This is a string field";
	public Integer intField=new Integer(1234567);
	
	public Integer intMethod(String s){
		return new Integer(s);
	}
	
	public void testMethod(){
		System.out.println("Thank you for calling the test method");
	}
	
	public String getValue(){
		return s;
	}
	
	public void setValue(String value){
		s=value;
	}
}

