package com.example.persistence.repository;

import com.example.persistence.entity.modulelecture.ModuleLecture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * The lecture repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface ModuleLectureRepository extends PagingAndSortingRepository<ModuleLecture, Long> {
    @Query("" +
            "SELECT ml " +
            "FROM ModuleLecture ml JOIN FETCH ml.lecture l " +
            "WHERE ml.moduleId IN :moduleIds ")
    List<ModuleLecture> findByModuleIdsFetchLecture(@Param("moduleIds") Collection<Long> moduleIds);
}
