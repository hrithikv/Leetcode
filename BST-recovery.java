class BSTRecovery {
    TreeNode[] node_list = new TreeNode[4];    
    TreeNode predecessor_node = null;
    public void recovery(TreeNode root) {                
    	parse_tree(root);
     	for (int i = 0; i < 4; i++) {
            if (node_list[i] != null) {
                for (int j = i + 1; j < 4; j++) {
                    if (node_list[j] != null) {
                        if (node_list[i].val > node_list[j].val) {
                            int temp = node_list[i].val;
                            node_list[i].val = node_list[j].val;
                            node_list[j].val = temp;
                        }    
                    }     			
                }   
            }     		
     	}     	
    }
    
    public void parse_tree(TreeNode root) {                
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {            
            if (predecessor_node == null || root.val > predecessor_node.val) {
                predecessor_node = root;
            } else {
            	if (node_list[0] == null) {
            		node_list[0] = predecessor_node;
            		node_list[1] = root;
            	} else if (node_list[2] == null) {
            		node_list[2] = predecessor_node;
            		node_list[3] = root;
            	}                
            	predecessor_node = root;
            }    
            return;        
        }                        
        parse_tree(root.left);
        if (predecessor_node == null || root.val > predecessor_node.val) {
        	predecessor_node = root;	
        } else {
        	if (node_list[0] == null) {
                node_list[0] = predecessor_node;
                node_list[1] = root;
            } else if (node_list[2] == null) {
                node_list[2] = predecessor_node;
                node_list[3] = root;
            }                   
        	predecessor_node = root;
        }        
        node = root.right;
        parse_tree(node);        
    }    
}
