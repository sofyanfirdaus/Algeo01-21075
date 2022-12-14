package tubes.algeo;

import java.util.Locale;

public class Row {
    private double[] data;

    public Row(int length) {
        data = new double[length];
    }

    public Row(double[] elements) {
        data = elements;
    }

    public void add(Row other) {
        if (other.getData().length != data.length) {
            throw new RuntimeException("Both data lengths must be the same");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] + other.data[i];
        }
    }

    public void multiply(double num) {
        for (int i = 0; i < data.length; i++) {
            data[i] *= num;
        }
    }

    public double[] getData() {
        return data;
    }

    public double getElement(int index) {
        return data[index];
    }

    public void setElement(int index, double value) {
        data[index] = value;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int length() {
        return data.length;
    }

    public void expand(int length) {
        double[] newData = new double[length() + length];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void concat(Row other) {
        int prevLength = length();
        expand(other.length());
        for (int i = prevLength; i < length(); i++) {
            setElement(i, other.getElement(i - prevLength));
        }
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < length(); i++) {
            if (i > 0) s += ", ";       
            s += String.format(Locale.US, "%.2f ", data[i]);
        }
        s += "]";
        return s;
    }

    public static Row add(Row a, Row b) {
        if (a.getData().length != b.getData().length) {
            throw new RuntimeException("Both data lengths must be the same");
        }
        Row result = new Row(a.getData());
        result.add(b);
        return result;
    }

    public static Row multiply(Row row, double num) {
        Row result = new Row(row.getData());
        result.multiply(num);
        return result;
    }
}
