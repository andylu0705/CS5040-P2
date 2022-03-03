
public class Tree {
    
    private Node root;
    private FlyweightNode fw;
    private char[] chars = {'A', 'C', 'G', 'T', 'E'};
    private int visitedCount;
    public Tree() {
        fw = new FlyweightNode();
        root = fw;        
    }
    
    public int insert(String sequence) {
        
        if (root instanceof FlyweightNode) {
            root = new LeafNode(sequence, 0);
            return 0;
        }
        
        if (root instanceof LeafNode) {
              
            if (((LeafNode) root).getSequence().equals(sequence)) {
                return -1;
            }
            
            InternalNode cur = new InternalNode(fw,0);
            cur.addNode(root, ((LeafNode) root).getSequence().charAt(0));
            root.setLevel(1);
            root = cur;
        }
        return insert(sequence, (InternalNode) root);
    }
    
    private int insert(String sequence, InternalNode node) {
        
        char pos;
        if (node.getLevel() < sequence.length()){
            pos = sequence.charAt(node.getLevel());
        } else {
            pos = 'E';
        }
        Node cur = node.getNode(pos);
        
        if (cur instanceof FlyweightNode) { 
            node.addNode(new LeafNode(sequence, node.getLevel() + 1), pos);
            return node.getLevel() + 1;
        }
        
        if (cur instanceof LeafNode) {            
            if (((LeafNode) cur).getSequence().equals(sequence)) {
                return -1;
            }
            
            InternalNode tmp = new InternalNode(fw, cur.getLevel());
            tmp.addNode(cur, ((LeafNode)cur).getSequence().charAt(cur.getLevel()));
            cur.setLevel(cur.getLevel() + 1);
            node.addNode(tmp, sequence.charAt(node.getLevel()));
            cur = tmp;
        }
        
        return insert(sequence,(InternalNode) cur);
        
    }
    
    public boolean remove(String sequence) {
        if (root instanceof FlyweightNode) {
            return false;
        }
        if (root instanceof LeafNode) {
                root = fw;
                if (((LeafNode)root).getSequence().equals(sequence)) {
                    root = fw;
                    return true;
                }
                return false;
        }
        return remove(sequence,(InternalNode)root);
    }
    
    public boolean remove(String sequence, InternalNode node) {
        
        char c = 'E';
        if (node.getLevel() < sequence.length()) {
            c = sequence.charAt(node.getLevel());
        }
        Node next = node.getNode(c);
        if (next instanceof FlyweightNode) {
            return false;
        }
        if (next instanceof LeafNode) {
            if (((LeafNode)next).getSequence().equals(sequence)) {
                node.addNode(fw, c);
                return true;
            }
            return false;
        }
        if (remove(sequence, (InternalNode) next)) {
            if (((InternalNode)next).getTotalFlyNode() == 4) {
                for (char cur : chars) {
                    Node child = ((InternalNode)next).getNode(cur);
                    if (child instanceof LeafNode) {
                        child.setLevel(child.getLevel()-1);
                        node.addNode(child, c);
                        return true;
                    }
                }
            }
            return true;
        }
        return true;
    }
    public String print(boolean lengths, boolean stats) {
        if (root instanceof FlyweightNode) {
            return "E";
        }
        return print(lengths,stats,root,null);
    }
    
    public String print(boolean lengths, boolean stats, Node cur, Node parent) {
        String result = "\n";
        
        int level;
        if (cur instanceof FlyweightNode) {
            level = parent.getLevel() + 1;
        }
        else {
            level = cur.getLevel();
        }
        
        for (int i = 0; i < level; i++) {
            result += " ";
        }
        
        if (cur instanceof FlyweightNode) {
            result += "E";
        }
        else if (cur instanceof LeafNode) {
            result += ((LeafNode)cur).getSequence();
            
            if (lengths) {
                result += " " + ((LeafNode)cur).getSequence().length();
            }
            else if (stats) {
                int[] count = {0, 0, 0, 0};
                String[] propotions = new String[4];
                char[] curWord = ((LeafNode)cur).getSequence().toCharArray();
                for (int i = 0; i < curWord.length;i++) {
                    char w = curWord[i];
                    if (w == 'A') {
                        count[0]++;
                    }
                    else if (w == 'C') {
                        count[1]++;
                    }
                    else if (w == 'G') {
                        count[2]++;
                    }
                    else if (w == 'T') {
                        count[3]++;
                    }
                }
                
                for (int i = 0; i < 4; i++) {
                    propotions[i] = String.format("%.2f",100.0 * count[i] / curWord.length);
                }
                result += " A:" +propotions[0] + " C:" + propotions[1] + " G:" + propotions[2] + " T:" + propotions[3];
            }
        }
        else if (cur instanceof InternalNode) {
            result += "I";
            for (char c : chars) {
                result += print(lengths, stats, ((InternalNode)cur).getNode(c), cur);
            }
            
        }
        
        return result;
    }
    
    public String search(String searchWord) {
        
        if (root instanceof FlyweightNode) {
            return "no sequence found";
        }
        
        String result = "\n";
        visitedCount = 1;
        boolean isWord = false;
        
        if (searchWord.charAt(searchWord.length()-1) == '$') {
            isWord = true;
            searchWord = searchWord.substring(0,searchWord.length()-1);
        }else {
            isWord = false;
        }
        
        if (root instanceof LeafNode) {
            String rootSeq = ((LeafNode)root).getSequence();
            if ( (isWord && rootSeq.equals(searchWord)) ||(!isWord && rootSeq.substring(0,searchWord.length()).equals(searchWord))) {
                result += "sequence: " + searchWord;
            }
            else {
                result += "no sequence found";
            }
        }
        
        else if (root instanceof InternalNode) {
            int count = 0;
            InternalNode next = (InternalNode) root;
            while(count < searchWord.length()) {
                if (next.getNode(searchWord.charAt(count)) instanceof InternalNode) {
                        next = (InternalNode)next.getNode(searchWord.charAt(count));
                } else {
                    break;
                }
                count++;
                visitedCount++;
            }
            
            char pos;
            
            if (count == searchWord.length()) {
                pos = 'E';
            } else {
                pos = searchWord.charAt(count);
            }
            
            Node nextNode = next.getNode(pos);
            if (isWord) {
                if (nextNode instanceof LeafNode && ((LeafNode)nextNode).getSequence().equals(searchWord)) {
                    result += "sequence: " + searchWord;
                } else {
                    result += "no sequence found";
                }
                visitedCount++;
            }
            else {
                if (pos != 'E' && nextNode instanceof LeafNode && ((LeafNode)nextNode).getSequence().substring(0, searchWord.length()).equals(searchWord)) {
                    result += "sequence: " + ((LeafNode)nextNode).getSequence();
                    visitedCount++;
                }
                else if (pos == 'E') {
                    result += printAll((InternalNode)next);
                    visitedCount--;
                    result = result.substring(0, result.length() - 1);
                }
                else {
                    result += "no sequence found";
                    visitedCount++;
                }
            }
            
        }
        return "# of nodes visited: " + visitedCount + result + "\n";
    }
    
    private String printAll(InternalNode node) {
        visitedCount++;
        String result = "";
        
        for (int i = 0; i < 4; i++) {
            if (node.getNode(chars[i]) instanceof LeafNode) {
                result += "Sequence: " + ((LeafNode)node.getNode(chars[i])).getSequence() + "\n";
                visitedCount++;
            }
            else if (node.getNode(chars[i]) instanceof InternalNode) {
                result += printAll((InternalNode)node.getNode(chars[i]));
            }else {
                visitedCount++;
            }
        }
        
        if (node.getNode('E') instanceof LeafNode) {
            result += "Sequence: " + ((LeafNode)node.getNode('E')).getSequence() + "\n";
        }
        visitedCount++;
        return result;
    }

}
