package com.se_notification_server.repository;

import com.se_notification_server.domain.AccountTokenMapping;
import java.util.List;
import java.util.Optional;

public interface FcmRepository {

    AccountTokenMapping save(AccountTokenMapping accountTokenMapping);
    Optional<AccountTokenMapping> findById(Long userId);
    List<AccountTokenMapping> findAll(); //관리자가 보내는 메시지
    boolean existsAccountTagMappingByToken(String token);
}
