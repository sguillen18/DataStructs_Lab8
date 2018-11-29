package Lab8;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTree <T> {

	private BinaryNode<T> root;
	
	
	class BinaryNode <T>
	{
	     private T data;
	     private BinaryNode<T> leftChild;
		private BinaryNode<T> rightChild;
			   
		public BinaryNode () {
		   this (null);
		}
			   
		public BinaryNode (T rootData) {
		   this (rootData, null, null);
		}
			   
		public BinaryNode (T rootData, BinaryNode <T> newLeftChild,
	                                    BinaryNode <T> newRightChild) {
		   data = rootData;
		   leftChild = newLeftChild;
		   rightChild = newRightChild;
		}
			   
		public T getData() {
		   return data;
		}
			   
		public void setData (T newData) {
		   data = newData;
		}
			   
		public BinaryNode<T> getLeftChild () {
		    return leftChild;
		}
		
		public BinaryNode<T> getRightChild () {
		    return rightChild;
		}	   
		public void setLeftChild (BinaryNode<T> newLeftChild) {
		    leftChild = newLeftChild;
		}
			   
		public boolean hasLeftChild () {
		   return (leftChild != null);
		}
		
		public void setRightChild (BinaryNode<T> newRightChild) {
		    leftChild = newRightChild;
		}
			   
		public boolean hasRightChild () {
		   return (rightChild != null);
		}
			
		public boolean isLeaf () {
		   return (leftChild == null && (rightChild == null));
		}
		
		public int getHeight() {
		     int leftHeight = hasLeftChild() ? leftChild.getHeight() : 0;
		     int rightHeight = hasRightChild() ? rightChild.getHeight() : 0;
			return 1 + Math.max (leftHeight, rightHeight);
		}

		public int getNumberOfNodes () {
			int leftNodes = hasLeftChild() ? leftChild.getNumberOfNodes() : 0;
		    int rightNodes = hasRightChild() ? rightChild.getNumberOfNodes() : 0;
			return 1 + leftNodes + rightNodes;
		}

		public BinaryNode <T> copy() {
			   BinaryNode <T> copied = new BinaryNode(data);
			   if(hasLeftChild())
				   copied.setLeftChild(leftChild.copy());
			   if(hasRightChild())
				   copied.setRightChild(rightChild.copy());
			   return copied;
			}

	}
	
	public Iterator<T> getPreorderIterator(){
		return new PreorderIterator();
	}
	
	private class PreorderIterator implements Iterator<T>{		
		private BinaryNode<T> currNode;
		private Stack <BinaryNode<T>> nodeStack;
		private BinaryNode<T> leftChild;
		private BinaryNode<T> rightChild;
		
		public PreorderIterator() {
			//currNode = root;
			nodeStack = new Stack();
			
			addToStack(root);
			
		}
		
		private class InorderIterator implements Iterator <T> {
			private Stack <BinaryNode<T>> nodeStack;
			
			public InorderIterator() {
				nodeStack = new Stack <> ();
				addToStack (root);
			}
				
			private void addToStack (BinaryNode <T> aNode) {
				if (aNode == null)
					return;
			     BinaryNode <T> right = 
		                         (BinaryNode <T>)aNode.getRightChild();
				BinaryNode <T> left = 
		                         (BinaryNode <T>)aNode.getLeftChild();	
				addToStack (right);
				nodeStack.push(aNode);
				addToStack (left);
			}
			
		public boolean hasNext() {
				return (!nodeStack.isEmpty());
			}
				
			public T next() {
				return nodeStack.pop().getData();
			}

		      public void remove() {
				throw new UnsupportedOperationException();
			}
				
		}

		
		
		
		private void addToStack(BinaryNode<T> addedNode) {
			if (addedNode.hasRightChild()) {
				addToStack(addedNode.getRightChild());
			}
			if (addedNode.hasLeftChild()) {
				addToStack(addedNode.getLeftChild());
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
			if(leftChild!=null) {
				n += leftChild.getNumberOfNodes();
			}
			return n;
		}
		
		
		/*public int getNumberOfNodes() {
			int n=1;
			if (rightChild != null) {
				n += rightChild.getNumberOfNodes();
			}
		}*/
		
		/*
		public T next() {
			T outData = currNode.getData();
			if (currNode.hasRightChild()) {
				nodeStack.push(currNode.getRightChild());
				//DOES THIS IF STATEMENT STAY NESTED?
				//OR IS IT OUTSIDE OF THE ABOVE FOR LOOP?
				if (currNode.hasLeftChild()) {
					currNode = currNode.getLeftChild();
				}
				else if(!nodeStack.isEmpty()) {
					currNode = nodeStack.pop();
				}
				else {
					currNode = null;
				}
			}
			return outData;
		}
		*/
	}
	
}