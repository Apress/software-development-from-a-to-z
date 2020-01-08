package com.example.persistence.repository;

import com.example.persistence.entity.module.Module;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The course_module repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface ModuleRepository extends PagingAndSortingRepository<Module, Long> {

}
