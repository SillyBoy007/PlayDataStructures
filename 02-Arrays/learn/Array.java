import java.util.Objects;

/**
 * @author admin
 */
public class Array {

    /**
     * 数组元素的个数
     */
    private int size;
    private Object[] data;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    public Array() {
        this(10);
    }

    public Array(int initCapacity) {
        if (initCapacity > 0) {
            data = new Object[initCapacity];
        } else if (initCapacity == 0) {
            data = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException();
        }
        this.size = 0;
    }

    public void add(int index, Object e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        /**
         * 若当前数组长度已到达上限,进行扩容
         */
        if (size == data.length) {
            this.resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addLast(Object e) {
        this.add(size, e);
    }

    public void addFirst(Object e) {
        this.add(0, e);
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        Object ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        // loitering objects != memory leak
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;

    }

    public void modify(int index, Object e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        data[index] = e;
    }

    public Object find(Object e) {
        for (int i = 0; i < data.length; i++) {
            if (Objects.equals(e, data[i])) {
                return data[i];
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        if (newCapacity < 0) {
            throw new IllegalArgumentException();
        }
        Object[] newObject = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newObject[i] = data[i];
        }
        data = newObject;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size:%s ,capacity:%s [", size, data.length));

        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (!Objects.equals(i, size - 1)) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        Array array = new Array(2);
        array.add(0, 1);
        array.add(1, 2);
        array.add(2, 3);
        array.add(3, 3);

        array.add(4, 3);
        array.add(5, 3);

        array.remove(1);
        //array.remove(1);

        System.out.println(array);
    }
}
