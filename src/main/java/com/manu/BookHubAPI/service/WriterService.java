package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.exception.WriterAlreadyExistsException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.repository.WriterRepository;
import com.manu.BookHubAPI.repository.specifications.WriterSpecification;
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

    public Set<Writer> getWriters(Long id, String name, String lastName, String email) throws WriterNotFoundException{
        Set<Writer> writers = writerRepository.findAll(writerSpecification.combinedSpecification(id, name, lastName, email));

        if (writers.isEmpty()) throw new WriterNotFoundException();
        return writers;
    }

    public void deleteWriter(Long id) throws WriterNotFoundException {
        Writer writer = writerRepository.findById(id).orElseThrow(WriterNotFoundException::new);
        writerRepository.delete(writer);
    }

    public Writer updateWriter(Long id, String email, String firstName, String lastName) {
        Writer writerToUpdate = writerRepository.findById(id).orElseThrow(WriterNotFoundException::new);

        if (email != null && !email.isEmpty()) {
            if (!writerRepository.existsByEmail(email)) {
                writerToUpdate.setEmail(email);
            } else throw new WriterAlreadyExistsException();
        }
        if (firstName != null && !firstName.isEmpty()) {
            writerToUpdate.setName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            writerToUpdate.setLastName(lastName);
        }

        return writerRepository.save(writerToUpdate);
    }
}
