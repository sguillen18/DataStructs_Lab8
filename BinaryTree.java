package Lab8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree <T> implements BinaryTreeInterface <T> {

	private BinaryNode<T> root;
	public BinaryTree () {
		initializeTree (null, null, null);
	}
	public BinaryTree (T rootData) {
		initializeTree (rootData, null, null);
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
	
	public void clear() {
		
	}

	public void setTree (T rootData, 
			BinaryTreeInterface <T> leftTree,
			BinaryTreeInterface <T> rightTree) {
		initializeTree (rootData, (BinaryTree <T>) leftTree, 
				(BinaryTree <T>) rightTree);
	}

	private void initializeTree (T rootData, 
			BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
		root = new BinaryNode <> (rootData);
		if (leftTree != null)	
			root.setLeftChild (leftTree.root);
		if (rightTree != null)	
			root.setRightChild (rightTree.root);	   
	}


	public void setRoot(T rootData) {
		root = new BinaryNode <T>(rootData);
	}

	public T getRootData(){
		return root.getData();
	}

	public void setLeftTree(T left) {
		BinaryNode <T> leftTree = new BinaryNode <T>(left);
		root.setLeftChild(leftTree);
	}
	
	public BinaryNodeInterface <T> getLeftTree() {
		return root.getLeftChild();
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
		root.getLeaves();
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
		public void setLeftChild (BinaryNode<T> newLeftChild) {
			leftChild = newLeftChild;
		}
		public void setLeftChild (BinaryNodeInterface<T> newLeftChild) {
			leftChild = (BinaryNode<T>) newLeftChild;
		}

		public boolean hasLeftChild () {
			return (leftChild != null);
		}

		public void setRightChild (BinaryNode<T> newRightChild) {
			rightChild = newRightChild;
		}
		public void setRightChild (BinaryNodeInterface<T> newRightChild) {
			rightChild = (BinaryNode<T>) newRightChild;
		}

		public boolean hasRightChild () {
			return (rightChild != null);
		}

		public boolean isLeaf () {
			return (leftChild == null && (rightChild == null));
		}
		
		public void clear() {
			data = null;
			if(hasLeftChild()) {
				leftChild.clear();
				setLeftChild(null);
			}
			if(hasRightChild()) {
				rightChild.clear();
				setRightChild(null);
			}
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
		
		public void getLeaves() {
			if(root.isLeaf()) {
				System.out.print(getData() + " ");
			}
			else {
				if(root.hasLeftChild()) {
					BinaryNode<T> currLeft = leftChild;
					currLeft.getLeaves();
				}
				if(root.hasRightChild()) {
					BinaryNode<T> currRight = rightChild;
					currRight.getLeaves();
				}
			}
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

	public Iterator<T> getPreOrderIterator(){
		return new PreorderIterator();
	}
	
	public void printPreorder() {
		Iterator<T> p = getPreOrderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
		}
	}

	private class PreorderIterator implements Iterator<T>{	
		private Stack <BinaryNode<T>> nodeStack;

		public PreorderIterator() {
			nodeStack = new Stack<>();

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

	public Iterator<T> getInOrderIterator(){
		return new InorderIterator();
	}
	
	public void printInorder() {
		Iterator<T> p = getInOrderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
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
	
	public Iterator<T> getPostOrderIterator(){
		return new PostorderIterator();
	}
	
	public void printPostorder() {
		Iterator<T> p = getPostOrderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
		}
	}

	private class PostorderIterator implements Iterator <T> {
		private Stack <BinaryNode<T>> nodeStack;

		public PostorderIterator() {
			nodeStack = new Stack <> ();
			addToStack (root);
		}

		private void addToStack(BinaryNode<T> addedNode) {
			nodeStack.push(addedNode);
			if (addedNode.hasRightChild()) {
				addToStack( (BinaryNode <T>) addedNode.getRightChild());
			}
			if (addedNode.hasLeftChild()) {
				addToStack((BinaryNode <T>) addedNode.getLeftChild());
			}
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
	
	public Iterator<T> getLevelOrderIterator(){
		return new LevelorderIterator();
	}
	
	public void printLevelorder() {
		Iterator<T> p = getLevelOrderIterator();
		while(p.hasNext()) {
			System.out.print(p.next() + " ");
		}
	}

	private class LevelorderIterator implements Iterator <T> {
		private Queue <BinaryNode<T>> nodeQueue;

		public LevelorderIterator() {
			nodeQueue = new  LinkedList <>();
			int h = root.getHeight();
			for(int i = 1; i <= h; i++)
				addToQueue (root, i);
		}

		private void addToQueue(BinaryNode<T> addedNode, int level) {
			if(addedNode == null)
				return;
			if(level == 1)
				nodeQueue.add(addedNode);
			else if(level > 1) {
				addToQueue(addedNode.leftChild, level-1);
				addToQueue(addedNode.rightChild, level-1);
			}
		}

		public boolean hasNext() {
			return (!nodeQueue.isEmpty());
		}

		public T next() {
			return nodeQueue.remove().getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
	