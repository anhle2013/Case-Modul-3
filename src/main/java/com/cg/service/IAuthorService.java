package com.cg.service;

import com.cg.model.Authors;

public interface IAuthorService extends IGeneralService<Authors>{
    boolean existsByName(String name);
}
