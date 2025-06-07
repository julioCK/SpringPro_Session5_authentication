package com.devsuperior.demo.repositories;


import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT email AS username, password AS password, role_id AS roleId, authority AS authority " +
                                        "FROM tb_user u INNER JOIN tb_user_role ur ON u.id = ur.user_id " +
                                        "INNER JOIN tb_role r ON r.id = ur.role_id " +
                                        "WHERE UPPER(u.email) = UPPER(:email)")
    public List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
