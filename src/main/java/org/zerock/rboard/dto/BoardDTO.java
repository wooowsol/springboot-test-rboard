package org.zerock.rboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long gno;
    private String title;
    private String content;
    private String writer; // 작성자 아이디
    private String mname; // 작성자 이름
    private LocalDateTime regDate, modDate;

}
