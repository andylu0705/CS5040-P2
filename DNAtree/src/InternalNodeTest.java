import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InternalNodeTest {
    private InternalNode internalNodeObj0;
    private InternalNode internalNodeObj1;
    private InternalNode internalNodeObj2;
    private InternalNode internalNodeObj3;
    private InternalNode internalNodeObj4;
    private InternalNode internalNodeObj5;
    private InternalNode internalNodeObj6;
    private FlyweightNode flyweightNodeObj;
    private LeafNode leafNodeObjAC;
    private LeafNode leafNodeObjACG;
    private LeafNode leafNodeObjGT;

    @Before
    public void setUp() {
        internalNodeObj0 = new InternalNode(new FlyweightNode(), 0);
        internalNodeObj1 = new InternalNode(new FlyweightNode(), 1);

        internalNodeObj3 = new InternalNode(new FlyweightNode(), 3);
        internalNodeObj4 = new InternalNode(new FlyweightNode(), 4);
        internalNodeObj5 = new InternalNode(new FlyweightNode(), 5);
        internalNodeObj6 = new InternalNode(new FlyweightNode(), 6);

        flyweightNodeObj = new FlyweightNode();
        leafNodeObjAC = new LeafNode("AC", 0);
        leafNodeObjACG = new LeafNode("ACG", 0);
        leafNodeObjGT = new LeafNode("GT", 0);

    }
    @Test
    public void testDefaultNodes() {
        assertTrue(internalNodeObj0.getNode('A') instanceof FlyweightNode);
        assertTrue(internalNodeObj0.getNode('C') instanceof FlyweightNode);
        assertTrue(internalNodeObj0.getNode('G') instanceof FlyweightNode);
        assertTrue(internalNodeObj0.getNode('T') instanceof FlyweightNode);
        assertTrue(internalNodeObj0.getNode('E') instanceof FlyweightNode);
    }
    @Test
    public void testAddFlyweight_ACGTE_addNode() {
        internalNodeObj1.addNode(flyweightNodeObj, 'A');
        assertTrue(internalNodeObj1.getNode('A') instanceof FlyweightNode);

        internalNodeObj1.addNode(flyweightNodeObj, 'C');
        assertTrue(internalNodeObj1.getNode('C') instanceof FlyweightNode);

        internalNodeObj1.addNode(flyweightNodeObj, 'G');
        assertTrue(internalNodeObj1.getNode('G') instanceof FlyweightNode);

        internalNodeObj1.addNode(flyweightNodeObj, 'T');
        assertTrue(internalNodeObj1.getNode('T') instanceof FlyweightNode);

        internalNodeObj1.addNode(flyweightNodeObj, 'E');
        assertTrue(internalNodeObj1.getNode('E') instanceof FlyweightNode);
    }
    @Test
    public void testaddLeaf_ACGTE_addNode() {
        internalNodeObj3.addNode(leafNodeObjAC, 'A');
        assertTrue(internalNodeObj3.getNode('A') instanceof LeafNode);
        internalNodeObj3.addNode(leafNodeObjAC, 'C');
        assertTrue(internalNodeObj3.getNode('C') instanceof LeafNode);
        internalNodeObj3.addNode(leafNodeObjAC, 'G');
        assertTrue(internalNodeObj3.getNode('G') instanceof LeafNode);
        internalNodeObj3.addNode(leafNodeObjAC, 'T');
        assertTrue(internalNodeObj3.getNode('T') instanceof LeafNode);
        internalNodeObj3.addNode(leafNodeObjAC, 'E');
        assertTrue(internalNodeObj3.getNode('E') instanceof LeafNode);
    }
    @Test
    public void testaddInternalNode_ACGTE_addNode() {
        InternalNode internalNodeObjA = new InternalNode(new FlyweightNode(), 1);
        InternalNode internalNodeObjC = new InternalNode(new FlyweightNode(), 1);
        InternalNode internalNodeObjG = new InternalNode(new FlyweightNode(), 1);
        InternalNode internalNodeObjT = new InternalNode(new FlyweightNode(), 1);
        InternalNode internalNodeObjE = new InternalNode(new FlyweightNode(), 1);
        internalNodeObj4.addNode(internalNodeObjA, 'A');
        assertTrue(internalNodeObj4.getNode('A') instanceof Node);
        internalNodeObj4.addNode(internalNodeObjC, 'C');
        assertTrue(internalNodeObj4.getNode('C') instanceof Node);
        internalNodeObj4.addNode(internalNodeObjG, 'G');
        assertTrue(internalNodeObj4.getNode('G') instanceof Node);
        internalNodeObj4.addNode(internalNodeObjT, 'T');
        assertTrue(internalNodeObj4.getNode('T') instanceof Node);
        internalNodeObj4.addNode(internalNodeObjE, 'E');
        assertTrue(internalNodeObj4.getNode('E') instanceof Node);
    }
    @Test
    public void testaddNode_ACGTE_addNode() {
        Node simpleNodeObjA = new Node();
        Node simpleNodeObjC = new Node();
        Node simpleNodeObjG = new Node();
        Node simpleNodeObjT = new Node();
        Node simpleNodeObjE = new Node();
        internalNodeObj5.addNode(simpleNodeObjA, 'A');
        assertTrue(internalNodeObj5.getNode('A') instanceof Node);
        internalNodeObj5.addNode(simpleNodeObjC, 'C');
        assertTrue(internalNodeObj5.getNode('C') instanceof Node);
        internalNodeObj5.addNode(simpleNodeObjG, 'G');
        assertTrue(internalNodeObj5.getNode('G') instanceof Node);
        internalNodeObj5.addNode(simpleNodeObjT, 'T');
        assertTrue(internalNodeObj5.getNode('T') instanceof Node);
        internalNodeObj5.addNode(simpleNodeObjE, 'E');
        assertTrue(internalNodeObj5.getNode('E') instanceof Node);
    }

    @Test
    public void testNonExist_getNode() {
        assertNull(internalNodeObj6.getNode('B'));
        assertNull(internalNodeObj6.getNode('D'));
        assertNull(internalNodeObj6.getNode('F'));
    }

    @Test
    public void getTotalFlyNode() {
        assertEquals(5, internalNodeObj0.getTotalFlyNode());
        assertEquals(5, internalNodeObj1.getTotalFlyNode());
        assertEquals(0, internalNodeObj3.getTotalFlyNode());
    }
}