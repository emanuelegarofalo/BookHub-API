package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.exception.WriterAlreadyExistsException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.repository.WriterRepository;
import com.manu.BookHubAPI.repository.specifications.WriterSpecification;
import com.manu.BookHubAPI.request.WriterCriteriaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;
    private final WriterSpecification writerSpecification;

    public Writer createWriter(@NotNull WriterDTO writer) throws WriterAlreadyExistsException {
        if (!writerRepository.existsByEmail(writer.getEmail())) {
            return writerRepository.save(new Writer(writer.getName(), writer.getLastName(), writer.getEmail()));
        } else throw new WriterAlreadyExistsException();
    }

    public Writer getWriterByFullName(String name, String lastName) throws WriterNotFoundException {
        return writerRepository.findByNameAndLastName(name, lastName).orElseThrow(WriterNotFoundException::new);
    }

    public Set<Writer> getWriters(WriterCriteriaDTO criteria) throws WriterNotFoundException{
        Set<Writer> writers = writerRepository.findAll(
                writerSpecification.combinedSpecification(criteria.id(), criteria.name(),
                        criteria.lastName(), criteria.email(), criteria.bookId())
        );
        if (writers.isEmpty()) throw new WriterNotFoundException();
        return writers;
    }

    public void deleteWriter(Long id) throws WriterNotFoundException {
        Writer writer = writerRepository.findById(id).orElseThrow(WriterNotFoundException::new);
        writerRepository.delete(writer);
    }

    public void updateWriter(Long id, WriterCriteriaDTO criteria) {
        Writer writerToUpdate = writerRepository.findById(id).orElseThrow(WriterNotFoundException::new);

        if (criteria.email() != null && !criteria.email().isEmpty()) {
            if (!writerRepository.existsByEmail(criteria.email())) {
                writerToUpdate.setEmail(criteria.email());
            } else throw new WriterAlreadyExistsException();
        }
        if (criteria.name() != null && !criteria.name().isEmpty()) {
            writerToUpdate.setName(criteria.name());
        }
        if (criteria.lastName() != null && !criteria.lastName().isEmpty()) {
            writerToUpdate.setLastName(criteria.lastName());
        }

        writerRepository.save(writerToUpdate);
    }
}
