package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.exception.WriterAlreadyExistsException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;

    public Writer createWriter(WriterDTO writer) throws WriterAlreadyExistsException {
        System.out.println(writer.getEmail());
        if (!writerRepository.existsByEmail(writer.getEmail())) {
            return writerRepository.save(new Writer(writer.getName(), writer.getLastName(), writer.getEmail()));
        } else throw new WriterAlreadyExistsException(writer.getEmail());
    }

    public Writer getWriterByFullName(String name, String lastName) throws WriterNotFoundException {
        return writerRepository.findByNameAndLastName(name, lastName).orElseThrow(WriterNotFoundException::new);
    }
}
