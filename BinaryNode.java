package Lab8;

public class BinaryNode <T>
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

