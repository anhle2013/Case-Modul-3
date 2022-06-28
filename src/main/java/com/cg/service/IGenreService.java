package com.cg.service;

import com.cg.model.Genres;

public interface IGenreService extends IGeneralService<Genres>{
    boolean existsByName(String name);
}
