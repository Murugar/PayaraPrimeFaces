package com.iqmsoft.payara.jsf;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.enterprise.inject.Model;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

@Model
public class AppView implements Serializable {

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private final ScheduleModel eventModel = new LazyScheduleModel() {
        @Override
        public void loadEvents(final Date startDate, final Date endDate) {
            final LocalDate startLocalDate
                    = LocalDateTime.ofInstant(startDate.toInstant(), ZoneOffset.UTC).toLocalDate();
            final LocalDate endLocalDate
                    = LocalDateTime.ofInstant(endDate.toInstant(), ZoneOffset.UTC).toLocalDate().minusDays(1);

            for (LocalDate i = startLocalDate; !i.isAfter(endLocalDate); i = i.plusDays(1)) {
                addEvent(new DefaultScheduleEvent("happening",
                        Date.from(i.atStartOfDay().plusHours(6).toInstant(ZoneOffset.UTC)),
                        Date.from(i.atStartOfDay().plusHours(12).toInstant(ZoneOffset.UTC))));
            }
        }
    };
}
