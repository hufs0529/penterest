package penterest.spring.domain.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penterest.spring.domain.comment.dto.CommentSaveDto;
import penterest.spring.domain.comment.dto.CommentUpdateDto;
import penterest.spring.domain.comment.entity.Comment;
import penterest.spring.domain.comment.service.CommentService;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.service.GifService;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final GifService gifService;

    @PostMapping("/save/{gifId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("gifId") Long gifId,
                            @RequestBody CommentSaveDto commentSaveDto){
        commentService.save(gifId, commentSaveDto);
    }

    @PostMapping("/save/{gifId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void reCommentSave(@PathVariable("gifId") Long gifId,
                              @PathVariable("commentId") Long commentId,
                              @RequestBody CommentSaveDto commentSaveDto) {
        commentService.saveReComment(gifId, commentId, commentSaveDto);
    }

    @PutMapping("/update/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentUpdateDto commentUpdateDto) throws Exception {
        commentService.update(commentId, commentUpdateDto);
    }

    @DeleteMapping("/delete/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId) throws Exception {
        commentService.remove(commentId);
    }

}
