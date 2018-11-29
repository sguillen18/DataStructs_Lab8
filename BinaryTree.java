package Lab8;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTree <T> {

	private BinaryNode<T> root;
	
	public Iterator<T> getPreorderIterator(){		
		//create inner class
		return new PreorderIterator<>;
	}
	
	public BinaryTree() {
		root = null;
	}
	
	
	
	
	
	
	
	private class PreorderIterator implements Iterator<T>{		
		private BinaryNode<T> currNode;
		private Stack <BinaryNode<T>> nodeStack;
		private BinaryNode<T> leftChild;
		private BinaryNode<T> rightChild;
		
		public PreorderIterator() {
			nodeStack = new Stack();			
			addToStack(root);
			
		}		
		
		private void addToStack(BinaryNode<T> addedNode) {
			if (addedNode.hasRightChild()) {
				addToStack(addedNode.getRightChild);
			}
			if (addedNode.hasLeftChild()) {
				addToStack(addedNode.getLeftChild);
			}
			nodeStack.push(addedNode);
		}
		
		public T next() {
			return(nodeStack.pop().getData());
		}
		
		public boolean hasNext() {
			return(!nodeStack.isEmpty());
		}
		
		public int getNumberOfNodes() {
			int n = 1;
			if(rightChild!=null) {
				n += rightChild.getNumberOfNodes();
			}
			if(leftChild!-null) {
				n += leftChild.getNumberOfNodes();
			}
			return n;
		}

	}
	
}
