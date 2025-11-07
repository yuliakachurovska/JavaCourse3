package hw7;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class B07_01 {
    public static void main(String[] args) {
        String inp = "src/hw7/input.bin";
        String out = "src/hw7/output.bin";

        double[] array = {1.5, -2.0, 3.14, 7.0, 0.5, 10.1};
        double a = 2.0;

        write(inp, array);
        array = read(inp);
        System.out.println("Файл F: " + Arrays.toString(array));

        var lst = new ArrayList<Double>();
        for (double x : array) {
            if (x > a) lst.add(x);
        }

        writeWithByteBuffer(out, lst);
        double[] filtered = readWithByteBuffer(out);
        System.out.println("Файл G (x > " + a + "): " + Arrays.toString(filtered));
    }

    public static void write(String filename, double[] array) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
            for (double x : array) out.writeDouble(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double[] read(String filename) {
        double[] result;
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {
            int count = (int) new File(filename).length() / Double.BYTES;
            result = new double[count];
            for (int i = 0; i < count; i++) result[i] = in.readDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void writeWithByteBuffer(String filename, ArrayList<Double> list) {
        try (FileOutputStream fout = new FileOutputStream(filename)) {
            ByteBuffer bb = ByteBuffer.allocate(list.size() * Double.BYTES);
            for (double x : list) bb.putDouble(x);
            fout.write(bb.array());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double[] readWithByteBuffer(String filename) {
        double[] result;
        try (FileInputStream finp = new FileInputStream(filename)) {
            ByteBuffer bb = ByteBuffer.wrap(finp.readAllBytes());
            result = new double[bb.capacity() / Double.BYTES];
            for (int i = 0; i < result.length; i++) {
                result[i] = bb.getDouble();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
