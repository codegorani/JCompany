package com.spring.jcompany.springboot.domain.docs;

import com.spring.jcompany.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentsRepository extends JpaRepository<Documents, Long> {
    @Query("SELECT d FROM Documents d WHERE d.approval = :approval ORDER BY d.draftDate desc")
    List<Documents> findAllByApproval(User approval);

    @Query("SELECT d FROM Documents d WHERE d.user = :user ORDER BY d.draftDate desc")
    List<Documents> findAllByUser(User user);
}
