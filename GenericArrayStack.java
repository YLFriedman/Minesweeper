public class GenericArrayStack<E> implements Stack<E> {
   
   private E[] arrayStack;
   private int top;

   // Constructor
   @SuppressWarnings( "unchecked" )
    public GenericArrayStack( int capacity ) {
        
        arrayStack = (E[]) new Object[capacity];    
        top = 0;
    

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    return top == 0;

    }
    @SuppressWarnings( "unchecked" )
    public void push( E elem ) {
    
    if(top == arrayStack.length){
        E[] newStack = (E[]) new Object[arrayStack.length*2];
        for(int i = 0; i < arrayStack.length; i ++){
            newStack[i] = arrayStack[i];
        }
        arrayStack = newStack;
    }
    arrayStack[top] = elem;
    top++;

    }
    public E pop() {

        if(!isEmpty()){
        top = top-1;
        E holder = arrayStack[top];
        arrayStack[top] = null;
        return holder;
        }  

        E fuckyou = null;
        return fuckyou;  

    }

    public E peek() {
        
    return arrayStack[top-1];

    }
}
