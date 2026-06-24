package com.monstermeter.consumption;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class ConsumptionLogController {

    private final ConsumptionLogService consumptionLogService;

    @GetMapping("/user/{userId}")
    public List<ConsumptionLogResponseDTO> getLogsByUser(@PathVariable Long userId) {
        return consumptionLogService.getLogsByUser(userId).stream().map(ConsumptionLogResponseDTO::from).toList();
    }

    @GetMapping("/drink/{drinkId}")
    public List<ConsumptionLogResponseDTO> getLogsByDrink(@PathVariable Long drinkId) {
        return consumptionLogService.getLogsByDrink(drinkId).stream().map(ConsumptionLogResponseDTO::from).toList();
    }

    @PostMapping
    public ResponseEntity<ConsumptionLogResponseDTO> createLog(@RequestBody ConsumptionLogRequest request) {
        ConsumptionLog log = consumptionLogService.createLog(request.getUserId(), request.getDrinkId(),
                request.getNotes());
        return ResponseEntity.ok(ConsumptionLogResponseDTO.from(log));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        consumptionLogService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/notes")
    public ResponseEntity<ConsumptionLogResponseDTO> updateNotes(@PathVariable Long id, @RequestBody String notes) {
        return ResponseEntity.ok(ConsumptionLogResponseDTO.from(consumptionLogService.updateNotes(id, notes)));
    }
}
