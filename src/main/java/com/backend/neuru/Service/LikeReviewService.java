package com.backend.neuru.Service;

import com.backend.neuru.DTO.PostDTO;
import com.backend.neuru.DTO.ResponseDTO;
import com.backend.neuru.Entity.LikeEntity;
import com.backend.neuru.Entity.ReviewEntity;
import com.backend.neuru.Entity.WalkwayEntity;
import com.backend.neuru.Repository.LikeRepository;
import com.backend.neuru.Repository.ReviewRepository;
import com.backend.neuru.Repository.WalkwayRepository;
import com.backend.neuru.exception.CustomException;
import com.backend.neuru.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeReviewService {
    private final ReviewRepository reviewRepository;
    private final LikeRepository likeRepository;
    private final WalkwayRepository walkwayRepository;

    @Transactional
    public ResponseDTO<?> registerReview(Long walk_id, String reviewContents) {
        WalkwayEntity walkway = walkwayRepository.findById(walk_id).get();
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReview_content(reviewContents);
        reviewEntity.setWalkway(walkway);
        reviewRepository.save(reviewEntity);
        return ResponseDTO.success("산책로 리뷰 등록 완료", reviewEntity);
    }

//    @Transactional
//    public ResponseDTO<?> fixReview(Long walk_id, String reviewContents) {
//        WalkwayEntity walkway = walkwayRepository.findById(walk_id).get();
//        Optional<ReviewEntity> reviewEntity = reviewRepository.findByWalkway(walkway);
//        reviewEntity.ifPresent(reviewEntity1 -> reviewEntity1.setReview_content(reviewContents));
//        reviewRepository.save(reviewEntity.get());
//        return ResponseDTO.success("산책로 리뷰 수정 완료", reviewEntity);
//    }

    @Transactional
    public ResponseDTO<?> getReviews(Long walk_id) {

        // 주어진 walk_id로 WalkwayEntity를 조회합니다.
        WalkwayEntity walkway = walkwayRepository.findById(walk_id)
                .orElseThrow(() -> new NoSuchElementException("No walkway found with id: " + walk_id));

        // WalkwayEntity를 기준으로 ReviewEntity들을 조회합니다.
        List<ReviewEntity> reviewEntityList = reviewRepository.findByWalkway(walkway);

        return ResponseDTO.success("산책로 리뷰 조회 완료", reviewEntityList);
    }


    @Transactional
    public ResponseDTO<?> registerLike(Long walk_id) {
        WalkwayEntity walkway = walkwayRepository.findById(walk_id).get();
        int likeCount = walkway.getLike_count();
        likeCount = likeCount + 1;
        walkway.setLike_count(likeCount);
        walkwayRepository.save(walkway);
        return ResponseDTO.success("산책로 좋아요 수 증가 완료", walkway);
    }

    @Transactional
    public ResponseDTO<?> deleteLike(Long walk_id) {
        WalkwayEntity walkway = walkwayRepository.findById(walk_id).get();
        int likeCount = walkway.getLike_count();
        if (likeCount > 0) {
            likeCount = likeCount - 1;
            walkway.setLike_count(likeCount);
            walkwayRepository.save(walkway);
            return ResponseDTO.success("산책로 좋아요 수 감소 완료", walkway);
        } else{
            return ResponseDTO.error("기존의 좋아요 수가 0개이므로 감소할 수 없습니다.");
        }
    }

}
