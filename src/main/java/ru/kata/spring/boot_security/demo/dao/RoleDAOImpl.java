package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Role setNewRole(String nameRole) {
        entityManager.persist(new Role(nameRole));
        return getRole(nameRole);
    }

    @Override
    public Role getRole(String nameRole) {
        List<Role> role = entityManager.createQuery("from Role r where r.role=?1", Role.class)
                .setParameter(1, nameRole)
                .getResultList();
        return role.size() > 0 ? role.get(0) : setNewRole(nameRole);
    }

}
