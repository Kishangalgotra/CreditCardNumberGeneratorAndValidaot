package com.charge.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneratorService {

    public String generate(String uniqueId) {
        List<Integer> list
                = Arrays.stream(uniqueId.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(list);
        return generate("1", list);
    }

    public static String randomNo(int count, List<Integer> intArray) {
        StringBuilder builder = new StringBuilder();
        List<Integer> randomIndexes = Arrays.asList(1, 2, 3, 4, 5);
        while (count-- != 0) {
            int character = (int) (Math.random() * randomIndexes.get(count));
            builder.append(intArray.get(character));
        }
        return builder.toString();
    }

    public static String generate(String bin, List<Integer> array) {
        StringBuilder builder = new StringBuilder(bin);
        builder.append(randomNo(4, array));
        builder.append(randomNo(4, array));
        builder.append(randomNo(4, array));
        builder.append(randomNo(2, array));

        // Do the Luhn algorithm to generate the check digit.
        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);
        return builder.toString();
    }

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
