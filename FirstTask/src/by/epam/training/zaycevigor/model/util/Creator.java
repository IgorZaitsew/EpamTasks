package by.epam.training.zaycevigor.model.util;

import by.epam.training.zaycevigor.model.util.exceptions.CreatorException;

public interface Creator {

    public Object[] create(String params) throws CreatorException;
}
