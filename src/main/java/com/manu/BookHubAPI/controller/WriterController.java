package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.WriterMapper;
import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.exception.WriterAlreadyExistsException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {
    private final WriterService writerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createWriter(@RequestBody WriterDTO writer) {
        try {
            Writer createdWriter = writerService.createWriter(writer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Writer created", WriterMapper.INSTANCE.toWriterDTO(createdWriter)));
        } catch (WriterAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/find-by-criteria")
    public ResponseEntity<ApiResponse> findWriterByCriteria(@RequestParam(required = false) Long id,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String lastName,
                                                            @RequestParam(required = false) String email) {
        try {
            Set<Writer> writers = writerService.getWriters(id, name, lastName, email);
            return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("Writer found", writers.stream().map(WriterMapper.INSTANCE::toWriterDTO).toList()));
        } catch (WriterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

}
