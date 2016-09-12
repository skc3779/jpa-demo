package io.kangchun;

/**
 * Created by seokangchun on 2016. 9. 13..
 */

import lombok.Data;

import javax.persistence.*;  //**
import java.util.Date;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint( //추가
        name = "ID_NAME_AGE_UNIQUE",
        columnNames = {"ID", "NAME", "AGE"} )})
//@SequenceGenerator(name="MEMBER_ID_GENERATOR",
//        sequenceName="MEMBER_ID",
//        initialValue = 1, allocationSize = 1)
@TableGenerator(name="MEMBER_ID_GENERATOR",
        table="MY_SEQUENCES",
        pkColumnName = "SEQUENCE_NAME",
        pkColumnValue = "MEMBER_ID",
        allocationSize = 1)
@Data
public class Member {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_GENERATOR")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
//    @Column(name = "NAME") //추가 //**
    private String username;

    private Integer age;

    //=== 추가
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;
}
