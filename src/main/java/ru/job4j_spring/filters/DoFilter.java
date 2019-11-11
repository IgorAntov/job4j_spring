package ru.job4j_spring.filters;

import org.hibernate.Session;
import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Component;
import ru.job4j_spring.models.Cars;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Component
public class DoFilter {

    public List<Cars> filtersCars(FilterList filterList, Session session) {

        try {
            List<Cars> result;
            filterEnable(filterList, session);
            result = (List<Cars>) session.createQuery("From Cars c").list();
            filterDisable(filterList, session);
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    private void filterDisable(FilterList filterList, Session session) {
        if (!filterList.getPeriod().isEmpty()) {
            session.disableFilter("dateFilter");
        }
        if (!filterList.getBrand().isEmpty()) {
            session.disableFilter("brandFilter");
        }
    }

    private void filterEnable(FilterList filterList, Session session) {
        if (!filterList.getBrand().isEmpty()) {
            session.enableFilter("brandFilter").setParameter("brand", Integer.parseInt(filterList.getBrand()));
        }
        if (!filterList.getPeriod().isEmpty()) {
            Date currentDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, -1 * Integer.parseInt(filterList.getPeriod()));
            Date currentDatePlusDays = c.getTime();
            session.enableFilter("dateFilter").setParameter("dateParam", currentDatePlusDays);
        }
    }

}
