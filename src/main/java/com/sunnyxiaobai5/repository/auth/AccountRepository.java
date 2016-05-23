package com.sunnyxiaobai5.repository.auth;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.domain.auth.Account;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {

    /**
     * 根据登陆名称查询
     *
     * @param login 登陆名
     * @return
     */
    Optional<Account> findOneByLogin(String login);

}
