package com.chs.boot.gerp.core.sync.controller;

import com.chs.boot.gerp.core.sync.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SyncController {
    private final SyncService syncService;

    @GetMapping("/doSync")
    public void doSync(){
        syncService.doSync();
    }

}
