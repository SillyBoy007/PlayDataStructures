import java.util.Arrays;
import java.util.Objects;

public class SingleLink {
    private int index = 0;
    private Object[] retDatas;
    /**
     * 元素个数
     */
    private int count = 0;
    private Node root;


    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public void addNode(Node node) {
            if (this.next == null) {
                this.next = node;
            } else {
                this.next.addNode(node);
            }
        }

        public Object removeNode(Node previous, Object data) {
            if (Objects.equals(data, this.data)) {
                previous.next = this.next;
                return data;
            } else {
                if (Objects.nonNull(this.next)) {
                    this.next.removeNode(this, data);
                }
                return null;

            }
        }

        public void setNode(int index, Object data) {
            if (Objects.equals(SingleLink.this.index++, index)) {
                this.data = data;
            } else {
                if (this.next != null) {
                    this.next.setNode(index, data);
                }
            }
        }

        public Object getNode(int index) {
            if (Objects.equals(SingleLink.this.index++, index)) {
                return this.data;
            } else {
                if (this.next != null) {
                    return this.next.getNode(index);
                }
                return null;
            }
        }

        public boolean containsNode(Object data) {
            if (Objects.equals(data, this.data)) {
                return true;
            } else {
                if (this.next == null) {
                    return false;
                }
                return this.next.containsNode(data);
            }
        }

        public void toArrayNode() {
            SingleLink.this.retDatas[SingleLink.this.index++] = this.data;

            if (this.next != null) {
                this.next.toArrayNode();
            }
        }


    }

    public void add(Object data) {
        if (data == null) {
            return;
        }

        Node newNode = new Node(data);

        if (this.root == null) {
            this.root = newNode;
        } else {
            this.root.addNode(newNode);
        }

        this.count++;
    }

    public boolean contains(Object data) {
        if (data == null || this.root == null) {
            return false;
        }
        return this.root.containsNode(data);
    }

    public Object remove(Object data) {

        if (this.contains(data)) {
            if (Objects.equals(data, this.root.data)) {
                return data;
            }
            this.count--;
            return this.root.removeNode(this.root, data);

        }
        return null;

    }

    public Object get(int index) {
        if (index < 0 || index > this.count || this.root == null) {
            //没有数据
            throw new IllegalArgumentException();
        }
        this.index = 0;
        return this.root.getNode(index);
    }

    public void set(int index, Object data) {
        if (index < 0 || index > this.count || this.root == null) {
            throw new IllegalArgumentException();
        }
        this.index = 0;
        this.root.setNode(index, data);
    }

    public Object[] toArray() {
        if (this.root == null) {
            return new Object[]{};
        }
        this.index = 0;
        this.retDatas = new Object[this.count];
        this.root.toArrayNode();

        return this.retDatas;
    }

    public static void main(String[] args) {
        SingleLink singleLink = new SingleLink();
        singleLink.add(1);
        singleLink.add(2);
        singleLink.add(3);
        System.out.println(singleLink.contains(2));
        singleLink.remove(2);
        System.out.println(singleLink.contains(2));
        System.out.println(Arrays.toString(singleLink.toArray()));
    }
}
