package com.cg.service;

import com.cg.model.Publishers;

public interface IPublisherService extends IGeneralService<Publishers> {
    boolean existsByName(String name);
}
