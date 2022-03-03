
public class InternalNode extends Node{
    
    private Node A, C, G, T, $;
    
    public InternalNode(FlyweightNode fw, int level) {
        A = fw;
        C = fw;
        G = fw;
        T = fw;
        $ = fw;
        
        setLevel(level);          
    }
    
    public void addNode(Node node, char c) {
        if (c == 'A') {
            A = node;
        }
        else if (c == 'C') {
            C = node;
        }
        else if (c == 'G') {
            G = node;
        }
        else if (c == 'T') {
            T = node;
        }       
        else if (c == 'E') {
            $ = node;
        }
    }
    public Node getNode(char c) {
        if (c == 'A') {
            return A;
        }
        else if (c == 'C') {
            return C;
        }
        else if (c == 'G') {
            return G;
        }
        else if (c == 'T') {
            return T;
        }       
        else if (c == 'E') {
            return $;
        }
        return null;
    }
    
    public int getTotalFlyNode() {
        int sum = 0;
        if (A instanceof FlyweightNode) {
            sum++;
        }
        if (C instanceof FlyweightNode) {
            sum++;
        }
        if (G instanceof FlyweightNode) {
            sum++;
        }
        if (T instanceof FlyweightNode) {
            sum++;
        }
        if ($ instanceof FlyweightNode) {
            sum++;
        }
        return sum;
    }

}
