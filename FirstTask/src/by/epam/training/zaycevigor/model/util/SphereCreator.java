package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.entity.Sphere;
import static by.epam.training.zaycevigor.model.util.ConstContainer.END_OF_LINE;
import static by.epam.training.zaycevigor.model.util.ConstContainer.START_OF_LINE;
import static by.epam.training.zaycevigor.model.util.ConstContainer.VALUES_DIVIDER;
import static by.epam.training.zaycevigor.model.util.SphereDataValidator.NO_END_OF_LINE_EXC_MESSAGE;
import static by.epam.training.zaycevigor.model.util.SphereDataValidator.POSITIVE_AND_NEGATIVE_SIGN;
import static by.epam.training.zaycevigor.model.util.SphereDataValidator.POSITIVE_SIGN;
import static by.epam.training.zaycevigor.model.util.SphereDataValidator.VALUES_COUNT;
import by.epam.training.zaycevigor.model.util.exceptions.CreatorException;
import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public final class SphereCreator implements Creator {

    private static Logger log = Logger.getLogger(SphereCreator.class);

    private static final String ARRAY_IS_NOT_INIT_EXC_MESSAGE = "Some of array values is not been initialized";
    private static final int PARAM_COUNT = 4;

    @Override
    public Sphere[] create(String data) throws CreatorException {
        int count = linesCount(data);
        Sphere[] sphereArray = new Sphere[count];
        int spheresIndex = -1;

        for (int i = 0; i < data.length(); i++) {
            int valueIndex = 0;
            if (START_OF_LINE.equals(data.substring(i, i + 1))) {
                double[] values = new double[PARAM_COUNT];
                int j = i + 1;
                int startOfValue = j;

                while (j < data.length() || data.substring(j, j + 1).matches(END_OF_LINE)) {
                    int endOfValue = elemLocation(data, VALUES_DIVIDER, j);

                    if (endOfValue == -1) {
                        endOfValue = elemLocation(data, END_OF_LINE, j - 1);
                    }

                    values[valueIndex] = Double.parseDouble(data.substring(startOfValue, endOfValue));
                    valueIndex++;

                    if (!data.substring(endOfValue, endOfValue + 1).equals(END_OF_LINE)) {
                        j = endOfValue + 1;
                        startOfValue = j;
                    } else {
                        i = endOfValue + 1;
                        spheresIndex++;
                        break;
                    }
                }

                try {
                    sphereArray[spheresIndex] = new Sphere(values[0], values[1], values[2], values[3]);
                } catch (SphereException ex) {
                    log.error(ARRAY_IS_NOT_INIT_EXC_MESSAGE);
                    throw new CreatorException(ARRAY_IS_NOT_INIT_EXC_MESSAGE);
                }

            }
        }

        return sphereArray;
    }

    private static int elemLocation(String string, String searchable, int start) {
        int position = -1;
        for (int i = start; i < string.length(); i++) {
            if (string.substring(i, i + 1).matches(searchable)) {
                position = i;
                return position;
            }
            if (string.substring(i, i + 1).matches(END_OF_LINE)) {
                break;
            }
        }
        return position;
    }

    private static int linesCount(String string) throws CreatorException {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.substring(i, i + 1).equals(START_OF_LINE)) {
                for (int j = i + 1; j < string.length(); j++) {
                    if (j + 1 > string.length()) {
                        throw new CreatorException(NO_END_OF_LINE_EXC_MESSAGE);
                    }
                    if (string.substring(j, j + 1).equals(END_OF_LINE)) {
                        count++;
                        i = j;
                        break;
                    }

                }
            }
        }
        return count;
    }
}
