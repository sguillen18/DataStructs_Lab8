package Lab8;

public class Testing {

	public static void main(String[] args) {
		//leaves
		BinaryTree <Character> l = new BinaryTree <Character>('L');
		BinaryTree <Character> h = new BinaryTree <Character>('H');
		BinaryTree <Character> j = new BinaryTree <Character>('J');
		BinaryTree <Character> k = new BinaryTree <Character>('K');
		BinaryTree <Character> e = new BinaryTree <Character>('E');
		BinaryTree <Character> f = new BinaryTree <Character>('F');
		
		BinaryTree <Character> g = new BinaryTree <Character>('G', j, k);
		BinaryTree <Character> i = new BinaryTree <Character>('I', null, l);
		BinaryTree <Character> d = new BinaryTree <Character>('D', h, i);
		BinaryTree <Character> b = new BinaryTree <Character>('B', d, e);
		BinaryTree <Character> c = new BinaryTree <Character>('C', f, g);
		//root
		BinaryTree <Character> a = new BinaryTree <Character>('A', b, c);
		
		System.out.println("The height of this tree is: " + a.getHeight());
		
		System.out.println("The number of nodes in this tree is: " + a.getNumberOfNodes());
		
		System.out.println("These are the leaves of the tree: ");
		a.getLeaves();
		System.out.println("\n");
		
		System.out.println("Inorder: ");
		a.printInorder();
		System.out.println("\n");
		
		System.out.println("Levelorder: ");
		a.printLevelorder();
		System.out.println("\n");
		
		System.out.println("Postorder: ");
		a.printPostorder();
		System.out.println("\n");
		
		System.out.println("Preorder: ");
		a.printPreorder();
		System.out.println("\n");
		

	}

}
