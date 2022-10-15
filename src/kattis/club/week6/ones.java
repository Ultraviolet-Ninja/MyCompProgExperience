package kattis.club.week6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ones {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        Map<BigInteger, Integer> onesMap = new LinkedHashMap<>();
        Set<Map.Entry<BigInteger, Integer>> entries = onesMap.entrySet();
        BigInteger onesSetter = BigInteger.ONE;

        for (int counter = 1; counter <= 1000; counter++) {
            onesMap.put(onesSetter, counter);
            onesSetter = onesSetter.multiply(BigInteger.TEN)
                    .add(BigInteger.ONE);
        }

//        List<BigInteger> numberList = IN.lines().map(BigInteger::new).toList();
        List<BigInteger> numberList = LongStream.range(1, 100_000)
                .filter(e -> e % 2 == 1)
                .filter(e -> e % 5 != 0)
                .mapToObj(e -> new BigInteger(String.valueOf(e)))
                .collect(Collectors.toList());

        for (BigInteger number : numberList) {
            for (var entry : entries) {
                if (entry.getKey().mod(number).equals(BigInteger.ZERO)) {
                    OUT.write(String.valueOf(entry.getValue()).getBytes());
                    break;
                }
            }
            OUT.write("\n".getBytes());
        }
        OUT.flush();
    }
}
