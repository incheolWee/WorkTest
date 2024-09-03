package com.example.worktest.Work;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    WorkDTO convertToDto(Work work);
    Work convertToEntity(WorkDTO workDTO);
    WorkDTO createWork(WorkDTO workDTO);
    Optional<WorkDTO> getWorkById(Long id); // Return Optional<WorkDTO> to handle null values
    List<WorkDTO> getAllWorks();
    WorkDTO updateWork(Long id, WorkDTO workDTO);
    void deleteWork(Long id);
}
