package com.spring.jcompany.springboot.domain.bulletin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
}
