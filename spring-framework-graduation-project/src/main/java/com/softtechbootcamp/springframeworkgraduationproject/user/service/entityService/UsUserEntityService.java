package com.softtechbootcamp.springframeworkgraduationproject.user.service.entityService;

import com.softtechbootcamp.springframeworkgraduationproject.general.service.BaseEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.user.dao.UsUserDao;
import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import org.springframework.stereotype.Service;

@Service
public class UsUserEntityService extends BaseEntityService<UsUser, UsUserDao> {
    private UsUserDao usUserDao;

    public UsUserEntityService(UsUserDao dao, UsUserDao usUserDao) {
        super(dao);
        this.usUserDao = usUserDao;
    }

    public UsUser findByUsername(String username){
        return usUserDao.findByUsername(username);
    }

    public boolean isUsernameExist(String username){
        return usUserDao.existsByUsername(username);
    }


}
