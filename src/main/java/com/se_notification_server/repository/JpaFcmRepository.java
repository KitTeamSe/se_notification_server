package com.se_notification_server.repository;

import com.se_notification_server.domain.AccountTokenMapping;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaFcmRepository extends JpaRepository<AccountTokenMapping, Long>, FcmRepository {

    boolean existsAccountTagMappingByToken(String token);

}
