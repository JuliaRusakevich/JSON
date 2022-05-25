package com.gmail.juliarusakevich.service.mapper.api;

public interface IMapper<F, T> {
    T mapFrom(F object);
}
