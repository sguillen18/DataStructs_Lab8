package tree;
import java.util.Iterator;

public interface TreeIteratorInterface <T> {
	
	public Iterator <T> getPreOrderIterator();

	public Iterator <T> getLevelOrderIterator();
	
	public Iterator <T> getPostOrderIterator();
	
	public Iterator <T> getInOrderIterator();
}
