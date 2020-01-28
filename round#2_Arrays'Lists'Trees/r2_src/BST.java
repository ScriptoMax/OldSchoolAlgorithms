//case #5: create a BST exemplar to walk around its nodes 

import java.util.LinkedList;
import java.util.Queue;

public class BST {
		
	BSTNode node;
	
	public BST() {
		node = null;
	}
	
	public void runInsert(double val) {
		node = insert(node, val);
	}
	
	
	public BSTNode insert(BSTNode localNode, double val) {
		if(localNode == null) {
			return new BSTNode(val);
		}
		
		if(val <  localNode.value) {
			localNode.leftTree = insert(localNode.leftTree, val);
		} else if(val > localNode.value) {
			localNode.rightTree = insert(localNode.rightTree, val);
		} else 
			return localNode;
		
		return localNode;
	}
	
	// in-order traversal  
	public void walkInOrder(BSTNode locaNode) {
		if(locaNode != null) {
			walkInOrder(locaNode.leftTree);
			System.out.print(locaNode.value + " ");
			walkInOrder(locaNode.rightTree);
		}
	}
	
	// pre-order traversal
	public void walkPreOrder(BSTNode localNode) {
		if(localNode != null) {
			System.out.print(localNode.value + " ");
			walkPreOrder(localNode.leftTree);
			walkPreOrder(localNode.rightTree);
		}
	}
	
	// post-order traversal
	public void walkPostOrder(BSTNode locaNode) {
		if(locaNode != null) {
			walkPostOrder(locaNode.leftTree);
			walkPostOrder(locaNode.rightTree);
			System.out.print(locaNode.value + " ");
		}
	}
	
	//level-order traversal	
	public void walkCrossLevels() {
		if(node == null) {
			return;
		}
		
		Queue<BSTNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(node);
		
		while(!nodeQueue.isEmpty()) {
			BSTNode localNode = nodeQueue.remove();
			System.out.print(localNode.value + " ");
			
			if(localNode.leftTree != null) {
				nodeQueue.add(localNode.leftTree);
			} 
			
			if(localNode.rightTree != null) {
				nodeQueue.add(localNode.rightTree);
			}
			
		}
	}
		
}
