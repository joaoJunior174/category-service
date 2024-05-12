package com.raiadrogasil.categoryadmin.repository;

import com.raiadrogasil.categoryadmin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select tc from Category tc where tc.sessionToken = :session_token")
    List<Category> findBySessionToken(String session_token);

}