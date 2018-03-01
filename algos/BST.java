package algos;

public class BST {

	public static void main(String[] args) {

		BST bst = new BST();
		Node node = new Node(50);
		bst.addChild(node, node, 60);
		bst.addChild(node, node, 30);
		bst.addChild(node, node, 80);
		bst.addChild(node, node, 70);
		bst.addChild(node, node, 20);
		bst.addChild(node, node, 25);
		bst.addChild(node, node, 15);
		bst.addChild(node, node, 90);
		
		bst.printBST(node);
		System.out.println("-----InOrder/Ascending Traversal-----");
		bst.inOrder(node);
		System.out.println("\n---Descending Order---");
		bst.postOrder(node);
		System.out.println("\n---Search---");
		System.out.println(bst.searchKey(node, 70));
	}
	
	//Print in Ascending order
	public void inOrder(Node node)
	{
		if(node==null)
			return;
		else {
			inOrder(node.getLeft());
			System.out.print(node.getData() + " ");
			inOrder(node.getRight());
		}
	}
	
	//Print in Descending order
	public void postOrder(Node node)
	{
		if(node==null)
			return;
		else {
			postOrder(node.getRight());
			System.out.print(node.getData() + " ");
			postOrder(node.getLeft());
		}
		
	}
	
	static boolean isFound = false;
	boolean searchKey(Node node, int key)
	{
		if(node==null)
			return isFound;
		else
		{
			searchKey(node.getLeft(), key);
			if(node.getData()==key)
				isFound = true;
			searchKey(node.getRight(), key);
		}
		return isFound;
	}
	
	public void printBST(Node node)
	{
		if(node == null)
			return;
		else {
			StringBuilder sb = new StringBuilder("");
			if(node.getLeft()!=null)
				sb.append(node.getLeft().getData()+" <- ");
			sb.append(node.getData());
			if(node.getRight()!=null)
				sb.append(" -> "+node.getRight().getData());
			System.out.println(sb);
		}
		printBST(node.getLeft());
		printBST(node.getRight());
	}
	
	public Node addChild(Node parent, Node node, int childData)
	{
		if(node==null)
		{
			node = new Node(childData);
			System.out.println("New node created for "+node.getData());
			if(childData<parent.getData())
				parent.setLeft(node);
			else if(childData>parent.getData())
				parent.setRight(node);
		}
		else if(childData>node.getData())
		{
			addChild(node, node.getRight(), childData);
		}
		else if(childData<node.getData())
		{
			addChild(node, node.getLeft(), childData);
		}
		return node;
	}

}

class Node {
	private Node left=null, right=null;
	private int data;
	
	Node(int value)
	{
		data = value;
	}
	
	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}
