package com.yang.blog.service;

import com.yang.blog.dao.CommentRepository;
import com.yang.blog.dao.UserRepository;
import com.yang.blog.po.Comment;
import com.yang.blog.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Comment> ListCommentByBlogId(Long blogId){
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");
        List<Comment> list = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(list);
    }
    @Transactional
    @Override
    public Comment save(Comment comment) {
        if(comment.getParentComment().getId()==-1){
            comment.setParentComment(null);
        }else{
            comment.setParentComment(commentRepository.getOne(comment.getParentComment().getId()));
        }

        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * copy一个新的容器，防止数据库修改
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView = new ArrayList<>();
        for(Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentView.add(c);
        }
        combinChildren(commentView);
        return commentView;
    }

    private void combinChildren(List<Comment> comments){
        for (Comment comment : comments) {


            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply : replys1) {
                recursively(reply);
            }

            comment.setReplyComments(tempReplys);
            System.out.println(tempReplys);
            tempReplys=new ArrayList<>();
        }
    }
    private List<Comment> tempReplys = new ArrayList<>();

    private void recursively(Comment comment){
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply : replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
