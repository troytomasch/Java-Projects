import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		// Data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		// Constructors
		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			this.right = null;
			this.left = null;
		}
		
		// Methods
		public Node<E> rotateright() {
			Node<E> rootcopy = new Node<E>(this.data, this.priority);
			rootcopy.right = this.right;
			if (this.left.right != null) {
				Node<E> movable = new Node<E>(this.left.right.data, this.left.right.priority);
				movable.right = this.left.right.right;
				movable.left = this.left.right.left;
				rootcopy.left = movable;
			}
			this.data = this.left.data;
			this.priority = this.left.priority;
			this.left = this.left.left;
			this.right = rootcopy;
			return this;
		}
		
		public Node<E> rotateleft() {
			Node<E> rootcopy = new Node<E>(this.data, this.priority);
			rootcopy.left = this.left;
			if (this.right.left != null) {
				Node<E> movable = new Node<E>(this.right.left.data, this.right.left.priority);
				movable.left = this.right.left.left;
				movable.right = this.right.left.right;
				rootcopy.right = movable;
			}
			this.data = this.right.data;
			this.priority = this.right.priority;
			this.right = this.right.right;
			this.left = rootcopy;
			return this;
		}
		
		public String toString() {
			String s = "(key = " + data + ", priority = " + priority + ")";
			return s;
		}
	}
	
	// Data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	// Constructors
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	// Methods
	public boolean add(E key) {
		int rando = this.priorityGenerator.nextInt();
		return add(key, rando);
	}
	
	public void reheap(Stack<Node <E>> path, Node<E> newNode) {
		if (path.empty() == false) {
			Node<E> s = path.pop();
			if (s.priority < newNode.priority) {
				if (s.data.compareTo(newNode.data) < 0) {
					s.rotateleft();
				}
				if (s.data.compareTo(newNode.data) > 0) {
					s.rotateright();
				}
				reheap(path, newNode);
			}
		}
	}
	
	public boolean add(E key, int priority) {
		Node<E> newNode = new Node<E>(key, priority);
		if (root == null) {
			root = newNode;
			return true;
		}
		else {
			Stack<Node <E>> path = new Stack<Node<E>>();
			Node<E> current = root;
			while (current != null) {
				path.push(current);
				if (key.compareTo(current.data) == 0) {
					return false;
				}
				else {
					if (key.compareTo(current.data) < 0) {
						if (current.left == null) {
							current.left = newNode;
							reheap(path, newNode);
							return true;
						}
						else {
							current = current.left;
						}
					}
					else {
						if (key.compareTo(current.data) > 0) {
							if (current.right == null) {
								current.right = newNode;
								reheap(path, newNode);
								return true;
							}
							else {
								current = current.right;
							}
					}
					}
				}
			}
			return true;
		}
	}
	
	public boolean delete(E key) {
		Node<E> current = root;
		Node<E> prev = new Node<E>(null, 0);
		if (find(key) == false) {
			return false;
		}
		while(current.data.compareTo(key) != 0) {
			int n = current.data.compareTo(key);
			if (n > 0) {
				prev = current;
				current = current.left;
			}
			if (n < 0) {
				prev = current;
				current = current.right;
			}
		}
		while (current.right != null || current.left != null) {
			if (current.left == null) {
				current.rotateleft();
				prev = current;
				current = current.left;
			}
			else if (current.right == null) {
				current.rotateright();
				prev = current;
				current = current.right;
			}
			else {
				if (current.left.priority > current.right.priority) {
					current.rotateright();
					prev = current;
					current = current.right;
				}
				else {
					current.rotateleft();
					prev = current;
					current = current.left;				
				}
			}
		}
		if (prev.left != null) {
			if (prev.left.data.compareTo(current.data) == 0) {
				prev.left = null;
				return true;
			}
		}
		if (prev.right != null) {
			if (prev.right.data.compareTo(current.data) == 0) {
				prev.right = null;
				return true;
			}	
		}
		return false;
	}
	
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		else {
			if (key.compareTo(root.data) == 0) {
				return true;
			}
		}
		return find(root.right, key) || find(root.left, key);
	}
	
	public boolean find(E key) {
		return find(root, key);
	}
	
	private StringBuilder toString(Node<E> current, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append("-");
		}
		
		if (current==null) {
			r.append("null\n");
		} else {
			r.append(current.toString()+"\n");
			r.append(toString(current.left,i+1));
			r.append(toString(current.right,i+1));
			
		}
		return r;
		
	}
	
	public String toString() {
		return toString(root,0).toString();
	}
	
	public static void main(String args[]) {
		Treap<Integer> s = new Treap<Integer>();
//		s.add(1, 84);
//		s.add(5, 83);
//		s.add(2, 31);
//		s.add(6, 70);
//		s.add(4, 14);
//		s.add(7, 26);
//		s.add(3, 12);
//		System.out.println(s);
		

//		System.out.println(s);

//		s.add(1, 50);
//		s.add(2, 40);
//		s.add(4, 20);
//		s.add(3, 30);
//		
//		s.add(4, 19);
//		s.add(2, 31);
//		s.add(6, 70);
//		s.add(1, 84);
//		s.add(3, 12);
//		s.add(5, 83);
//		s.add(7, 26);
//		
//		System.out.println(s);
//		
//		s.delete(5);
//		System.out.println(s);
		
//		s.delete(3);
//		for (int i = 1; i<10; i++) {
//			System.out.println(s.find(i));
//		}
//		System.out.println(s);
		s.add(4, 25);
		s.add(2, 16);
		s.add(6, 78);
		s.add(1, 37);
		s.add(3, 14);
		s.add(7, 8);
		s.add(5, 19);
		
		s.delete(5);
		System.out.println(s);
	}
}
