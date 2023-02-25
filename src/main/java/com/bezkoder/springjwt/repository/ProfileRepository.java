package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Profile;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    public Profile findProfileByUser(User user);
}
