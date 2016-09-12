package io.kangchun;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by seokangchun on 2016. 9. 13..
 */
@Entity
@Table(name="MY_SEQUENCES")
@Data
public class MySequences {
    @Id
    private String SequenceName;
    private Long nextVal;
}

