
public class LeafNode extends Node{
    private String sequence;
    
    public LeafNode(String sequence, int level) {
        this.sequence = sequence;
        setLevel(level);
    }
    
    public String getSequence() {
        return sequence;
    }
}
