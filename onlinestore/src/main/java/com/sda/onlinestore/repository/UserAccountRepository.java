package com.sda.onlinestore.repository;

import com.sda.onlinestore.model.UserAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountModel,Long> {
}
