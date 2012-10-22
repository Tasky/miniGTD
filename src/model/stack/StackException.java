package model.stack;

public class StackException extends Exception
{

    public StackException(String operation, String error)
    {
	super("Illegal stack operation " + operation + "(): " + error);
    }
}
