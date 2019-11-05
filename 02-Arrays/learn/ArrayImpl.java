import java.util.Objects;

public class ArrayImpl {
    private int size;
    private Object[] data;

    public ArrayImpl() {
        this(10);
    }

    public ArrayImpl(int initCapacity) {
        data = new Object[initCapacity];
    }


    public void add(int index, Object e) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException();
        }
        if (data.length == size) {
            this.resize(data.length * 2);
        }
        for (int i = index + 1; i < size; i++) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[size] = null;
        size--;
        if (data.length / 4 == size && data.length > 0) {
            this.resize(data.length / 2);
        }
    }

    private void resize(int newCapacity) {
        if (newCapacity < 0) {
            throw new IllegalArgumentException();
        }

        Object[] newData = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array capacity:%s, size:%s  [",data.length,size));
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (!Objects.equals(size-1,i)){
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }


    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl(2);

        array.add(0,1);
        array.add(1,2);
        array.add(2,3);
        array.add(3,5);
        array.add(4,5);
        array.remove(4);
        array.remove(3);
        array.remove(2);
        array.remove(1);

        System.out.println(array);
    }
}
