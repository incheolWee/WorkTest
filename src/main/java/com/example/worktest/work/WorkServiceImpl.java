package com.example.worktest.work;

import com.example.worktest.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    private final UserService userService;

    public WorkServiceImpl(WorkRepository workRepository, UserService userService) {
        this.workRepository = workRepository;
        this.userService = userService;
    }

    @Override
    public Work createWork(WorkDTO workDTO) {
        return null;
    }
//
//    @Override
//    public List<Work> getWorksByUser(Long userId) {
//        return null;
//    }
//
//    @Override
//    public Work deleteWork(Long workId) {
//        return null;
//    }
}
