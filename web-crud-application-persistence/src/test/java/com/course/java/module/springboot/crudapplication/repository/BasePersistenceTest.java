package com.course.java.module.springboot.crudapplication.repository;

import com.course.java.module.springboot.crudapplication.configuration.PersistenceConfiguration;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = PersistenceConfiguration.class)
public abstract class BasePersistenceTest<T extends JpaRepository> {

    @Autowired
    protected TestEntityManager testEntityManager;

    @Autowired
    protected T repository;

    @BeforeEach
    public void truncateTables() {
        EntityManager entityManager = testEntityManager.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;").executeUpdate();

        Set<String> tableNames = session.getSessionFactory().getMetamodel().getEntities().stream()
                .map(entityType -> entityType.getJavaType().getAnnotation(Table.class))
                .filter(Objects::nonNull)
                .map(Table::name)
                .collect(Collectors.toSet());

        tableNames.forEach(tableName -> entityManager
                .createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
    }

}
