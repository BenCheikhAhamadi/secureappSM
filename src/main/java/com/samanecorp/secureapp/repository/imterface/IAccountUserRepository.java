package com.samanecorp.secureapp.repository.imterface;

import java.util.List;
import java.util.Optional;

import com.samanecorp.secureapp.entities.AccountUserEntity;

public interface IAccountUserRepository{
 public List<AccountUserEntity> list();
 public boolean delete(long id);
 public boolean update(AccountUserEntity e);
 public AccountUserEntity get(AccountUserEntity id);
 public boolean add(AccountUserEntity u);
}
