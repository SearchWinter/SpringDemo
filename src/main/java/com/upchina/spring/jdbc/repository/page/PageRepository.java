package com.upchina.spring.jdbc.repository.page;

import com.upchina.spring.jdbc.repository.crud.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by anjunli on  2021/12/14
 **/
public interface  PageRepository extends PagingAndSortingRepository<User,Long> {
    @Override
    Iterable<User> findAllById(Iterable<Long> longs);
}
