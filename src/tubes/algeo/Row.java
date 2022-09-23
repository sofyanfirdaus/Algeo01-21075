package tubes.algeo;

import java.util.Locale;

public class Row {
    private double[] data;

    public Row(int n) {
        data = new double[n];
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

    @Override
    public String toString() {
        String s = "[ ";
        for (double d : data) {
           s += String.format(Locale.US, "%.2f ", d);
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