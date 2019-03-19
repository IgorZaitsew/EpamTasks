package by.tc.zaycevigor.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;

    private RandomGenerator() {
        throw new UnsupportedOperationException("Empty constructor is not supported.");
    }

    private RandomGenerator(RandomGeneratorBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
    }

    public static class RandomGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;

        public RandomGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
        }

        public RandomGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public RandomGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public RandomGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public RandomGenerator build() {
            return new RandomGenerator(this);
        }
    }

    public String generate(int length) {
        if (length <= 0) {
            return "";
        }

        StringBuilder randomSequence = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            randomSequence.append(charCategory.charAt(position));
        }
        return new String(randomSequence);
    }
}

