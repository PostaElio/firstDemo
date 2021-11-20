package alkemy.firstdemo.service.impl;

import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.repository.PostRepository;
import alkemy.firstdemo.service.PostService;
import alkemy.firstdemo.service.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostEntity> getAllByTitle(String title) {
        return postRepository.getAllByTitle(title);
    }

    @Override
    public List<PostEntity> getAllByCategory(String category) {
        return postRepository.getAllByCategory(category);
    }

    @Override
    public List<PostEntity> getAllByTitleAndCategory(String title, String category) {
        return postRepository.getAllByTitleAndCategory(title,category);
    }

    @Override
    public PostEntity getById(Long id) throws IdNotFoundException {
        try {
            return postRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new IdNotFoundException("",id,"Post");
        }
    }

    @Override
    public PostEntity save(PostEntity post) throws IdNotFoundException{
        //Me hubiese gustado caputarar la excepcion de los atributos null de un objeto y el id de un usuario no existente
        try {
            return postRepository.save(post);
        }catch (Exception ex) {
            throw new IdNotFoundException("", post.getAuthor().getId(), "Author");
        }
    }

    @Override
    public void delete(Long id) throws EmptyResultDataAccessException {
        try {
            postRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new IdNotFoundException("",id,"Post");
        }
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Override
    public List<PostEntity> getAll() {
        return postRepository.findAll();
    }
}
