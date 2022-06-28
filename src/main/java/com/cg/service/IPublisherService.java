package com.cg.service;

import com.cg.model.Publisher;

public interface IPublisherService extends IGeneralService<Publisher> {
    boolean existsByName(String name);
}
