public class StackTest{
	
	public static void main(String[] args){
		GenericArrayStack<Integer> test = new GenericArrayStack<Integer>(2);
		test.push(1);
		test.push(2);
		test.push(5);
		test.push(8);
		test.push(10);
		while(!test.isEmpty()){
			System.out.println(test.pop());
		}
	}
}