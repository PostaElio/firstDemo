package alkemy.firstdemo.service.impl;

import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.repository.PostRepository;
import alkemy.firstdemo.service.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public PostEntity getById(Long id){
        return postRepository.findById(id).get();
    }

    @Override
    public PostEntity save(PostEntity post) {

        return postRepository.save(post);
    }

    @Override
    public PostEntity update(PostEntity post) {
        return postRepository.save(post);

    }

    @Override
    public void delete(Long id) throws NotFoundException {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }
}
