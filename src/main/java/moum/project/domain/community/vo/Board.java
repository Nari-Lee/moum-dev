package moum.project.domain.community.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int boardNo;
    private String title;
    private String content;
    private int userNo;
    private java.util.Date postDate;
    private int viewCount;
    private boolean isPublic;
    private boolean isDeleted;


}