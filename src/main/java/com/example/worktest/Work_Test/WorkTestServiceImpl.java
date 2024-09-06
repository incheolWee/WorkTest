package com.example.worktest.Work_Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkTestServiceImpl implements WorkTestService {

    @Autowired
    private WorkTestRepository workRepository;

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //데이터 형식 설정
    @Override
    public WorkTestDTO convertToDto(WorkTest work) {
        if (work == null) {
            return null;
        }
        return new WorkTestDTO(
                work.getId(),
                work.getUserId(),
                work.getFileId(),
                work.isShared(),
                work.isTrashed(),
                work.isFinish(),
                work.getCreateDate() != null ? work.getCreateDate().format(FORMATTER) : null,
                work.getUpdateDate() != null ? work.getUpdateDate().format(FORMATTER) : null,
                work.getDeleteDate() != null ? work.getDeleteDate().format(FORMATTER) : null,
                work.getOpenDate() != null ? work.getOpenDate().format(FORMATTER) : null
        );
    }

    @Override
    public WorkTest convertToEntity(WorkTestDTO workDTO) {
        if (workDTO == null) {
            return null;
        }
        return new WorkTest(
                workDTO.getId(),
                workDTO.getUserId(),
                workDTO.getFileId(),
                workDTO.isShared(),
                workDTO.isTrashed(),
                workDTO.isFinish(),
                workDTO.getCreateDate() != null ? LocalDateTime.parse(workDTO.getCreateDate(), FORMATTER) : null,
                workDTO.getUpdateDate() != null ? LocalDateTime.parse(workDTO.getUpdateDate(), FORMATTER) : null,
                workDTO.getDeleteDate() != null ? LocalDateTime.parse(workDTO.getDeleteDate(), FORMATTER) : null,
                workDTO.getOpenDate() != null ? LocalDateTime.parse(workDTO.getOpenDate(), FORMATTER) : null
        );
    }

    @Override
    public List<WorkTestDTO> getAllWorks() { //모든 작업 가져오기
        return workRepository.findAll()
                .stream() // stream() 메소드는 컬렉션을 스트림으로 변환
                .map(this::convertToDto) //  map() 메소드는 스트림의 각 요소에 함수를 적용
                .collect(Collectors.toList()); // collect() 메소드는 스트림의 요소를 수집
    }

    @Override
    public Optional<WorkTestDTO> getWorkById(Long id) { //id로 작업 찾기
        return workRepository.findById(id) //id로 작업 찾기
                .map(this::convertToDto); //작업을 WorkDTO로 변환
    }

    @Override
    public WorkTestDTO createWork(WorkTestDTO workDTO) { //작업 생성
        WorkTest work = convertToEntity(workDTO); //WorkDTO를 Work로 변환
        WorkTest savedWork = workRepository.save(work); //작업 저장
        return convertToDto(savedWork); //작업을 WorkDTO로 변환
    }

    @Override
    public WorkTestDTO updateWork(Long id, WorkTestDTO workDTO) { //작업 업데이트
        Optional<WorkTest> optionalWork = workRepository.findById(id); //id로 작업 찾기
        if (optionalWork.isPresent()) { //작업이 존재하면
            WorkTest work = optionalWork.get();
            work.setUserId(workDTO.getUserId());
            work.setFileId(workDTO.getFileId());
            work.setShared(workDTO.isShared());
            work.setTrashed(workDTO.isTrashed());
            work.setFinish(workDTO.isFinish());
            work.setCreateDate(workDTO.getCreateDate() != null ? LocalDateTime.parse(workDTO.getCreateDate(), FORMATTER) : null);
            work.setUpdateDate(workDTO.getUpdateDate() != null ? LocalDateTime.parse(workDTO.getUpdateDate(), FORMATTER) : null);
            work.setDeleteDate(workDTO.getDeleteDate() != null ? LocalDateTime.parse(workDTO.getDeleteDate(), FORMATTER) : null);
            work.setOpenDate(workDTO.getOpenDate() != null ? LocalDateTime.parse(workDTO.getOpenDate(), FORMATTER) : null);
            WorkTest updatedWork = workRepository.save(work); //작업 업데이트
            return convertToDto(updatedWork); //작업을 WorkDTO로 변환
        } else {
            return null; //작업이 존재하지 않으면 null 반환
        }
    }

    @Override
    public void deleteWork(Long id) { //작업 id로 삭제
        workRepository.deleteById(id); //작업 삭제
    }
    @Override
    public WorkTestDTO updateOpenDate(Long id){
        Optional<WorkTest> optionalWork = workRepository.findById(id); //id로 작업 찾기
        if (optionalWork.isPresent()) { //작업이 존재하면
            WorkTest work = optionalWork.get();
            work.setOpenDate(LocalDateTime.now());
            WorkTest updatedWork = workRepository.save(work); //작업 업데이트
            return convertToDto(updatedWork); //작업을 WorkDTO로 변환
        } else {
            throw new RuntimeException("Work entity with ID " + id + " not found.");
        }
    }
}
