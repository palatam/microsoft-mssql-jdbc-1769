package org.example;

import org.example.jpa.entity.DateTime2Entity;
import org.example.jpa.entity.DateTimeOffsetEntity;
import org.example.jpa.repository.DateTime2EntityRepository;
import org.example.jpa.repository.DateTimeOffsetEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleIT extends AbstractBaseIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private DateTime2EntityRepository dateTime2EntityRepository;

    @Autowired
    private DateTimeOffsetEntityRepository dateTimeOffsetEntityRepository;

    @Test
    public void test() {
        assertNotNull(this.applicationContext);
    }

    @Test
    public void testDateTime2() {
        final OffsetDateTime expectedValue = OffsetDateTime.of(2022, 1, 31, 12, 12, 34, 56789, ZoneOffset.MAX);

        final DateTime2Entity dateTime2 = new DateTime2Entity();
        dateTime2.setValue(expectedValue);

        final TransactionTemplate transactionTemplate1 = new TransactionTemplate(this.transactionManager);
        final DateTime2Entity expectedDateTime2Entity = transactionTemplate1.execute(status -> dateTime2EntityRepository.save(dateTime2));
        assertNotNull(expectedDateTime2Entity);

        final TransactionTemplate transactionTemplate2 = new TransactionTemplate(this.transactionManager);
        final OffsetDateTime actualDateTime2Value = transactionTemplate2.execute(status -> dateTime2EntityRepository.getById(expectedDateTime2Entity.getId()).getValue());
        assertNotNull(actualDateTime2Value);

        this.assertEquals(expectedValue, actualDateTime2Value);
    }

    @Test
    public void testDateTimeOffset() {
        final OffsetDateTime expectedValue = OffsetDateTime.of(2022, 1, 31, 12, 12, 34, 56789, ZoneOffset.MAX);

        final DateTimeOffsetEntity dateTimeOffset = new DateTimeOffsetEntity();
        dateTimeOffset.setValue(expectedValue);

        final TransactionTemplate transactionTemplate1 = new TransactionTemplate(this.transactionManager);
        final DateTimeOffsetEntity expectedDateTimeOffsetEntity = transactionTemplate1.execute(status -> dateTimeOffsetEntityRepository.save(dateTimeOffset));
        assertNotNull(expectedDateTimeOffsetEntity);

        final TransactionTemplate transactionTemplate2 = new TransactionTemplate(this.transactionManager);
        final OffsetDateTime actualDateTimeOffsetValue = transactionTemplate2.execute(status -> dateTimeOffsetEntityRepository.getById(expectedDateTimeOffsetEntity.getId()).getValue());
        assertNotNull(actualDateTimeOffsetValue);

        this.assertEquals(expectedValue, actualDateTimeOffsetValue);
    }

    private void assertEquals(final OffsetDateTime expected, final OffsetDateTime actual) {
        System.out.println(expected);
        System.out.println(actual);
        long diff = expected.until(actual, ChronoUnit.MINUTES);
        System.out.println("Diff: " + diff + " minutes");
        Assertions.assertEquals(0L, diff);
    }
}
