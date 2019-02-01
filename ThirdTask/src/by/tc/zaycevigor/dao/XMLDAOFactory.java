package by.tc.zaycevigor.dao;

import by.tc.zaycevigor.dao.impl.SaxXmlDao;
import by.tc.zaycevigor.dao.impl.StaxXmlDao;

public class XMLDAOFactory {
    private final static XMLDAOFactory instance = new XMLDAOFactory();

    private XMLDAOFactory() {
    }

    public static XMLDAOFactory getInstance() {
        return instance;
    }

    public XMLDao getDAO(DAOType type) throws XMLDaoException {
        switch (type) {
            case SAX:
                return SaxXmlDao.getInstance();
            case STAX:
                return StaxXmlDao.getInstance();
            default:
                throw new XMLDaoException("No such DAO");
        }
    }

    public enum DAOType {
        SAX, STAX, DOM;
    }
}
