package model.stack;

/*
 * Een Stack is een abstracte datastructuur volgens het LIFO-principe: Last In First Out.
 * 
 * Zie: http://en.wikipedia.org/wiki/Stack_(data_structure)
 */
public interface Stack<E>
{
    // Zet een element op de stack
    public void push(E e) throws StackException;
    
    // Haal het bovenste element van de stack
    public E pop() throws StackException;

    // Inspecteer het bovenste element van de stack zonder het te verwijderen
    public E peek() throws StackException;

    // Kijk of de stack leeg is
    public boolean isEmpty();

    // Maak de stack leeg
    public void clear();

    // Kijk hoe groot de stack is
    public int size();
}