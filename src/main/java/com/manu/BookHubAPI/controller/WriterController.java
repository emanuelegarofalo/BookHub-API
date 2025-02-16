package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.mapper.WriterMapper;
import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.request.WriterCriteriaDTO;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {
    private final WriterService writerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createWriter(@RequestBody WriterDTO writer) {
        Writer createdWriter = writerService.createWriter(writer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Writer created", WriterMapper.INSTANCE.toWriterDTO(createdWriter)));
    }

    @GetMapping("/find-by-criteria")
    public ResponseEntity<ApiResponse> findWriterByCriteria(@ModelAttribute WriterCriteriaDTO criteria) {
        Set<Writer> writers = writerService.getWriters(criteria);
        return ResponseEntity.ok(new ApiResponse("Writer found", writers.stream().map(WriterMapper.INSTANCE::toWriterDTO).toList()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteWriter(@PathVariable Long id) {
        writerService.deleteWriter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateWriter(@PathVariable Long id,
                                                    @ModelAttribute WriterCriteriaDTO criteria) {
        writerService.updateWriter(id, criteria);
        return ResponseEntity.ok(new ApiResponse("update done", null));
    }
}
