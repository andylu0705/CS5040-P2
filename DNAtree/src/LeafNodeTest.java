import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LeafNodeTest {

    @Test
    public void getSequence() {
        LeafNode testNode = new LeafNode("ATCG", 1);
        assertEquals("ATCG", testNode.getSequence());
    }
}