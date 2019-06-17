class Maximum_Stack {

    Stack<Integer> stack;
    Stack<Integer> maximum_Stack;
     
    public Maximum_Stack() {
        this.stack = new Stack<Integer>();
        this.maximum_Stack = new Stack<Integer>();
    }
    
    public void push(int x) {
        
        if (stack.empty()) {
            stack.push(x);
            maximum_Stack.push(x);
        } else {
            stack.push(x);
            if (!maximum_Stack.empty() && x >= maximum_Stack.peek()) {
                maximum_Stack.push(x);
            } else if (maximum_Stack.empty()) {
                maximum_Stack.push(x);
            }
        }        
    }
    
    public int pop() {
        
        int popped = Integer.MIN_VALUE;
        if (!stack.empty()) {
            popped = stack.pop();
            if (!maximum_Stack.empty() && popped == maximum_Stack.peek()) {
                maximum_Stack.pop();
            }
        }        
        return popped;
    }
    
    public int top() {
        if (!stack.empty()) {
            return stack.peek();
        }
        return Integer.MIN_VALUE;
    }
    
    public int Max_peek() {
        if (!maximum_Stack.empty()) {
            return maximum_Stack.peek();
        }
        return Integer.MIN_VALUE;
    }
    
    public int Max_pop() {
        
        if (!maximum_Stack.empty()) {
            int popped = maximum_Stack.pop();
            
            Stack<Integer> tempStack = new Stack<Integer>();
            
            while (stack.peek() != popped) {                
                int sPopped = stack.pop();
                tempStack.push(sPopped);
            }            
            stack.pop();   
            while (!tempStack.empty()) {
                push(tempStack.pop());
            }
            return popped;
        }       
        return Integer.MIN_VALUE;
    }
}
