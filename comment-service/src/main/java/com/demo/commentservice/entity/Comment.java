package com.demo.commentservice.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Data
@ToString(of = {"id"})
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    private String text;
}
