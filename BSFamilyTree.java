
/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    //Data Fields
    //TODO
	private FamilyTreeNode root;
	
    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
        //TODO
    	root = null;
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
        //TODO
    	if (root == null) {
    		return false;
    	}
    	else {
    		FamilyTreeNode current = root;
    		while (current != null) {
    			if (current.getLastName().equals(lastName)) {
    				return true;
    			}
    			else if (current.getLastName().compareTo(lastName) > 0) {
    				current = current.left;
    			}
    			else {
    				current = current.right;
    			}
    		}
    		return false;
    	}
    }

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
        //TODO
    	FamilyTreeNode newfam = new FamilyTreeNode(lastName); 
    	if (this.doesFamilyExist(lastName)) {
    		throw new IllegalArgumentException("Family Already Exists");
    	}
    	else if (root == null) {
    		root = newfam;
    	}
    	else {
    		int added = 0;
    		FamilyTreeNode current = root;
    		while (added == 0) {
    			if (current.getLastName().compareTo(lastName) < 0) {
    				if (current.right == null) {
    					current.right = newfam;
    					added = 1;
    				}
    				else {
    					current = current.right;
    				}
    			}
    			else {
    				if (current.left == null) {
    					current.left = newfam;
    					added = 1;
    				}
    				else {
    					current = current.left;
    				}
    			}
    		}
    	}
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
        //TODO
    	if (this.doesFamilyExist(lastName) == false) {
    		throw new IllegalArgumentException("Family does not exist");
    	}
    	if (root == null) {
    		throw new IllegalArgumentException("No families exist");
    	}
    	FamilyTreeNode current = root;
		while (current != null) {
			if (current.getLastName().equals(lastName)) {
				return current;
			}
			else if (current.getLastName().compareTo(lastName) > 0) {
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		return current;
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    private boolean doesNumberExistHelper(FamilyTreeNode current, String phoneNumber) {
    	if (current == null) {
    		return false;
    	}
    	if (current.doesNumberExist(phoneNumber)) {
    		return true;
    	}
    	return doesNumberExistHelper(current.left, phoneNumber) || doesNumberExistHelper(current.right, phoneNumber);
    }
    
    
    public boolean doesNumberExist(String phoneNumber) {
        //TODO
    	return doesNumberExistHelper(root, phoneNumber);
    }

    /**
     * Returns the string representation of the BSFamilyTree
     */
    private StringBuilder toString(FamilyTreeNode current, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append("  ");
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
    
    public static void main(String[] args) {
//		BSFamilyTree s = new BSFamilyTree();
//		s.addFamilyTreeNode("Bart");
//		s.addFamilyTreeNode("Brady");
//		s.addFamilyTreeNode("Bunyan");
//		s.addFamilyTreeNode("Bob");
//		
//		System.out.println(s);
    }
}
