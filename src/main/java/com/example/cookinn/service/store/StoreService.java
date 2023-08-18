package com.example.cookinn.service.store;

import com.example.cookinn.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
}
