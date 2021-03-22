package com.se_notification_server.repository;

import com.se_notification_server.domain.AccountTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JpaFcmRepository extends JpaRepository<AccountTagMapping, Long>, FcmRepository {

    boolean existsAccountTagMappingByToken(String token);

}
