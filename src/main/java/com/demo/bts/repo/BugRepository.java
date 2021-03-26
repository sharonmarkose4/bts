package com.demo.bts.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.bts.entity.Bug;

public interface BugRepository extends MongoRepository<Bug, String> {

}
