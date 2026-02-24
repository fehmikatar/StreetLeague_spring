package tn.esprit._4se2.pi.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4se2.pi.entities.Like;
import tn.esprit._4se2.pi.repository.LikeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService {

    private final LikeRepository likeRepository;

    @Override
    public Like addLike(Like like) {
        like.setCreatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public Like getLikeById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found with id: " + id));
    }

    @Override
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}
