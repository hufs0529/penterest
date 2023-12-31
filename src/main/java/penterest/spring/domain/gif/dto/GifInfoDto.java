package penterest.spring.domain.gif.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import penterest.spring.domain.comment.dto.CommentInfoDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;
import penterest.spring.domain.member.dto.MemberInfoDto;
import penterest.spring.domain.member.entity.Member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GifInfoDto {

    private Long gifId;
    private String url;
    private String caption;

    //@JsonIgnoreProperties({"email", "password", "authority"}) // MemberInfoDto 클래스의 firstName, lastName 프로퍼티는 무시하도록 설정
    private String email;
    private List<CommentInfoDto> commentInfoDtoList;

    public GifInfoDto(Gif gif) {
        this.gifId = gif.getId();
        this.url = gif.getUrl();
        this.caption = gif.getCaption();
        this.email = gif.getWriter().getEmail();


        /**
         * 댓글과 대댓글을 그룹짓기
         * post.getCommentList()는 댓글과 대댓글이 모두 조회된다.
         */
        Map<Comment, List<Comment>> commentListMap = gif.getCommentList().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comment::getParent));


        /**
         * 댓글과 대댓글을 통해 CommentInfoDto 생성
         */
        commentInfoDtoList = commentListMap.keySet().stream()

                .map(comment -> new CommentInfoDto(comment, commentListMap.get(comment)))
                .toList();

    }

}
