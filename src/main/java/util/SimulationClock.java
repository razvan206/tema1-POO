package util;

import java.time.LocalDateTime;

public class SimulationClock {
    private LocalDateTime currentTimestamp;
    private boolean importActive = false;

    public void updateTime(LocalDateTime newTimestamp){
        this.currentTimestamp = newTimestamp;
    }

    public void startImport() {
        this.importActive = true;
    }

    public void stopImport() {
        this.importActive = false;
    }

    public boolean isImportActive() {
        return importActive;
    }

    public LocalDateTime getCurrentTimestamp() {
        return currentTimestamp;
    }
}
