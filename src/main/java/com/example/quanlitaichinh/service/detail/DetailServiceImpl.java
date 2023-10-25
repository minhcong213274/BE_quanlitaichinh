package com.example.quanlitaichinh.service.detail;

import com.example.quanlitaichinh.model.Detail;
import com.example.quanlitaichinh.repo.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;
    @Override
    public Iterable<Detail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public Optional<Detail> findById(Long id) {
        return detailRepository.findById(id);
    }

    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public void remove(Long id) {
        detailRepository.deleteById(id);
    }

    @Override
    public Detail createDetail(Detail detail) {
        return detailRepository.save(detail);
    }
}
