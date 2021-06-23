package org.zerock.sbjwt.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tbl_board")
@Entity
public class Board {

    @SequenceGenerator(name = "seq_board_gen", sequenceName = "seq_board")
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board_gen")
    private Long bno;

    private String title;

    private String content;

    @JoinColumn
    @ManyToOne
    private User writer;

    private boolean delFlag;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public Long getBno() {
        return bno;
    }

    public User getWriter() {
        return writer;
    }


}