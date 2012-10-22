package model.stack;

import java.util.ArrayList;
import java.util.List;

public class ListStack<E> implements Stack<E>
{
	private List<E> data;

	public ListStack()
	{
		data = new ArrayList<E>();
	}

	public void push(E e)
	{
		data.add(e);
	}

	public E pop() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("pop", "stack is empty");
		}
		E e = peek();
		data.remove(data.size() - 1);
		return e;
	}

	public E peek() throws StackException
	{
		if (isEmpty())
		{
			throw new StackException("peek", "stack is empty");
		}
		return data.get(data.size() - 1);
	}

	public boolean isEmpty()
	{
		return data.isEmpty();
	}

	public void clear()
	{
		data.clear();
	}

	public int size()
	{
		return data.size();
	}
}
