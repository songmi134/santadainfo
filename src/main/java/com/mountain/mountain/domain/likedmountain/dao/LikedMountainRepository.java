package com.mountain.mountain.domain.likedmountain.dao;


import com.mountain.mountain.domain.likedmountain.model.Likedmountain;
import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedMountainRepository extends JpaRepository<Likedmountain,Long> {


    Likedmountain findByMountainNoAndUser(Mountain mountainNo, User user);

    Page<Likedmountain> findAllByUser(User user, Pageable pageable);
}
