package org.zerock.rboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Column(length = 50, nullable = false)
    private String mid;

    @Column(length = 50, nullable = false)
    private String mpw;

    @Column(length = 100, nullable = false)
    private String mname;

}
