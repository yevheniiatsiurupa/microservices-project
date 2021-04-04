package com.demo.postservice.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@ToString(of = {"id"})
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    private String topic;
}
