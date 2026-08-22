package com.infor.AWS.service;

import java.util.Optional;
import java.util.UUID;

public interface BasicEntityService<T>{

    public T create(T t);

    public Optional<T> getById(UUID uuid);

    public void deleteById(UUID uuid);

    public T update(T t);
}
