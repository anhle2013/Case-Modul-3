package com.cg.service;

import com.cg.model.Author;

public interface IAuthorService extends IGeneralService<Author>{
    boolean existsByName(String name);
}
