package com.se_notification_server.repository;

import com.se_notification_server.domain.AccountTagMapping;
import java.util.List;
import java.util.Optional;

public interface FcmRepository {

    AccountTagMapping save(AccountTagMapping accountTagMapping);
    Optional<AccountTagMapping> findById(Long userId);
    List<AccountTagMapping> findAll(); //관리자가 보내는 메시지
    boolean existsAccountTagMappingByToken(String token);
}
