package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/comment") // This means URL's start with /comment (after Application path)
public class CommentController {
  @Autowired // This means to get the bean called commentRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private CommentRepository commentRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewComment (@RequestParam String text) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Comment n = new Comment();
    n.setText(text);
    commentRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Comment> getAllComments() {
    // This returns a JSON or XML with the Comments
    return commentRepository.findAll();
  }
}