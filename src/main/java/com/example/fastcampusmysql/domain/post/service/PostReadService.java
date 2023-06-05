package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        /*
            반환 값 -> 리시트 [작성일자, 작성회원, 작성 게시물 갯수]
            select createdDate, memberId, count(id)
            from POST
            where memberId = :memberId and createdDate between firstDate and lastDate
            group by createdDate memberId
         */
        return postRepository.groupByCreatedDate(request);
    }

    public Page<Post> getPosts(Long memberId, Pageable pageable) {
        return postRepository.findAllByMemberId(memberId, pageable);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        var nextKey = posts.stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new PageCursor<Post>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey())
            return postRepository.findAllByLessThanIdAndMemberIdAndOrderByIDDesc(cursorRequest.key(), memberId, cursorRequest.size());
        return postRepository.findAllByMemberIdAndOrderByIDDesc(memberId, cursorRequest.size());
    }
}
