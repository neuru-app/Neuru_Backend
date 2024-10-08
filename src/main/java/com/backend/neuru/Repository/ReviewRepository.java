package com.backend.neuru.Repository;

import com.backend.neuru.Entity.LikeEntity;
import com.backend.neuru.Entity.ReviewEntity;
import com.backend.neuru.Entity.WalkwayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByWalkway(WalkwayEntity walkwayEntity);
}
