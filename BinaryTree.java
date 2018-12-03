package Lab8;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTree <T> {

	private BinaryNode<T> root;
	public BinaryTree () {
	   root = null;
	 }
	public BinaryTree (T rootData) {
	   root = new BinaryNode <> (rootData);
	 }
	public BinaryTree (T rootData, BinaryTree <T> leftTree,
                            	 BinaryTree <T> rightTree) {
		initializeTree (rootData, leftTree, rightTree);					 
	 }
	
	public boolean isEmpty() {
		return root == null;
	}
	 
	public void setTree (T rootData) {
	    root = new BinaryNode <> (rootData);
	 }
	 
	@SuppressWarnings("unchecked")
	public void setTree (T rootData, 
                           BinaryTreeInterface <T> leftTree,
                           BinaryTreeInterface <T> rightTree) {
	    initializeTree (rootData, (BinaryTree <T>) leftTree, 
		                         (BinaryTree <T>) rightTree);
	 }
	 
	private void initializeTree (T rootData, 
            BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
	   root = new BinaryNode <> (rootData);
        if (root.leftChild != null)	
           root.setLeftChild (leftTree);
        if (root.rightChild != null)	
           root.setRightChild (rightTree);	   
	 }

	
	public void setRoot(T rootData) {
		root = new BinaryNode <T>(rootData);
	}
	
	public BinaryNode <T> getRootData(){
		return root;
	}
	
	public void setLeftTree(T left) {
		BinaryNode <T> leftTree = new BinaryNode <T>(left);
		root.setLeftChild(leftTree);
	}
	
	public void setRightTree(T right) {
		BinaryNode <T> rightTree = new BinaryNode <T>(right);
		root.setLeftChild(rightTree);
	}
	
	public int getHeight() {
		return root.getHeight();
	}
	
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}
	
	public void getLeaves() {
		if(root.isLeaf()) {
			System.out.println(root);
		}
		else {
			root.getLeftChild();
		}
	}


	class BinaryNode <T> implements BinaryNodeInterface <T>{
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

		public BinaryNodeInterface<T> getLeftChild () {
			return leftChild;
		}

		public BinaryNodeInterface<T> getRightChild () {
			return rightChild;
		}	   
		public void setLeftChild (BinaryNodeInterface<T> newLeftChild) {
			leftChild = (BinaryNode <T>) newLeftChild;
		}

		public boolean hasLeftChild () {
			return (leftChild != null);
		}

		public void setRightChild (BinaryNodeInterface<T> newRightChild) {
			rightChild = (BinaryNode <T>) newRightChild;
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

		public BinaryNodeInterface <T> copy() {
			BinaryNode <T> copied = new BinaryNode <T>(data);
			
			if(hasLeftChild())
				copied.setLeftChild((BinaryNode<T>) leftChild.copy());
			if(hasRightChild())
				copied.setRightChild((BinaryNode<T>) rightChild.copy());
			return (BinaryNodeInterface <T>)copied;
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
			currNode = root;
			nodeStack = new Stack();

			addToStack(root);

		}
		private void addToStack(BinaryNode<T> addedNode) {
			if (addedNode.hasRightChild()) {
				addToStack( (BinaryNode <T>) addedNode.getRightChild());
			}
			if (addedNode.hasLeftChild()) {
				addToStack((BinaryNode <T>) addedNode.getLeftChild());
			}
			nodeStack.push(addedNode);
		}

		public T next() {
			return(nodeStack.pop().getData());
		}

		public boolean hasNext() {
			return(!nodeStack.isEmpty());
		}
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
}
	