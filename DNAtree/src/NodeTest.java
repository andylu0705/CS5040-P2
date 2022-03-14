import org.junit.*;
import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testGetter_getLevel() {
        Node NodeObject = new Node();
        NodeObject.setLevel(3);
        assertEquals(3, NodeObject.getLevel());
    }

    @Test
    public void testSetter_setLevel() {
        Node NodeObject = new Node();
        NodeObject.setLevel(2);
        assertEquals(2, NodeObject.getLevel());
    }
}