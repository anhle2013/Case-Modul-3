package com.cg.service;

import com.cg.model.Genre;

public interface IGenreService extends IGeneralService<Genre>{
    boolean existsByName(String name);
}
